package su.sergey.contacts.codegen.handlegen;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.db.TypeListener;

/**
 * FieldsGenerator
 * 
 * @author Сергей Богданов
 */
class FieldsGenerator implements TableListener {
	private TypeListener typeListener;
	private StringBuffer fields;
	
	FieldsGenerator(TypeListener typeListener) {
		this.typeListener = typeListener;
		fields = new StringBuffer();
	}
	
	/**
	 * @see TableListener#startTable(Table)
	 */
	public void startTable(Table table) {
		fields.delete(0, fields.length());
	}
	
	/**
	 * @see TableListener#attribute(Attribute)
	 */
	public void attribute(Attribute attribute) {
		if (attribute.getKeyseq() != 0) {
			String typeName = typeListener.type(Helper.getJavaType(attribute));
			fields.append("    private ");
			fields.append(typeName).append(" ");
			fields.append(Helper.getAttributeFieldName(attribute)).append(";\n");
		}
	}
	
	/**
	 * @see TableListener#endTable()
	 */
	public void endTable() {}
	
	String getFields() {
		return fields.toString();
	}
}
