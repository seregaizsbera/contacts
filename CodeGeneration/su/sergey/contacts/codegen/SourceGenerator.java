package su.sergey.contacts.codegen;

import su.sergey.contacts.codegen.createinfogen.CreateInfoClassGenerator;
import su.sergey.contacts.codegen.daogen.DAOClassGenerator;
import su.sergey.contacts.codegen.datagen.DataClassGenerator;
import su.sergey.contacts.codegen.db.PGParser;
import su.sergey.contacts.codegen.handlegen.HandleClassGenerator;
import su.sergey.contacts.codegen.impl.Broadcaster;
import su.sergey.contacts.codegen.updateinfogen.UpdateInfoClassGenerator;

/**
 * SourceGenerator
 * 
 * @author Сергей Богданов
 */
public class SourceGenerator {
    private static final String SCHEMA_PATTERN = null;
    private static final String TABLE_PATTERN = "%";

    public static void main(String args[]) {
        try {
        	FileHelper fileHelper = new FileHelper(Environment.SRC_PATH);
			Broadcaster broadcaster = new Broadcaster();
			broadcaster.addListener(new CreateInfoClassGenerator(fileHelper, Environment.DTO_PACKAGE));
			broadcaster.addListener(new HandleClassGenerator(fileHelper, Environment.DTO_PACKAGE));
			broadcaster.addListener(new DataClassGenerator(fileHelper, Environment.DTO_PACKAGE));
			broadcaster.addListener(new UpdateInfoClassGenerator(fileHelper, Environment.DTO_PACKAGE));
			broadcaster.addListener(new DAOClassGenerator(fileHelper,
			                                              Environment.DAO_PACKAGE,
			                                              Environment.DTO_PACKAGE,
			                                              "su.sergey.contacts.util.dao.AbstractDAO",
			                                              "su.sergey.contacts.util.dao.ConnectionSource",
			                                              "su.sergey.contacts.util.dao.SqlOutAccessor",
			                                              "su.sergey.contacts.util.dao.DAOException"));
            PGParser pgParser = new PGParser(broadcaster);
            pgParser.start(SCHEMA_PATTERN, TABLE_PATTERN);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
