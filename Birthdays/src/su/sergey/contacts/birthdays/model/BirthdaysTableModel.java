package su.sergey.contacts.birthdays.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.person.valueobjects.PersonAttributes;

/*
 * Created by IntelliJ IDEA.
 * User: sergey
 * Date: 06.12.2003
 * Time: 12:52:18
 * To change template for new class use
 * Code Style | Class Templates options (Tools | IDE Options).
 */
public class BirthdaysTableModel extends AbstractTableModel {
    private static String columns[] = {"Фамилия",
                                "Имя",
                                "День рождения",
                                "Год рождения",
                                "Телефон",
                                "Эл.почта"
    };

    private Person2 persons[];
    private DateFormat birthdayFormat;
    private DateFormat birthyearFormat;

    public void setPersons(Person2 persons[]) {
        this.persons = persons;
        birthdayFormat = new SimpleDateFormat("d MMMM");
        birthyearFormat = new SimpleDateFormat("yyyy");
    }

    public BirthdaysTableModel() {
    }

    public Object getValueAt(int row, int column) {
        if (persons == null) {
            return null;
        }
        PersonAttributes attributes = persons[row].getAttributes();
        switch (column) {
            case 0:
                return attributes.getLastName();
            case 1:
                return attributes.getFirstName();
            case 2:
                return birthdayFormat.format(attributes.getBirthday());
            case 3:
                return birthyearFormat.format(attributes.getBirthYear());
            case 4:
                return attributes.getBasicPhone() != null ? attributes.getBasicPhone().getPhone() : null;
            case 5:
                return attributes.getBasicEmail() != null ? attributes.getBasicEmail().getEmail() : null;
            default:
                return null;
        }
    }

    public int getColumnCount() {
        return 6;
    }

    public int getRowCount() {
        if (persons == null) {
            return 0;
        }
        return persons.length;
    }

    public String getColumnName(int column) {
        return columns[column];
    }

    public Class getColumnClass(int i) {
        return String.class;
    }
}
