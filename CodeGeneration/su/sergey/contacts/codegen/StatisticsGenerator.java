package su.sergey.contacts.codegen;

import java.io.InputStream;
import java.util.Properties;

import su.sergey.contacts.codegen.db.PGParser;
import su.sergey.contacts.codegen.impl.Broadcaster;
import su.sergey.contacts.codegen.statistics.AutoGenerateStatistics;
import su.sergey.contacts.codegen.statistics.TypeStatistics;

/**
 * StatisticsGenerator
 * 
 * @author Сергей Богданов
 */
public class StatisticsGenerator {
    public static void main(String args[]) {
        try {
            TypeStatistics typeStatistics = new TypeStatistics();
            AutoGenerateStatistics autoGenerateStatistics = new AutoGenerateStatistics();
            Broadcaster broadcaster = new Broadcaster();
            broadcaster.addListener(typeStatistics);
            broadcaster.addListener(autoGenerateStatistics);
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
            PGParser pgParser = new PGParser(databaseName, userName, userPassword, broadcaster);
            pgParser.start(schemaPattern, tablePattern);
            System.err.println(typeStatistics.listTypes());
            System.err.println(autoGenerateStatistics.listTypes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
