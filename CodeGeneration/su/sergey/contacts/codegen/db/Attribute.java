package su.sergey.contacts.codegen.db;

/**
 * Attribute
 * @author Сергей Богданов
 */
public class Attribute {
    private Table table;
    private String columnName = null;
    private int columnNumber = -1;
    private String type = null;
    private int length = -1;
    private int scale = -1;
    private boolean nulls = false;
    private int keyseq = 0;
    private boolean generated = false;
    private String remarks = "";

    public Attribute() {
    }

    public Attribute(Table table, String columnName, int columnNumber, String type, int length, int scale, boolean nulls, int keyseq, String remarks, boolean generated) {
        this.table = table;
        this.columnName = columnName;
        this.columnNumber = columnNumber;
        this.type = type;
        this.length = length;
        this.scale = scale;
        this.nulls = nulls;
        this.keyseq = keyseq;
        this.remarks = remarks;
        this.generated = generated;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public boolean isNulls() {
        return nulls;
    }

    public void setNulls(boolean nulls) {
        this.nulls = nulls;
    }

    public int getKeyseq() {
        return keyseq;
    }

    public void setKeyseq(int keyseq) {
        this.keyseq = keyseq;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isGenerated() {
        return generated;
    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
    }
}
