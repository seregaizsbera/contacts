#! /usr/bin/perl -w
use locale;
use strict;
use HTML::Parser;
use Pg;
use constant MAIN_HTML => "../download/gprs.html";
use constant DATABASE_PROPERTIES => "../../Z_Buffer/database.properties";
use constant USER => "apacheagent";
use constant PASSWORD => "apache";
use constant GPRS_TABLE => "gprs_buffer";
use constant CALL_INCOMING => 1;
use constant CALL_OUTGOING => 2;
use constant URL_INTERNET => 1;
use constant URL_WAP => 2;

#*****************************************************************************
sub do_body($$);
sub do_start_tag($$);
sub do_stop_tag($$);
sub trim($);
sub create_sql_value($);
sub insert_gprs($$);
sub my_time($);

#*****************************************************************************
my $parser = HTML::Parser->new(api_version => 3,
                               start_h => [\&do_start_tag, "self, tagname"],
			       end_h => [\&do_stop_tag, "self, tagname"],
			       text_h => [\&do_body, "self, dtext"]
			      );

my $field_text = "";
my @gprs_cases = ();
my @fields = ();

#*****************************************************************************
$parser->parse_file(MAIN_HTML);

my $database = `cat @{[DATABASE_PROPERTIES]}`;
$database or die "Не могу найти базу данных.\n";

my $connection = Pg::connectdb("dbname=$database user=@{[USER]} password=@{[PASSWORD]}");

$connection->status == PGRES_CONNECTION_OK or
    die "Connection to database failed with message \"$connection->errorMessage\"";

foreach my $gprs (sort {my_time($a->[1]) cmp my_time($b->[1])} @gprs_cases) {
    $connection->exec("BEGIN");
    eval {
        insert_gprs($connection, $gprs);
        $connection->exec("COMMIT");
    }; if($@) {
        my $errorMessage = $@;
        print STDERR "Error inserting record for $gprs->[0] $gprs->[1]:\n";
        print STDERR $errorMessage;
        print STDERR $connection->errorMessage, "\n";
        $connection->exec("ROLLBACK");
    };
}

#*****************************************************************************
sub insert_gprs($$) {
    my ($connection, $gprs) = @_;
    #print join("|", @$gprs), "\n";
    my ($gprs_local_id, $moment, $url, $type_str, $traffic_str, $price, $traffic) = @$gprs;
    my $note;
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
    $moment =~ /^(\d{2}\.\d{2}\.)(.*)$/;
    $moment = "${1}20${2}";
    $moment = create_sql_value($moment);
    $traffic = $traffic * 1024;
    $note = create_sql_value($note);
    my $query = "insert into @{[GPRS_TABLE]} (moment, direction, url, traffic, price, note)"
                . " values (to_timestamp($moment, 'dd.MM.yyyy HH24:MI:SS'), $direction, $url, $traffic, $price, $note)";
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
        push(@gprs_cases, \@temp);
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
