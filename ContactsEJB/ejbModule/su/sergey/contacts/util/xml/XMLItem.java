package su.sergey.contacts.util.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * ������������ ���� XML ���. ���� XML ��� ������� �� ����� ������ � �����
 * (��������� ������������) � Map �������������� ����������. ���� ������ ���
 * ����� �������� ����, ����� ������������ ������ Collection �������� ��������
 * XMLItem. ����� render() ����������� ������ ������� XMLItem � ���� XML ������.
 *
 * @author: ������ ��������
 */
public class XMLItem {
    /**
     * ���
     */
    private String tag;

    /**
     * ��������� ������� ���� (��������������)
     */
    private Map attributes;

    /**
     * ��������� ��������(���������) ����� (��������������)
     */
    private Collection children;

    /**
     * �������� ���� (��������������)
     */
    private String value;

    /**
     * ������������� �����������
     */
    public XMLItem(String tag) {
        this.tag = tag;
        this.attributes = new HashMap();
        this.children = new ArrayList();
    }

    /**
     * �����������, ��������� XMLItem ������ � ���������� ������
     */
    public XMLItem(String tag, Collection children) {
        this(tag);
        addChildren(children);
    }

    /**
     * �����������, ��������� XMLItem ������ �� ���������
     */
    public XMLItem(String tag, String value) {
        this(tag);
        this.value = value;
    }

    /**
     * �������� attributes � ��� ���������. ������� Map ���������� ���
     * �������������
     */
    public void addAttributes(Map attributes) {
        this.attributes.putAll(attributes);
    }

    /**
     * �������� children � ��� ���������. ������� ����� ��������� ���
     * �������������
     */
    public void addChildren(Collection children) {
        if (value != null) {
            throw new IllegalStateException("�� ���� �������� �������� ��������, ��������� ��� ����������� ��������� ��������.");
        }
        this.children.addAll(children);
    }

    /**
     * �������� ���� �������
     */
    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    /**
     * �������� ���� �������� ���
     */
    public void addChild(XMLItem item) {
        if(item == null) {
            return;
        }
        if (value != null) {
            throw new IllegalStateException("�� ���� �������� �������� ��������, ��������� ��� ����������� ��������� ��������.");
        }
        children.add(item);
    }

    public void setValue(String value) {
        if (children != null && !children.isEmpty()) {
            throw new IllegalStateException("�� ���� ���������� ��������� ��������, ��������� ��� ��������� �������� ��������.");
        }
        this.value = value;
    }

    public Document makeDocument() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            makeTree(document, document);
            return document;
        } catch(ParserConfigurationException e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage());
        }
    }

    public static void render(OutputStream output, Document document, String encoding, boolean indenting) throws IOException {
        XMLSerializer serializer = new XMLSerializer(output, new OutputFormat((String)null, encoding, indenting));
        serializer.serialize(document);
    }

    public static String render(Document document, String encoding, boolean indenting) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        render(output, document, encoding, indenting);
        String result = output.toString(encoding);
        output.close();
        return result;
    }

    protected void makeTree(Document document, Node parent) {
        Element element = document.createElement(tag);
        parent.appendChild(element);
        for(Iterator i = attributes.keySet().iterator(); i.hasNext();) {
            String key = (String) i.next();
            element.setAttribute(key, (String) attributes.get(key));
        }
        if(value != null) {
            element.appendChild(document.createTextNode(value));
        } else {
            for(Iterator i = children.iterator(); i.hasNext();) {
                XMLItem item = (XMLItem)i.next();
                item.makeTree(document, element);
            }
        }
    }
}
