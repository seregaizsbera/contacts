#! /usr/bin/perl -w
use locale;
use strict;
use HTML::Parser;
use Pg;
use constant DATABASE_PROPERTIES => "../database.properties";
use constant USER => "apacheagent";
use constant PASSWORD => "apache";
use constant GPRS_TABLE => "gprs_buffer";
use constant CALLS_TABLE => "calls_buffer";
use constant CALL_INCOMING => 1;
use constant CALL_OUTGOING => 2;
use constant CALL_PHONE => 1;
use constant CALL_SMS => 2;
use constant CALL_SOS => 3;
use constant URL_INTERNET => 1;
use constant URL_WAP => 2;

#*****************************************************************************
sub do_body($$);
sub do_start_tag($$);
sub do_stop_tag($$);
sub trim($);
sub create_sql_value($);
sub insert_gprs($$);
sub insert_call($$);
sub my_time($);

#*****************************************************************************
my $parser = HTML::Parser->new(api_version => 3,
                               start_h => [\&do_start_tag, "self, tagname"],
			       end_h => [\&do_stop_tag, "self, tagname"],
			       text_h => [\&do_body, "self, dtext"]
			      );

my $field_text = "";
my @expenses = ();
my @fields = ();

#*****************************************************************************
my $source = $ARGV[0];
if (!defined($source)) {
    die "Usage: $0 <source-file-name>\n";
}

[ -r $source ] || die "Can't open source file $source\n";

my $database = `cat @{[DATABASE_PROPERTIES]}`;
$database or die "�� ���� ����� ���� ������.\n";

$parser->parse_file($source);

my $connection = Pg::connectdb("dbname=$database user=@{[USER]} password=@{[PASSWORD]}");

$connection->status == PGRES_CONNECTION_OK or
    die "Connection to database failed with message \"$connection->errorMessage\"";

foreach my $expense (sort {my_time($a->[0]) cmp my_time($b->[0])} @expenses) {
    $connection->exec("BEGIN");
    eval {
        if ($expense->[3] eq "GPRS") {
            insert_gprs($connection, $expense);
	} else {
	    insert_call($connection, $expense);
	}
        $connection->exec("COMMIT");
    }; if($@) {
        my $errorMessage = $@;
        print STDERR "Error inserting record for $expense->[0] $expense->[1]:\n";
        print STDERR $errorMessage;
        print STDERR $connection->errorMessage, "\n";
        $connection->exec("ROLLBACK");
    };
}

#*****************************************************************************
sub insert_call($$) {
    my ($connection, $call) = @_;
    my ($moment, $phone, $place, $ignore1, $type, $ignore2, $time, $price) = @$call;
    my $direction = CALL_OUTGOING;
    #print join(" | ", @$call), "\n";
    #return;
    $phone =~ s/\+7/8/;
    $phone =~ s/^<--7(\d{10})$/<--8$1/;
    if ($phone =~ s/^<--//) {
        $direction = CALL_INCOMING;
	if ($phone =~ /^\d{10}$/) {
	    $phone = "8$phone";
	}
    }
    $moment =~ m|^(\d{2}/\d{2}/)(.*)$|;
    $moment = "${1}20${2}";
    $time =~ s/\s+//g;
    if ($type =~ /sms/i) {
        $type = CALL_SMS;
    } elsif ($type =~ /�����/i) {
        $type = CALL_PHONE;
    } elsif ($type =~ /emrg/i) {
        $type = CALL_SOS;
    } else {
        die "Unknown call type: $type (" . join("\t", @$call) . ")\n";
    }
    $moment = create_sql_value($moment);
    $phone = create_sql_value($phone);
    $place = create_sql_value($place);
    $time = create_sql_value($time);
    my $query = "insert into @{[CALLS_TABLE]} (moment, direction, phone, place, type, quantity, price, note)"
                . " values (to_timestamp($moment, 'dd/MM/yyyy HH24:MI'), $direction, $phone, $place, $type, $time, $price, null)";
    my $result = $connection->exec($query);
    $result->resultStatus == PGRES_COMMAND_OK or die "Execution of query \"$query\" failed";
}

sub insert_gprs($$) {
    my ($connection, $gprs) = @_;
    my ($moment, $url, $ignore1, $igonre2, $ignore3, $ignore4, $traffic, $price) = @$gprs;
    #print join(" | ", @$gprs), "\n";
    #return;
    my $direction = CALL_OUTGOING;
    if ($url =~ s/^<--//) {
        $direction = CALL_INCOMING;
    }
    if ($url eq "internet.mts.ru") {
        $url = URL_INTERNET;
    } elsif ($url eq "wap.mts.ru") {
        $url = URL_WAP;
    } else {
        die "Unknown url: $url\n";
    }
    $moment =~ m|^(\d{2}/\d{2}/)(.*)$|;
    $moment = "${1}20${2}";
    $moment = create_sql_value($moment);
    $traffic =~ s/��//ig;
    $traffic = $traffic * 1024;
    my $query = "insert into @{[GPRS_TABLE]} (moment, direction, url, traffic, price, note)"
                . " values (to_timestamp($moment, 'dd.MM.yyyy HH24:MI:SS'), $direction, $url, $traffic, $price, null)";
    my $result = $connection->exec($query);
    $result->resultStatus == PGRES_COMMAND_OK or die "Execution of query \"$query\" failed";
}

sub my_time($) {
    $_ = shift;
    my ($day, $month, $year, $hour, $minute) = m|^(\d\d)/(\d\d)/(\d\d) (\d\d):(\d\d)$|;
    if (!defined($year)) {
        ($day, $month, $year) = m|^(\d\d)/(\d\d)/(\d\d)$|;
        ($hour, $minute) = (0, 0);
    }
    if ($year < 70) {
        $year += 2000;
    } else {
        $year += 1900;
    }
    return sprintf("%.4d-%.2d-%.2d %.2d:%.2d:%.2d", $year, $month, $day, $hour, $minute, 0);
}

#*****************************************************************************
sub do_start_tag($$) {
    my($self, $tagname) = @_;
    if($tagname eq "tr") {
        @fields = ();
        return;
    }
    if($tagname eq "td") {
        $field_text = "";
        return;
    }
    if($tagname eq "br") {
        trim($field_text);
	if ($field_text ne "") {
            $field_text .= "\n";
	}
        return;
    }
}

sub do_stop_tag($$) {
    my($self, $tagname) = @_;
    if($tagname eq "tr") {
        my @temp = @fields;
        push(@expenses, \@temp);
        return;
    }
    if($tagname eq "td") {
        push(@fields, $field_text);
        return;
    }
}

sub do_body($$) {
    my($self, $text) = @_;
    chomp($text);
    $field_text .= $text;
}

sub trim($) {
    my $str = $_[0];
    $str =~ s/^\s+//g;
    $str =~ s/\s+$//g;
    $_[0] = $str;
    return $str;
}

sub create_sql_value($) {
    my $str = $_[0];
    if(!defined($str) || $str eq "") {
        $str = "null";
    } else {
        $str =~ s/\'/\'\'/g;
        $str = "'$str'";
    }
    return $str;
}
