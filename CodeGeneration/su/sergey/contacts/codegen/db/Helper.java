package su.sergey.contacts.codegen.db;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Helper
 * 
 * @author Сергей Богданов
 */
public class Helper {
	private static final String CURRENCY = "su.sergey.contacts.valueobjects.Currency";
    private static final Map className;

    public static Map getClassNames() {
        return className;
    }

    public static final String getSetMethod(Attribute attribute) {
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
        } else if (type.equals(CURRENCY)) {
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

    public static final String getGetMethod(Attribute attribute) {
        String type = getJavaType(attribute);
        String attributeType = attribute.getType();
        if (type.equals("java.lang.Integer")) {
            return "getInt(rs, index++)";
        } else if (type.equals("java.math.BigDecimal")) {
            return "getBigDecimal(rs, index++)";
        } else if (type.equals(CURRENCY)) {
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

    public static final String getJavaType(Attribute attribute) {
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
            return CURRENCY;
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

    public static final String getDataClassName(Table table) {
        return ((String)className.get(table.getTable()) + "Data");
    }

    public static final String getCreateInfoClassName(Table table) {
        return ((String)className.get(table.getTable()) + "CreateInfo");
    }

    public static final String getUpdateInfoClassName(Table table) {
        return ((String)className.get(table.getTable()) + "UpdateInfo");
    }

    public static final String getDaoClassName(Table table) {
        return ((String)className.get(table.getTable()) + "DAO");
    }

    public static final String getHandleClassName(Table table) {
        return ((String)className.get(table.getTable()) + "Handle");
    }

    public static final String getTableName(Table table) {
        return table.getTable();
    }

    public static final String getAttributeName(Attribute attribute) {
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

    public static final String getAttributeFieldName(Attribute attribute) {
        String s =  getAttributeName(attribute);
        return (s.substring(0, 1).toLowerCase() + s.substring(1));
    }
    
    public static final boolean isTarget(Table table) {
        return className.containsKey(table.getTable());
    }

    static {
        Map temp = new HashMap();
        temp.put("persons", "Person");
        temp.put("birthdays", "Birthday");
        temp.put("call_directions", "CallDirection");
        temp.put("call_reports", "CallReport");
        temp.put("call_types", "CallType");
        temp.put("calls", "Call");
        temp.put("calls_pays", "CallPay");
        temp.put("coworkers", "Coworker");
        temp.put("emails", "Email");
        temp.put("friends", "Friend");
        temp.put("gprs", "Gprs");
        temp.put("gprs_urls", "GprsUrl");
        temp.put("icqs", "Icq");
        temp.put("msu", "Msu");
        temp.put("msu_departments", "MsuDepartment");
        temp.put("persons", "Person");
        temp.put("phone_types", "PhoneType");
        temp.put("phones", "Phone");
        temp.put("shnip", "Shnip");
        temp.put("supplies", "Supply");
        temp.put("supply_phones", "SupplyPhone");
        className = Collections.unmodifiableMap(temp);
    }
}
