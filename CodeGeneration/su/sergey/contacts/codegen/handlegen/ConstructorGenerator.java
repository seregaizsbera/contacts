package su.sergey.contacts.codegen.handlegen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.db.TypeListener;

/**
 * ConstructorGenerator
 * 
 * @author Сергей Богданов
 */
class ConstructorGenerator implements TableListener {
	private TypeListener typeListener;
	private Collection fields;
	private StringBuffer constructor;
	private Table theTable;
	
	/**
	 * Constructor for ConstructorGenrator
	 */
	public ConstructorGenerator(TypeListener typeListener) {
		this.typeListener = typeListener;
		fields = new ArrayList();
		constructor = new StringBuffer();
	}
	
	/**
	 * @see TableListener#startTable(Table)
	 */
	public void startTable(Table table) {
	    constructor.delete(0, constructor.length());
	    fields.clear();
	    theTable = table;
	}
	
	/**
	 * @see TableListener#attribute(Attribute)
	 */
	public void attribute(Attribute attribute) {
		if (attribute.getKeyseq() != 0) {
			String typeName = typeListener.type(Helper.getJavaType(attribute));
			String fieldName = Helper.getAttributeFieldName(attribute);
			fields.add(new FieldParameter(typeName, fieldName));
		}
	}
	
	/**
	 * @see TableListener#endTable()
	 */
	public void endTable() {
		StringBuffer body = new StringBuffer();
		constructor.append("    public ").append(Helper.getHandleClassName(theTable));
		constructor.append("(");
		for (Iterator i = fields.iterator(); i.hasNext();) {
			FieldParameter field = (FieldParameter) i.next();
			constructor.append(field.getTypeName()).append(" ");
			constructor.append(field.getFieldName());
			if (i.hasNext()) {
				constructor.append(", ");
			}
			body.append("        this.").append(field.getFieldName());
			body.append(" = ").append(field.getFieldName());
			body.append(";\n");
		}
		constructor.append(") {\n");
		constructor.append(body.toString());
		constructor.append("    }\n");
	}
	
	String getConstructor() {
		return constructor.toString();
	}
}
