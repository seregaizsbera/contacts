package su.sergey.contacts.codegen.db;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Helper
 * @author 
 */
public class Helper {
    private static final Map className;

    public static Map getClassNames() {
        return className;
    }

    public static final String getSetMethod(Attribute attribute) {
        String type = getJavaType(attribute);
        if (type.equals("Integer")) {
            return "setInt(pstmt, index++, ";
        } else if (type.equals("java.math.BigDecimal")) {
            return "setBigDecimal(pstmt, index++, ";
        } else if (type.equals("java.util.Date") && attribute.getType().equalsIgnoreCase("TIME")) {
            return "setTime(pstmt, index++, ";
        } else if (type.equals("java.util.Date") && attribute.getType().equalsIgnoreCase("TIMESTAMP")) {
            return "setTimestamp(pstmt, index++, ";
        } else if (type.equals("java.util.Date") && attribute.getType().equalsIgnoreCase("DATE")) {
            return "setDate(pstmt, index++, ";
        } else if (type.equals("Float")) {
            return "setFloat(pstmt, index++, ";
        } else if (type.equals("com.sberbank.sbclients.valueobjects.Currency")) {
            return "setCurrency(pstmt, index++, ";
        } else if (type.equals("String")) {
           return "setString(pstmt, index++, ";
        } else {
            throw new IllegalArgumentException(">>> " + type);
        }
    }

    public static final String getGetMethod(Attribute attribute) {
        String type = getJavaType(attribute);
        if (type.equals("Integer")) {
            return "getInt(rs, index++)";
        } else if (type.equals("java.math.BigDecimal")) {
            return "getBigDecimal(rs, index++)";
        } else if (type.equals("com.sberbank.sbclients.valueobjects.Currency")) {
            return "getCurrency(rs, index++)";
        } else if (type.equals("java.util.Date") && attribute.getType().equalsIgnoreCase("TIME")) {
            return "getTime(rs, index++)";
        } else if (type.equals("java.util.Date") && attribute.getType().equalsIgnoreCase("TIMESTAMP")) {
            return "getTimestamp(rs, index++)";
        } else if (type.equals("java.util.Date")  && attribute.getType().equalsIgnoreCase("DATE")) {
            return "getDate(rs, index++)";
        } else if (type.equals("Float")) {
            return "getFloat(rs, index++)";
        } else if (type.equals("String")) {
            if (attribute.getType().equals("CHARACTER")) {
                return "getCharString(rs, index++)";
            } else {
                return "getString(rs, index++)";
            }
        } else {
            throw new IllegalArgumentException(">>> " + type);
        }
    }

    public static final String getJavaType(Attribute attribute) {
        if (attribute.getType().equals("SMALLINT") || attribute.getType().equals("INTEGER") || attribute.getType().equals("int4")) {
            return "Integer";
        } else if (attribute.getType().equalsIgnoreCase("VARCHAR") || attribute.getType().equals("CHARACTER") || attribute.getType().equals("LONG VARCHAR") || attribute.getType().equals("text")) {
            return "String";
        } else if (attribute.getType().equalsIgnoreCase("DATE") || attribute.getType().equalsIgnoreCase("TIME") || attribute.getType().equalsIgnoreCase("TIMESTAMP") || attribute.getType().equals("date")) {
            return "java.util.Date";
        } else if (attribute.getType().equals("BIGINT") || attribute.getType().equals("BIGDECIMAL") || attribute.getType().equals("int8")) {
            return "java.math.BigDecimal";
        } else if ((attribute.getType().equals("DECIMAL") && attribute.getLength() == 30 && attribute.getScale() == 2) || attribute.getType().equals("numeric")) {
            return "com.sberbank.sbclients.valueobjects.Currency";
        } else if (attribute.getType().equals("REAL")) {
            return "Float";
        } else if (attribute.getType().equals("bool")) {
            return "Integer";
        } else {
            throw new IllegalArgumentException("Unknown type > " + attribute.getType());
        }
    }

    public static final String getDataClassName(Table table) {
        return ((String)className.get(table.getTable()) + "Data");
    }

    public static final String getDataInfoClassName(Table table) {
        return ((String)className.get(table.getTable()) + "DataInfo");
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

    public static final String getRealDaoClassName(Table table) {
        return ((String)className.get(table.getTable()) + "DAO");
    }

    public static final String getHandleClassName(Table table) {
        return ((String)className.get(table.getTable()) + "Data");
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
