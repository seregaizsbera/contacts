package su.sergey.contacts.codegen.handlegen;

import su.sergey.contacts.codegen.db.Attribute;
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
	public void startTable(Table table) {}
	
	/**
	 * @see TableListener#attribute(Attribute)
	 */
	public void attribute(Attribute attribute) {}
	
	/**
	 * @see TableListener#endTable()
	 */
	public void endTable() {}
	
	String getFields() {
		return fields.toString();
	}
}
