#! /usr/bin/perl -w
use strict;
use constant BACKUP_PATH => "/home/archives/contacts";

sub backup($$);
sub restore($$$);
sub list_backups($);

my $database;
my $mode;
my $day_str;

($mode, $database, $day_str) = @ARGV;

$mode or die "Working mode not specified.\n";

umask(077);

if ($mode eq "backup") {
    $database or die "Database name not specified.\n";
    backup(BACKUP_PATH, $database);
} elsif ($mode eq "restore") {
    $database or die "Database name not specified.\n";
    $day_str or die "Date of the backup not specified.\n";
    restore(BACKUP_PATH, $database, $day_str);
} elsif ($mode eq "list") {
    list_backups(BACKUP_PATH);
} else {
    die "Invalid working mode specified.\n";
}


sub backup($$) {
    my ($path, $dbname) = @_;
    my ($a, $b, $c, $day, $month, $year) = localtime();
    $year = sprintf("%04d", $year + 1900);
    $month = sprintf("%02d", $month + 1);
    $day = sprintf("%02d", $day);
    my $output_file = "$path/contacts-backup-$year-$month-$day.gz";
    open(OUT, ">", "$output_file") or die "Can't open file $output_file.\n";
    open(PG_DUMP, "-|", "pg_dump -c $dbname | gzip") or die "Can't backup database $dbname.\n";
    while (<PG_DUMP>) {
        print OUT;
    }
    close(OUT);
    close(PG_DUMP);
}

sub restore($$$) {
    my ($path, $dbname, $backup_day) = @_;
    my ($day, $month, $year) = split(/\./, $backup_day);
    my $input_file = "$path/contacts-backup-$year-$month-$day.gz";
    [ -r $input_file ] or die "Can't open file $input_file.\n";
    system("createdb $dbname") == 0 or die "Database $dbname can't be created.\n";
    open(GUNZIP, "-|", "gzip -cd $input_file | grep -Eivw ^DROP") or die "Can't open database backup $input_file.\n";
    open(PSQL, "|-", "psql -q $dbname") or die "Can't open database $dbname.\n";
    while (<GUNZIP>) {
        print PSQL;
    }
    close(PSQL);
    close(GUNZIP);
}

sub list_backups($) {
    my $path= shift;
    my @files = glob("$path/contacts-backup-????-??-??.gz");
    foreach my $file (@files) {
        $file =~ m|backup-(\d{4})-(\d\d)-(\d\d)|;
        my ($year, $month, $day) = ($1, $2, $3);
        print "$day.$month.$year\n";
    }
}
