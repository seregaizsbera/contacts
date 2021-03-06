#! /usr/bin/perl -w
use strict;

sub process_file($);
sub print_java_interface($$$$$);
sub print_sql($$$$$);
sub make_sql_value($$);

my $sql_table;
my $sql_file;
my $java_package;
my $java_interface;
my $comment;
my $need_atomic_values;
my @values;
my @additional_columns = ();
my @table_modifiers = ();

my @common_columns = (
    ["id", "int4", "NOT NULL PRIMARY KEY", "�������� ��������"],
    ["name", "text", "NOT NULL UNIQUE CHECK (name <> '')", "��������� ��������"]
);


if ($#ARGV < 0) {
    print STDERR "Usage: $0 cfg-file1 cfg-file2...\n";
    exit 1;
}

foreach my $file (@ARGV) {
    $need_atomic_values = undef;
    process_file($file);
}

sub process_file($) {
    my ($file) = @_;

    if (! -r $file) { 
        print STDERR "File $file doesn't exist or isn't readable.\n";
        return;
    }
    open(PROG, $file);
    my $prog;
    while (<PROG>) {
        $prog .= $_;
    }
    eval $prog;

    my @columns = (@common_columns, @additional_columns);

    open(OUT, "> $java_interface.java") or die;
    print_java_interface($sql_table, $java_package, $java_interface, $comment, \@values);
    close(OUT);

    open(OUT, "> $sql_file") or die;
    print_sql($sql_table, $comment, \@columns, \@table_modifiers, \@values);
    close(OUT);
}

################################################################################
sub print_sql($$$$$) {
    my ($table, $comment, $columns, $modifiers, $values) = @_;
    print OUT "-- Generated by nsi.perl script at " . gmtime() . "\n\n";
    print OUT <<EOF
DROP TABLE $table;

BEGIN;

CREATE TABLE $table (
EOF
    ;
    my $index = 0;
    foreach my $column (@$columns) {
        if ($index++) {
            print OUT ",\n";
        }
        print OUT "    $column->[0] $column->[1]";
        my $modifier = $column->[2];
        if ($modifier) {
            print OUT " $modifier";
        }
    }
    foreach my $modifier (@$modifiers) {
        if ($index++) {
            print OUT ",\n";
            print OUT "    $modifier";
        }
    }
    if ($index) {
        print OUT "\n";
    }
    print OUT ")";
    print OUT ";\n";
    print OUT <<EOF

COMMENT ON TABLE $table IS '$comment';

EOF
    ;
    foreach my $column (@$columns) {
        print OUT "COMMENT ON COLUMN $table.$column->[0] IS '$column->[3]';\n";
    }
print OUT <<EOF

REVOKE ALL ON $table FROM PUBLIC;
REVOKE ALL ON $table FROM j2eeagent;
REVOKE ALL ON $table FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON $table TO j2eeagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON $table TO apacheagent;

EOF
    ;
    my @fields = ();
    foreach my $column (@$columns) {
        push(@fields, $column->[0]);
    }
    my $fields_sig = join(", ", @fields);
    foreach my $value (@$values) {
        my @the_value = @$value;
        shift(@the_value);
        for (my $i = 0; $i <= $#$columns; $i++) {
            $the_value[$i] = make_sql_value($columns->[$i]->[1], $the_value[$i]);
        }
        my $the_values = join(", ", @the_value);
        print OUT "INSERT INTO $table ($fields_sig) VALUES ($the_values);\n";
    }
    print OUT <<EOF

COMMIT;
EOF
}

################################################################################
sub print_java_interface($$$$$) {
    my ($table, $package, $interface, $comment, $values) = @_;
    my $time = gmtime();
    print OUT <<EOF
/*
 * Generated by nsi.perl at $time
 */
package $package;

/**
 * $interface - $comment
 * ��������, ������������ � ���� ���������� ������� � ������� $table
 */
public interface $interface {
EOF
    ;
    my $index = 0;
    foreach my $value (@$values) {
        if ($index++) {
            print OUT "\n";
        }
        print OUT "    /**\n     * $value->[2]\n     */\n";
	if ($need_atomic_values) {
	    print OUT "    int $value->[0] = $value->[1];\n";
	} else {
            print OUT "    Integer $value->[0] = new Integer($value->[1]);\n";
	}
    }
    print OUT "}\n";
}

sub make_sql_value($$) {
    my ($type, $value) = @_;
    if (!defined($value)) {
        return "null";
    }
    if ($type =~ /int/i) {
        return $value;
    }
    return "'$value'";
}
