package su.sergey.contacts.codegen;

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
    private static final String SCHEMA_PATTERN = null;
    private static final String TABLE_PATTERN = "%";

    public static void main(String args[]) {
        try {
            TypeStatistics typeStatistics = new TypeStatistics();
            AutoGenerateStatistics autoGenerateStatistics = new AutoGenerateStatistics();
            Broadcaster broadcaster = new Broadcaster();
            broadcaster.addListener(typeStatistics);
            broadcaster.addListener(autoGenerateStatistics);
            PGParser pgParser = new PGParser(broadcaster);
            pgParser.start(SCHEMA_PATTERN, TABLE_PATTERN);
            System.err.println(typeStatistics.listTypes());
            System.err.println(autoGenerateStatistics.listTypes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
