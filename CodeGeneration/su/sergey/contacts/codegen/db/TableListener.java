package su.sergey.contacts.codegen.db;

/**
 * TableListener
 * 
 * @author ������ ��������
 */
public interface TableListener {
    void startTable(Table table);
    void attribute(Attribute attribute);
    void endTable();
}
