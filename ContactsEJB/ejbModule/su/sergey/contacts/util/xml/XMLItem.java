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
 * Представляет один XML тег. Один XML тег состоит из тегов начала и конца
 * (последний необязателен) и Map необязательных аттрибутов. Если данный тег
 * имеет дочерние теги, тогда присутствует список Collection дочерних объектов
 * XMLItem. Метод render() преобразует данные объекта XMLItem к виду XML строки.
 *
 * @author: Сергей Богданов
 */
public class XMLItem {
    /**
     * Тег
     */
    private String tag;

    /**
     * Аттрибуты данного тега (необязательные)
     */
    private Map attributes;

    /**
     * Коллекция дочерних(вложенных) тегов (необязательная)
     */
    private Collection children;

    /**
     * Значение тега (необязательное)
     */
    private String value;

    /**
     * Универсальный конструктор
     */
    public XMLItem(String tag) {
        this.tag = tag;
        this.attributes = new HashMap();
        this.children = new ArrayList();
    }

    /**
     * Конструктор, создающий XMLItem вместе с вложенными тегами
     */
    public XMLItem(String tag, Collection children) {
        this(tag);
        addChildren(children);
    }

    /**
     * Конструктор, создающий XMLItem вместе со значением
     */
    public XMLItem(String tag, String value) {
        this(tag);
        this.value = value;
    }

    /**
     * Добавить attributes к уже имеющимся. Создает Map аттрибутов при
     * необходимости
     */
    public void addAttributes(Map attributes) {
        this.attributes.putAll(attributes);
    }

    /**
     * Добавить children к уже имеющимся. Создает новую коллекцию при
     * необходимости
     */
    public void addChildren(Collection children) {
        if (value != null) {
            throw new IllegalStateException("Не могу добавить дочерние элементы, поскольку уже установлено текстовое значение.");
        }
        this.children.addAll(children);
    }

    /**
     * Добавить один атрибут
     */
    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    /**
     * Добавить один дочерний тег
     */
    public void addChild(XMLItem item) {
        if(item == null) {
            return;
        }
        if (value != null) {
            throw new IllegalStateException("Не могу добавить дочерние элементы, поскольку уже установлено текстовое значение.");
        }
        children.add(item);
    }

    public void setValue(String value) {
        if (children != null && !children.isEmpty()) {
            throw new IllegalStateException("Не могу установить текстовое значение, поскольку уже добавлены дочерние элементы.");
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
