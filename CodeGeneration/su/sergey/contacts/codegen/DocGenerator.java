package su.sergey.contacts.codegen;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.PGParser;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.util.Environment;


/**
 * DocGenerator
 * 
 * @author: Сергей Богданов
 */
public class DocGenerator implements TableListener {
    private static final String HEADER_COLUMNS[] = {"No.", "Название", "Тип", "Параметры", "Комментарий"};
    private StringBuffer result;
    
    public static void main(String args[]) {
        try {
            Properties properties = new Properties();
            ClassLoader loader = DocGenerator.class.getClassLoader();
            InputStream input = loader.getResourceAsStream("database.properties");
            if (input != null) {
            	properties.load(input);
            }
            String schemaPattern = properties.getProperty("schemas");
            String tablePattern = properties.getProperty("tables");
            String database = properties.getProperty("database");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String fileName = Environment.getDocumentOutputPath();
            DocGenerator docGenerator = new DocGenerator();
            PGParser parser = new PGParser(database, user, password, docGenerator);
            parser.start(schemaPattern, tablePattern);
            String tables = docGenerator.getTables();
            makeDocs(database, tables, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DocGenerator() {
        result = new StringBuffer();
    }
    
    public String getTables() {
    	return result.toString();
    }

    private static void makeDocs(String database, String tables, String fileName) throws IOException {
		StringBuffer output = new StringBuffer();
	    output.append(makeHtmlStart(database));
	    output.append(tables);
	    output.append(makeHtmlEnd());
        saveFile(output, fileName);
    }
    
    private static void saveFile(StringBuffer output, String fileName) throws IOException {
        File file = new File(fileName);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
        try {
            stream.write(output.toString().getBytes(Environment.getDocumentEncoding()));
        } finally {
            stream.close();
        }
    }

    private static String makeHtmlStart(String databaseName) {
    	StringBuffer header = new StringBuffer();
    	header.append("<html>\n <head>\n");
    	header.append("  <title>База данных ").append(databaseName).append("</title>\n");
    	header.append("  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=").append(Environment.getDocumentEncoding()).append("\">\n");
    	header.append(" </head>\n <body>\n");
    	String result = header.toString();
    	return result;
    }
    
    private static String makeHtmlEnd() {
    	return "</body></html>\n";
    }
    
    private void makeHtmlTableRow(Attribute attribute) {
        String columns[] = new String[HEADER_COLUMNS.length];
        int columnNumber = attribute.getColumnNumber();
        String remarks = attribute.getRemarks();
        int columnsIndex = 0;
        columns[columnsIndex++] = columnNumber + ".";
        columns[columnsIndex++] = attribute.getColumnName();
        columns[columnsIndex++] = attribute.getType();
        columns[columnsIndex++] = makeColumnTypeDescription(attribute);
        columns[columnsIndex++] = (remarks == null) ? "&nbsp;" : remarks;
        result.append("<tr>\n");
        for (int i = 0; i < columns.length; i++) {
            result.append("  <td>" + columns[i] + "</td>\n");
        }
        result.append("</tr>\n");
    }

    private static String makeColumnTypeDescription(Attribute attribute) {
    	String type = attribute.getType();
    	int scale = attribute.getScale();
        String result = "";
        if (type.equalsIgnoreCase("numeric")
                || type.equalsIgnoreCase("decimal")
                || type.toLowerCase().endsWith("char")) {
            result += "(" + attribute.getLength();
            if (scale != 0) {
                result += ", " + scale;
            }
            result += ")";
        }
        result += "&nbsp;";
        if (!attribute.isNulls()) {
            result += "NOT NULL";
        }
        if (attribute.getKeyseq() != 0) {
            result += ", PK";
        }
        return result;
    }

    private void makeHtmlTableStart(Table table) {
    	result.append("<p><b>");
    	result.append(table.getQualifiedName());
    	result.append("</b>");
		String remarks = table.getRemarks();
		if (remarks != null) {
			result.append(" - ");
		    result.append(remarks);
		}
    	result.append("</p>\n");
        result.append("<table border=1 cellspacing=0 cellpadding=5>\n");
        result.append("<tr>\n");
        for (int i = 0; i < HEADER_COLUMNS.length; i++) {
            result.append("  <th>" + HEADER_COLUMNS[i] + "</th>\n");
        }
        result.append("</tr>\n");
    }
    
    private void makeHtmlTableEnd() {
        result.append("</table>\n");
    }
    
	/**
	 * @see TableListener#startTable(Table)
	 */
	public void startTable(Table table) {
		makeHtmlTableStart(table);
	}

	/**
	 * @see TableListener#attribute(Attribute)
	 */
	public void attribute(Attribute attribute) {
		makeHtmlTableRow(attribute);
	}

	/**
	 * @see TableListener#endTable()
	 */
	public void endTable() {
		makeHtmlTableEnd();
	}
}
