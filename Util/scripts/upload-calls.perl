#! /usr/bin/perl -w
use locale;
use strict;
use HTML::Parser;
use Pg;
use constant MAIN_HTML => "../download/calls.html";
use constant DATABASE_PROPERTIES => "../database.properties";
use constant USER => "apacheagent";
use constant PASSWORD => "apache";
use constant CALLS_TABLE => "calls_buffer";
use constant CALL_INCOMING => 1;
use constant CALL_OUTGOING => 2;
use constant CALL_PHONE => 1;
use constant CALL_SMS => 2;
use constant CALL_SOS => 3;

#*****************************************************************************
sub do_body($$);
sub do_start_tag($$);
sub do_stop_tag($$);
sub trim($);
sub create_sql_value($);
sub insert_call($$);
sub my_time($);

#*****************************************************************************
my $parser = HTML::Parser->new(api_version => 3,
                               start_h => [\&do_start_tag, "self, tagname"],
			       end_h => [\&do_stop_tag, "self, tagname"],
			       text_h => [\&do_body, "self, dtext"]
			      );

my $field_text = "";
my @calls = ();
my @fields = ();

#*****************************************************************************
$parser->parse_file(MAIN_HTML);

my $database = `cat @{[DATABASE_PROPERTIES]}`;
$database or die "Не могу найти базу данных.\n";

my $connection = Pg::connectdb("dbname=$database user=@{[USER]} password=@{[PASSWORD]}");

$connection->status == PGRES_CONNECTION_OK or
    die "Connection to database failed with message \"$connection->errorMessage\"";

foreach my $call (sort {my_time($a->[1]) cmp my_time($b->[1])} @calls) {
    $connection->exec("BEGIN");
    eval {
        insert_call($connection, $call);
        $connection->exec("COMMIT");
    }; if($@) {
        my $errorMessage = $@;
        print STDERR "Error inserting record for $call->[0] $call->[1]:\n";
        print STDERR $errorMessage;
        print STDERR $connection->errorMessage, "\n";
        $connection->exec("ROLLBACK");
    };
}

#*****************************************************************************
sub insert_call($$) {
    my ($connection, $call) = @_;
    my ($call_local_id, $moment, $phone, $type, $time, $price, $note) = @$call;
    my $direction = CALL_OUTGOING;
    $phone =~ s/\+7/8/;
    if ($phone =~ s/^<--//) {
        $direction = CALL_INCOMING;
	if ($phone =~ /^\d{10}$/) {
	    $phone = "8$phone";
	}
    }
    $moment =~ /^(\d{2}\.\d{2}\.)(.*)$/;
    $moment = "${1}20${2}";
    $phone = create_sql_value($phone);
    $moment = create_sql_value($moment);
    $time = create_sql_value($time);
    $note = create_sql_value($note);
    if ($type =~ /sms/i) {
        $type = CALL_SMS;
    } elsif ($type =~ /телеф/i) {
        $type = CALL_PHONE;
    } elsif ($type =~ /emrg/i) {
        $type = CALL_SOS;
    } else {
        die "Unknown call type: $type (" . join("\t", @$call) . ")\n";
    }
    my $query = "insert into @{[CALLS_TABLE]} (moment, direction, phone, place, type, quantity, price, note)"
                . " values (to_timestamp($moment, 'dd.MM.yyyy HH24:MI:SS'), $direction, $phone, null, $type, $time, $price, $note)";
    my $result = $connection->exec($query);
    $result->resultStatus == PGRES_COMMAND_OK or die "Execution of query \"$query\" failed";
}

sub my_time($) {
    $_ = shift;
    my ($day, $month, $year, $hour, $minute, $second) = /^(\d\d)\.(\d\d)\.(\d\d) (\d\d):(\d\d):(\d\d)$/;
    if (!defined($year)) {
        ($day, $month, $year) = /^(\d\d)\.(\d\d)\.(\d\d)$/;
        ($hour, $minute, $second) = (0, 0, 0);
    }
    if ($year < 70) {
        $year += 2000;
    } else {
        $year += 1900;
    }
    return sprintf("%.4d-%.2d-%.2d %.2d:%.2d:%.2d", $year, $month, $day, $hour, $minute, $second);
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
        push(@calls, \@temp);
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
