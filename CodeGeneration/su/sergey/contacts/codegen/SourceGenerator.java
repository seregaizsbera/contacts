package su.sergey.contacts.codegen;

import su.sergey.contacts.codegen.daogen.PrepClassGenerator;
import su.sergey.contacts.codegen.daogen.RealClassGenerator;
import su.sergey.contacts.codegen.db.PGParser;
import su.sergey.contacts.codegen.delgen.DelegateClassGenerator;
import su.sergey.contacts.codegen.impl.Broadcaster;

/**
 * SourceGenerator
 * @author Сергей Богданов
 */
public class SourceGenerator {
    private static final String SCHEMA_PATTERN = null;
    private static final String TABLE_PATTERN = "%";

    public static void main(String args[]) {
        try {
            Broadcaster broadcaster = new Broadcaster();
            broadcaster.addListener(new su.sergey.contacts.codegen.createinfogen.ClassGenerator());
            broadcaster.addListener(new su.sergey.contacts.codegen.updateinfogen.ClassGenerator());
            broadcaster.addListener(new su.sergey.contacts.codegen.datagen.ClassGenerator());
            broadcaster.addListener(new su.sergey.contacts.codegen.datainfogen.ClassGenerator());
            broadcaster.addListener(new PrepClassGenerator());
            broadcaster.addListener(new RealClassGenerator());
            broadcaster.addListener(new DelegateClassGenerator());
            PGParser pgParser = new PGParser(broadcaster);
            pgParser.start(SCHEMA_PATTERN, TABLE_PATTERN);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
