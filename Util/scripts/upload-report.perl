#! /usr/bin/perl -w
use locale;
use strict;
use HTML::Parser;
use Pg;
use constant DATABASE_PROPERTIES => "../../Z_Buffer/database.properties";
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
# Параметры HTML-отчета о звонках
use constant SIGNAL_TAG_NAME => "table";
use constant SIGNAL_ATTRIBUTE_NAME => "class";
use constant SIGNAL_ATTRIBUTE_VALUE => "CallsDetail";
use constant NUMBER_OF_COLUMNS => 9;
use constant MOMENT_COLUMN => 0;
use constant PHONE_COLUMN => 1;
use constant PLACE_COLUMN => 2;
use constant SERVICE_COLUMN => 3;
use constant TYPE_COLUMN => 5;
use constant TIME_COLUMN => 7;
use constant PRICE_COLUMN => 8;

#*****************************************************************************
sub do_body($$);
sub do_start_tag($$$);
sub do_stop_tag($$);
sub trim($);
sub create_sql_value($);
sub insert_gprs($$);
sub insert_call($$);
sub my_time($);

#*****************************************************************************
my $parser = HTML::Parser->new(api_version => 3,
                               xml_mode => 1,
                               start_h => [\&do_start_tag, "self, tagname, attr"],
			       end_h => [\&do_stop_tag, "self, tagname"],
			       text_h => [\&do_body, "self, dtext"]
			      );

my $field_text = "";
my @expenses = ();
my @fields = ();
my $calls_started = 0;

#*****************************************************************************
my $source = $ARGV[0];
if (!defined($source)) {
    die "Usage: $0 <source-file-name>\n";
}

[ -r $source ] || die "Can't open source file $source\n";

my $database = `cat @{[DATABASE_PROPERTIES]}`;
$database or die "Не могу найти базу данных.\n";

$parser->parse_file($source);

my $connection = Pg::connectdb("dbname=$database user=@{[USER]} password=@{[PASSWORD]}");

$connection->status == PGRES_CONNECTION_OK or
    die "Connection to database failed with message \"$connection->errorMessage\"";

foreach my $expense (sort {my_time($a->[MOMENT_COLUMN]) cmp my_time($b->[MOMENT_COLUMN])} @expenses) {
    $connection->exec("BEGIN");
    eval {
        if ($expense->[SERVICE_COLUMN] eq "GPRS") {
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
    my $moment = $call->[MOMENT_COLUMN];
    my $phone = $call->[PHONE_COLUMN];
    my $place = $call->[PLACE_COLUMN];
    my $ignore1 = $call->[SERVICE_COLUMN];
    my $type = $call->[TYPE_COLUMN];
    my $time = $call->[TIME_COLUMN];
    my $price = $call->[PRICE_COLUMN];
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
    if ($time =~ /^\d:/) {
        $time = "0$time";
    }
    if ($type =~ /sms/i) {
        $type = CALL_SMS;
    } elsif ($type =~ /телеф/i) {
        $type = CALL_PHONE;
    } elsif ($type =~ /emrg/i) {
        $type = CALL_SOS;
    } else {
        die "Unknown call type: $type (" . join("\t", @$call) . ")\n";
    }
    $moment = create_sql_value($moment);
    $phone = create_sql_value($phone);
    $place = create_sql_value(trim("$place $ignore1"));
    $time = create_sql_value($time);
    my $query = "insert into @{[CALLS_TABLE]} (moment, direction, phone, place, type, quantity, price, note)"
                . " values (to_timestamp($moment, 'dd/MM/yyyy HH24:MI'), $direction, $phone, $place, $type, to_timestamp($time, 'MI:SS'), $price, null)";
    my $result = $connection->exec($query);
    $result->resultStatus == PGRES_COMMAND_OK or die "Execution of query \"$query\" failed";
}

sub insert_gprs($$) {
    my ($connection, $gprs) = @_;
    my $moment = $gprs->[MOMENT_COLUMN];
    my $url = $gprs->[PHONE_COLUMN];
    my $traffic = $gprs->[TIME_COLUMN];
    my $price = $gprs->[PRICE_COLUMN];
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
    $traffic =~ s/кб//ig;
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
sub do_start_tag($$$) {
    my($self, $tagname, $attr) = @_;
    if ($tagname eq SIGNAL_TAG_NAME
        && defined($attr->{"@{[SIGNAL_ATTRIBUTE_NAME]}"})
	&& $attr->{"@{[SIGNAL_ATTRIBUTE_NAME]}"} eq SIGNAL_ATTRIBUTE_VALUE
	) {
	$calls_started = 1;
	return;
    }
    if (!$calls_started) {
        return;
    }
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
    if ($tagname eq SIGNAL_TAG_NAME) {
	$calls_started = 0;
	return;
    }
    if (!$calls_started) {
        return;
    }
    if($tagname eq "tr"  && $#fields >= NUMBER_OF_COLUMNS - 1) {
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
