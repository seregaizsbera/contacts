#! /usr/bin/perl
use strict;
use HTML::Parser;
use Pg;
use constant MAIN_HTML => "../download/Main.html";
use constant DB => "contacts";
use constant USER => "apacheagent";
use constant PASSWORD => "apache";
use constant PERSONS_TABLE => "persons";
use constant PHONES_TABLE => "phones";
use constant PERSON_PHONES_TABLE => "person_phones";
use constant EMAILS_TABLE => "emails";
use constant ADDRESSES_TABLE => "addresses";
use constant BIRTHDAYS_TABLE => "birthdays";
$^W = 1;

#*****************************************************************************
sub do_body($$);
sub do_start_tag($$);
sub do_stop_tag($$);
sub trim($);
sub create_sql_value($);
sub insert_person($@);
sub insert_phones($$@);
sub insert_birthday($$$);
sub insert_email($$$);
sub insert_address($$$);

#*****************************************************************************
my $parser = HTML::Parser->new(api_version => 3,
                               start_h => [\&do_start_tag, "self, tagname"],
			       end_h => [\&do_stop_tag, "self, tagname"],
			       text_h => [\&do_body, "self, dtext"]
			      );

my $field_text = "";
my %persons = ();
my @fields = ();

#*****************************************************************************
$parser->parse_file(MAIN_HTML);

my $connection = Pg::connectdb("dbname=@{[DB]} user=@{[USER]} password=@{[PASSWORD]}");

$connection->status == PGRES_CONNECTION_OK or
  die "Connection to database failed with message \"$connection->errorMessage\"";

while(my($key, $val) = each %persons) {
  my @val = @$val;
  my @phones = split("\n", $val[3]);
  my($second, $first, $last, $temp , $address, $note) = @val;
  $connection->exec("BEGIN");
  eval {
    my $person_id = insert_person($connection, $first, $second, $last, $note);
    insert_address($connection, $person_id, $address);
    insert_phones($connection, $person_id, @phones);
    insert_birthday($connection, $person_id, $val[6]);
    insert_email($connection, $person_id, $val[7]);
    $connection->exec("COMMIT");
  }; if($@) {
    my $errorMessage = $@;
    print STDERR "Error inserting record for $first $second:\n";
    print STDERR $errorMessage;
    print STDERR $connection->errorMessage, "\n";
    $connection->exec("ROLLBACK");
  };
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
    $field_text .= "\n" if($field_text ne "");
    return;
  }
}

sub do_stop_tag($$) {
  my($self, $tagname) = @_;
  if($tagname eq "tr") {
    my $key = shift @fields;
    my @temp = @fields;
    $persons{$key} = \@temp;
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
  $field_text .= $text
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
  $_[0] = $str;
  return $str;
}

sub insert_person($@) {
  my $connection = shift;
  my @row = @_;
  for(my $i = 0; $i <= $#row; $i++) {
    create_sql_value($row[$i]);
  }
  my($first, $second,  $last, $note) = @row;
  my $query = "insert into " . PERSONS_TABLE . " " .
              "(first, second, last, note) " .
              "values ($first, $second, $last, $note)";
  my $result = $connection->exec($query);
  $result->resultStatus == PGRES_COMMAND_OK or die "Execution of query \"$query\" failed";
  my $oid = $result->oidStatus();
  $query = "select id from " . PERSONS_TABLE . " where oid=$oid";
  $result = $connection->exec($query);
  $result->resultStatus == PGRES_TUPLES_OK or die "Execution of query \"$query\" failed";
  @row = $result->fetchrow();
  return $row[0];
}

sub insert_phones($$@) {
  my $connection = shift;
  my $person_id = shift;
  my @phones = @_;
  for(my $i = 0; $i <= $#phones; $i++) {
    create_sql_value($phones[$i]);
  }
  my $basic = "true";
  foreach my $phone (@phones) {
    my $query = "insert into @{[PHONES_TABLE]} (phone, type, basic)"
                . " values ($phone, 1, $basic)";
    my $result = $connection->exec($query);
    $result->resultStatus == PGRES_COMMAND_OK or die "Execution of query \"$query\" failed";
    my $oid = $result->oidStatus();
    
    $query = "select id from @{[PHONES_TABLE]} where oid=$oid";
    $result = $connection->exec($query);
    $result->resultStatus == PGRES_TUPLES_OK or die "Execution of query \"$query\" failed";
    my @row = $result->fetchrow();
    my $phone_id = $row[0];
    
    $query = "insert into @{[PERSON_PHONES_TABLE]} (person, phone)"
             . " values ($person_id, $phone_id)";
    $result = $connection->exec($query);
    $result->resultStatus == PGRES_COMMAND_OK or die "Execution of query \"$query\" failed";
    
    $basic = "false" if($basic eq "true");
  }
}

sub insert_birthday($$$) {
  my $connection = shift;
  my $person_id = shift;
  my $birthday = shift;
  return if(!defined($birthday) || $birthday eq "");
  my @birthdate = split('\.', $birthday);
  my @date = localtime();
  $birthdate[2] += ((($date[5] + 1900) % 100) < $birthdate[2]) ? 1900 : 2000;
  $birthday = join('.', @birthdate);
  create_sql_value($birthday);
  my $query = "insert into " . BIRTHDAYS_TABLE . " " .
              "(person, birthday) " .
              "values ($person_id, $birthday)";
  my $result = $connection->exec($query);
  $result->resultStatus == PGRES_COMMAND_OK or die "Execution of query \"$query\" failed";
}

sub insert_email($$$) {
  my $connection = shift;
  my $person_id = shift;
  my $email = shift;
  $email =~ s/^mailto://i;
  return if(!defined($email) || $email eq "");
  create_sql_value($email);
  my $query = "insert into " . EMAILS_TABLE . " " .
              "(person, email, basic) " .
              "values ($person_id, $email, true)";
  my $result = $connection->exec($query);
  $result->resultStatus == PGRES_COMMAND_OK or die "Execution of query \"$query\" failed";
}

sub insert_address($$$) {
  my $connection = shift;
  my $person_id = shift;
  my $address = shift;
  return if(!defined($address) || $address eq "");
  create_sql_value($address);
  my $query = "insert into @{[ADDRESSES_TABLE]}"
              . " (person, address) "
              . " values ($person_id, $address)";
  my $result = $connection->exec($query);
  $result->resultStatus == PGRES_COMMAND_OK or die "Execution of query \"$query\" failed";
}