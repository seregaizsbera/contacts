#! /usr/bin/perl
use strict;
use HTML::Parser;
use Pg;
use constant MAIN_HTML => "../download/Main.html";
use constant DATABASE_PROPERTIES => "../database.properties";
use constant USER => "apacheagent";
use constant PASSWORD => "apache";
use constant PERSONS_TABLE => "persons";
use constant PHONES_TABLE => "phones";
use constant PERSON_PHONES_TABLE => "person_phones";
use constant EMAILS_TABLE => "emails";
use constant PERSON_EMAILS_TABLE => "person_emails";
use constant ADDRESSES_TABLE => "addresses";
use constant BIRTHDAYS_TABLE => "birthdays";
use constant GENDER_MALE => 0;
use constant GENDER_FEMALE => 1;
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
sub get_id($$$);

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

my $database = `cat @{[DATABASE_PROPERTIES]}`;
$database or die "Ну могу найти базу данных.\n";

my $connection = Pg::connectdb("dbname=$database user=@{[USER]} password=@{[PASSWORD]}");

$connection->status == PGRES_CONNECTION_OK or
  die "Connection to database failed with message \"$connection->errorMessage\"";

while(my($key, $val) = each %persons) {
  my @val = @$val;
  my @phones = split("\n", $val[3]);
  my($last, $first, $middle, $temp , $address, $note) = @val;
  $connection->exec("BEGIN");
  eval {
    my $person_id = insert_person($connection, $first, $middle, $last, $note);
    insert_address($connection, $person_id, $address);
    insert_phones($connection, $person_id, @phones);
    insert_birthday($connection, $person_id, $val[6]);
    insert_email($connection, $person_id, $val[7]);
    $connection->exec("COMMIT");
  }; if($@) {
    my $errorMessage = $@;
    print STDERR "Error inserting record for $first $last:\n";
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
    my($first, $middle,  $last, $note) = @row;
    my $gender = GENDER_MALE;
    if ($first =~ /[аяь]\'$/ && $last !~ /ич\'$/) {
        $gender = GENDER_FEMALE;
    }
    my $query = "insert into @{[PERSONS_TABLE]} (first, middle, last, gender, note)"
                . " values ($first, $middle, $last, $gender, $note)";
    my $result = $connection->exec($query);
    $result->resultStatus == PGRES_COMMAND_OK or die "Execution of query \"$query\" failed";
    my $person_id = get_id($connection, $result, PERSONS_TABLE);
    return $person_id;
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
    my $query = "insert into @{[PHONES_TABLE]} (phone, type)"
                . " values ($phone, 1)";
    my $result = $connection->exec($query);
    $result->resultStatus == PGRES_COMMAND_OK or die "Execution of query \"$query\" failed";
    my $phone_id = get_id($connection, $result, PHONES_TABLE);
    
    $query = "insert into @{[PERSON_PHONES_TABLE]} (person, phone, basic)"
             . " values ($person_id, $phone_id, $basic)";
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
  my $query = "insert into @{[BIRTHDAYS_TABLE]} (person, birthday)"
              . " values ($person_id, to_date($birthday, 'dd.MM.yyyy'))";
  my $result = $connection->exec($query);
  $result->resultStatus == PGRES_COMMAND_OK or die "Execution of query \"$query\" failed";
}

sub get_id($$$) {
    my $connection = shift;
    my $sql_result = shift;
    my $table = shift;
    my $oid = $sql_result->oidStatus();
    my $query = "select id from $table where oid=$oid";
    my $result = $connection->exec($query);
    $result->resultStatus == PGRES_TUPLES_OK or die "Execution of query \"$query\" failed";
    my @row = $result->fetchrow();
    my $id = $row[0];
    return $id;
}

sub insert_email($$$) {
  my $connection = shift;
  my $person_id = shift;
  my $email = shift;
  $email =~ s/^mailto://i;
  return if(!defined($email) || $email eq "");
  create_sql_value($email);
  my $query = "insert into @{[EMAILS_TABLE]} (email)"
              . " values ($email)";
  my $result = $connection->exec($query);
  $result->resultStatus == PGRES_COMMAND_OK or die "Execution of query \"$query\" failed";
  my $email_id = get_id($connection, $result, EMAILS_TABLE);
  my $query = "insert into @{[PERSON_EMAILS_TABLE]} (person, email, basic)"
              . " values ($person_id, $email_id, true)";
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
