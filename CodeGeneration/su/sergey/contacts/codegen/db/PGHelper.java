package su.sergey.contacts.codegen.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import su.sergey.contacts.codegen.util.Environment;

/**
 * Helper
 * 
 * @author Сергей Богданов
 */
public class PGHelper implements Helper {
    private final Map classNames;
    
    public PGHelper() {
    	Properties properties = new Properties();
    	ClassLoader classLoader = PGHelper.class.getClassLoader();
    	InputStream input = classLoader.getResourceAsStream("tables.properties");
    	if (input != null) {
    		try {
        		properties.load(input);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	classNames = Collections.unmodifiableMap(properties);
    }

    public boolean isForUpdate(Attribute attribute) {
    	boolean result = attribute.getKeyseq() == 0;
    	result &= !attribute.isGenerated();
    	result &= !attribute.isIdentity();
    	return result;
    }
    
    public String getSetMethod(Attribute attribute) {
        String type = getJavaType(attribute);
        String attributeType = attribute.getType();
        if (type.equals("java.lang.Integer")) {
            return "setInt(pstmt, index++, ";
        } else if (type.equals("java.math.BigDecimal")) {
            return "setBigDecimal(pstmt, index++, ";
        } else if (type.equals("java.util.Date")) {
        	if (attributeType.equalsIgnoreCase("date")) {
	            return "setDate(pstmt, index++, ";
        	} else if (attributeType.equalsIgnoreCase("time")) {
        		return "setTime(pstmt, index++, ";
        	} else if (attributeType.equalsIgnoreCase("timestamp")) {
                return "setTimestamp(pstmt, index++, ";
        	} else {
        		throw new IllegalArgumentException(">>> " + attributeType);
        	}
        } else if (type.equals("java.lang.Float")) {
            return "setFloat(pstmt, index++, ";
        } else if (type.equals("java.lang.Double")) {
            return "setDouble(pstmt, index++, ";
        } else if (type.equals(Environment.getCurrencyClass())) {
            return "setCurrency(pstmt, index++, ";
        } else if (type.equals("java.lang.Long")) {
            return "setLong(pstmt, index++, ";
        } else if (type.equals("java.lang.Boolean")) {
            return "setBoolean(pstmt, index++, ";
        } else if (type.equals("java.lang.String")) {
            return "setString(pstmt, index++, ";
        } else {
            throw new IllegalArgumentException(">>> " + type);
        }
    }

    public String getGetMethod(Attribute attribute) {
        String type = getJavaType(attribute);
        String attributeType = attribute.getType();
        if (type.equals("java.lang.Integer")) {
            return "getInt(rs, index++)";
        } else if (type.equals("java.math.BigDecimal")) {
            return "getBigDecimal(rs, index++)";
        } else if (type.equals(Environment.getCurrencyClass())) {
            return "getCurrency(rs, index++)";
        } else if (type.equals("java.util.Date")) {
	        if (attributeType.equalsIgnoreCase("date")) {
	            return "getDate(rs, index++)";
        	} else if (attributeType.equalsIgnoreCase("time")) {
	            return "getTime(rs, index++)";
	        } else if (attributeType.equalsIgnoreCase("timestamp")) {
	            return "getTimestamp(rs, index++)";
        	} else {
        		throw new IllegalArgumentException(">>> " + attributeType);
        	}
        } else if (type.equals("java.lang.Float")) {
            return "getFloat(rs, index++)";
        } else if (type.equals("java.lang.Double")) {
            return "setDouble(pstmt, index++)";
        } else if (type.equals("java.lang.Boolean")) {
            return "getBoolean(rs, index++)";
        } else if (type.equals("java.lang.Long")) {
            return "getLong(rs, index++)";
        } else if (type.equals("java.lang.String")) {
            return "getString(rs, index++)";
        } else {
            throw new IllegalArgumentException(">>> " + type);
        }
    }

    public String getJavaType(Attribute attribute) {
    	String attributeType = attribute.getType();
        if (attributeType.equalsIgnoreCase("integer")
                || attributeType.equalsIgnoreCase("int4")
                || attributeType.equalsIgnoreCase("serial")
                || attributeType.equalsIgnoreCase("oid")) {
            return "java.lang.Integer";
        } else if (attributeType.equalsIgnoreCase("int8")) {
            return "java.lang.Long";
        } else if (attributeType.equalsIgnoreCase("text")
                   || attributeType.equalsIgnoreCase("varchar")) {
            return "java.lang.String";
        } else if (attributeType.equalsIgnoreCase("date")
                   || attributeType.equalsIgnoreCase("time")
                   || attributeType.equalsIgnoreCase("timestamp")) {
            return "java.util.Date";
        } else if (attributeType.equalsIgnoreCase("decimal")) {
            return "java.math.BigDecimal";
        } else if (attributeType.equalsIgnoreCase("money")
                   || attributeType.equalsIgnoreCase("numeric")) {
            return Environment.getCurrencyClass();
        } else if (attributeType.equalsIgnoreCase("float4")) {
            return "java.lang.Float";
        } else if (attributeType.equalsIgnoreCase("float8")) {
            return "java.lang.Double";
        } else if (attribute.getType().equalsIgnoreCase("bool")) {
            return "java.lang.Boolean";
        } else {
            throw new IllegalArgumentException("Unknown type >>> " + attribute.getType());
        }
    }

    public String getDataClassName(Table table) {
        return ((String)classNames.get(table.getTable()) + "Data");
    }

    public String getCreateInfoClassName(Table table) {
        return ((String)classNames.get(table.getTable()) + "CreateInfo");
    }

    public String getUpdateInfoClassName(Table table) {
        return ((String)classNames.get(table.getTable()) + "UpdateInfo");
    }

    public String getDaoClassName(Table table) {
        return ((String)classNames.get(table.getTable()) + "DAO");
    }

    public String getHandleClassName(Table table) {
        return ((String)classNames.get(table.getTable()) + "Handle");
    }

    public String getTableName(Table table) {
        return table.getQualifiedName();
    }

    public String getAttributeName(Attribute attribute) {
        String res = attribute.getColumnName();
        if (res.startsWith(attribute.getTable().getTable() + "_")) {
            res = res.substring((attribute.getTable().getTable() + "_").length());
        }
        StringBuffer buf = new StringBuffer();
        StringTokenizer tok = new StringTokenizer(res, "_");
        while (tok.hasMoreTokens()) {
            String part = tok.nextToken();
            buf.append(part.substring(0, 1).toUpperCase() + part.toLowerCase().substring(1));
        }
        return buf.toString();
    }

    public String getAttributeFieldName(Attribute attribute) {
        String s =  getAttributeName(attribute);
        return (s.substring(0, 1).toLowerCase() + s.substring(1));
    }
    
    public boolean isTarget(Table table) {
        return classNames.containsKey(table.getTable());
    }
}
