package su.sergey.contacts.codegen.daogen;

import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.codegen.util.HelperFactory;
import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.db.TypeListener;
import su.sergey.contacts.codegen.util.*;


/**
 * PopulateMethodGenerator
 * 
 * @author Сергей Богданов
 */
public class PopulateMethodGenerator implements TableListener {
    private static final String PREFIX = "        ";
    private StringBuffer method;
    private TypeListener typeListener;
    private String dtoPackage;

    public PopulateMethodGenerator(TypeListener typeListener, String dtoPackage) {
    	this.typeListener = typeListener;
    	this.dtoPackage = dtoPackage;
        method = new StringBuffer();
    }

    public void startTable(Table table) {
        method.delete(0, method.length());
        String data = typeListener.type(dtoPackage + "." + HelperFactory.getHelper().getDataClassName(table));
        String resultSet = typeListener.type(ResultSet.class);
        String sqlException = typeListener.type(SQLException.class);
        method.append("    public int populate(").append(data).append(" value, ").append(resultSet).append(" rs, int startIndex)");
        method.append(" throws ").append(sqlException).append(" {\n");
        method.append("        int index = startIndex;\n");
    }

    public void attribute(Attribute attribute) {
        method.append(PREFIX).append("value.set").append(HelperFactory.getHelper().getAttributeName(attribute)).append("(").append(HelperFactory.getHelper().getGetMethod(attribute)).append(");\n");
    }

    public void endTable() {
        method.append("        return index;\n");
        method.append("    }\n");
    }

    public String getMethod() {
        return method.toString();
    }
}
