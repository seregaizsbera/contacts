package su.sergey.contacts.codegen.handlegen;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;

/**
 * ConstructorGenerator
 * 
 * @author Сергей Богданов
 */
class ConstructorGenrator implements TableListener {
	
	/**
	 * Constructor for ConstructorGenrator
	 */
	public ConstructorGenrator() {
		super();
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
}
