package su.sergey.contacts.codegen.db;

/**
 * Helper
 * 
 * @author Сергей Богданов
 */
public interface Helper {
    boolean isForUpdate(Attribute attribute);
    
    String getSetMethod(Attribute attribute);

    String getGetMethod(Attribute attribute);

    String getJavaType(Attribute attribute);

    String getDataClassName(Table table);

    String getCreateInfoClassName(Table table);
    
    String getUpdateInfoClassName(Table table);

    String getDaoClassName(Table table);

    String getHandleClassName(Table table);

    String getTableName(Table table);

    String getAttributeName(Attribute attribute);

    String getAttributeFieldName(Attribute attribute);
    
    boolean isTarget(Table table);
}
