package su.sergey.contacts.birthdays.model;

import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import su.sergey.contacts.JNDINames;
import su.sergey.contacts.util.ContactsDateTimeFormat;
import su.sergey.contacts.person.businessdelegate.PersonPageIteratorBusinessDelegate;
import su.sergey.contacts.person.businessdelegate.impl.DefaultPersonPageIteratorBusinessDelegate;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.person.valueobjects.Person2;

public class BirthdaysContent {
    private final int PAGE_SIZE = 10;
    private PersonSearchParameters searchParameters;
    private PersonPageIteratorBusinessDelegate iterator;
    private final Calendar calendar;

    private Date clearYear(Date date) {
        calendar.setTime(date);
        calendar.clear(Calendar.YEAR);
        Date result = calendar.getTime();
        return result;
    }

    /**
     * Constructor for BirthdaysContent
     */
    public BirthdaysContent(DateBounds bounds) {
        calendar = Calendar.getInstance();
        searchParameters = new PersonSearchParameters();
        searchParameters.setAfterBirthdayDay(clearYear(bounds.getDate1()));
        searchParameters.setBeforeBirthdayDay(clearYear(bounds.getDate2()));
        searchParameters.setFullData(true);
        iterator = new DefaultPersonPageIteratorBusinessDelegate(JNDINames.PERSON_PAGE_ITERATOR_BEAN, searchParameters, PAGE_SIZE);
    }

    public Person2[] next() {
        return iterator.next();
    }

    /**
     * @see PersonPageIteratorBusinessDelegate#prev()
     */
    public Person2[] prev() {
        return iterator.prev();
    }

    /**
     * @see PersonPageIteratorBusinessDelegate#current()
     */
    public Person2[] current() {
        return iterator.current();
    }

    /**
     * @see PersonPageIteratorBusinessDelegate#goTo(int)
     */
    public Person2[] goTo(int page) {
        return iterator.goTo(page);
    }

    /**
     * @see PageIteratorBusinessDelegate#hasNext()
     */
    public boolean hasNext() {
        return iterator.hasNext();
    }

    /**
     * @see PageIteratorBusinessDelegate#hasPrev()
     */
    public boolean hasPrev() {
        return iterator.hasPrev();
    }

    /**
     * @see PageIteratorBusinessDelegate#getCurrentPage()
     */
    public int getCurrentPage() {
        return iterator.getCurrentPage();
    }

    /**
     * @see PageIteratorBusinessDelegate#getPageSize()
     */
    public int getPageSize() {
        return iterator.getPageSize();
    }

    /**
     * @see PageIteratorBusinessDelegate#getNumberOfPages()
     */
    public int getNumberOfPages() {
        return iterator.getNumberOfPages();
    }

    protected void finalize() throws Throwable {
        iterator.freeResources();
    }
}
