package su.sergey.contacts.codegen;

import java.io.InputStream;
import java.util.Properties;

import su.sergey.contacts.codegen.createinfogen.CreateInfoClassGenerator;
import su.sergey.contacts.codegen.daogen.DAOClassGenerator;
import su.sergey.contacts.codegen.datagen.DataClassGenerator;
import su.sergey.contacts.codegen.db.PGParser;
import su.sergey.contacts.codegen.handlegen.HandleClassGenerator;
import su.sergey.contacts.codegen.impl.Broadcaster;
import su.sergey.contacts.codegen.updateinfogen.UpdateInfoClassGenerator;
import su.sergey.contacts.codegen.util.Environment;
import su.sergey.contacts.codegen.util.FileHelper;

/**
 * SourceGenerator
 * 
 * @author Сергей Богданов
 */
public class SourceGenerator {
    public static void main(String args[]) {
        try {
            Properties properties = new Properties();
            ClassLoader loader = StatisticsGenerator.class.getClassLoader();
            InputStream input = loader.getResourceAsStream("database.properties");
            if (input != null) {
            	properties.load(input);
            }
            String tablePattern = properties.getProperty("tables");
            String schemaPattern = properties.getProperty("schemas");
            String databaseName = properties.getProperty("database");
            String userName = properties.getProperty("user");
            String userPassword = properties.getProperty("password");
        	FileHelper fileHelper = new FileHelper(Environment.getSrcPath());
			Broadcaster broadcaster = new Broadcaster();
			broadcaster.addListener(new CreateInfoClassGenerator(fileHelper, Environment.getDtoPackage()));
			broadcaster.addListener(new HandleClassGenerator(fileHelper, Environment.getDtoPackage()));
			broadcaster.addListener(new DataClassGenerator(fileHelper, Environment.getDtoPackage()));
			broadcaster.addListener(new UpdateInfoClassGenerator(fileHelper, Environment.getDtoPackage()));
			broadcaster.addListener(new DAOClassGenerator(fileHelper,
			                                              Environment.getDaoPackage(),
			                                              Environment.getDtoPackage(),
			                                              Environment.getAbstractDaoClass(),
			                                              Environment.getConnectionSourceClass(),
			                                              Environment.getSqlOutAccessorClass(),
			                                              Environment.getDaoExceptionClass()));
            PGParser pgParser = new PGParser(databaseName, userName, userPassword, broadcaster);
            pgParser.start(schemaPattern, tablePattern);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
