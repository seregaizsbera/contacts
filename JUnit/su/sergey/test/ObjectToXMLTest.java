package su.sergey.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import junit.framework.TestCase;
import su.sergey.beans.Bean1;
import su.sergey.beans.Bean2;
import su.sergey.contacts.dao.ShnipDAO;
import su.sergey.contacts.dto.ShnipData;
import su.sergey.contacts.dto.ShnipHandle;
import su.sergey.contacts.util.dao.PGConnectionSource;
import su.sergey.contacts.util.xml.ObjectToXmlConverter;
import su.sergey.contacts.util.xml.XMLItem;
import su.sergey.contacts.util.xml.XmlToObjectConverter;

public class ObjectToXMLTest extends TestCase {
	private Properties properties;

	/**
	 * Constructor for XMLToObjectTest
	 */
	public ObjectToXMLTest(String name) {
		super(name);
	}
	
	public void test1() throws IOException {
        ObjectToXmlConverter converter = new ObjectToXmlConverter();
		ShnipData data0 = new ShnipDAO(new PGConnectionSource(properties)).find(new ShnipHandle(new Integer(264)));
		String xml1 = XMLItem.render(converter.makeXMLRecord("shnip", data0).makeDocument(), "UTF-8", true);
		ShnipData data1 = (ShnipData) new XmlToObjectConverter().makeObject(ShnipData.class, xml1);
		String xml2 = XMLItem.render(converter.makeXMLRecord("shnip", data1).makeDocument(), "UTF-8", true);
		System.err.println(xml1);
		assertEquals(xml1, xml2);
	}
	
	public void test2() throws IOException {
		Bean1 bean1 = new Bean1();
		Bean2 bean2 = new Bean2();
		bean2.setA(1.7);
		bean2.setB(1.8);
		bean1.setA(17);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 2);
		bean1.setBirthDate(new Date[] { new Date(),  calendar.getTime() });
		bean1.setC(bean2);
		bean1.setD("");
        ObjectToXmlConverter converter = new ObjectToXmlConverter();
		String xml1 = XMLItem.render(converter.makeXMLRecord("theBean", bean1).makeDocument(), "UTF-8", true);
		Bean1 bean11 = (Bean1) new XmlToObjectConverter().makeObject(Bean1.class, xml1);
		String xml2 = XMLItem.render(converter.makeXMLRecord("theBean", bean11).makeDocument(), "UTF-8", true);
		System.err.println(xml1);
		assertEquals(xml1, xml2);
	}
	
	/**
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		properties = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("test.properties");
		properties.load(input);
	}

	/**
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		properties = null;
	}
}
