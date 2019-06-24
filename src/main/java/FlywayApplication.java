import config.FlywayConfig;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

import java.util.Arrays;
import java.util.List;

public class FlywayApplication {
    public static void main(String[] args) {
        Flyway flyway = FlywayConfig.getFlyway();
//        List<String> locations = Arrays.asList("1", "2", "3");
//        for (String location : locations) {
//            try {
//                flyway.setLocations("filesystem:" + "C:\\Users\\zhanna_fedorova\\IdeaProjects\\test_flyway\\r_sql\\" + location);
//                flyway.migrate();
//            } catch (FlywayException ex) {
//                System.out.println("REPAIRING...");
//                flyway.repair();
//            }
//        }
        List<String> prefixes = Arrays.asList("1", "2", "3");
        for (String prefix : prefixes) {
            try {
                flyway.setRepeatableSqlMigrationPrefix("R"+prefix);
                flyway.migrate();
            } catch (FlywayException ex) {
                System.out.println("REPAIRING...");
                flyway.repair();
            }
        }

//        List<String> initFiles = Arrays.asList("R1__test_apply_normal.sql", "R2__test_apply_with_fail.sql", "R3__test_apply_x.sql");
//        for (String file : initFiles) {
//            try {
//                System.out.println("Migrating file " + file + "...");
//                flyway.setDataSource("jdbc:oracle:thin:@localhost:1521:ORCLCDB", "fp", "qwer", file);
//                flyway.setSqlMigrationPrefix("zzz");
//                flyway.migrate();
//            } catch (FlywayException ex) {
//                System.out.println("REPAIRING...");
//                flyway.repair();
//            }
//        }
//        flyway.setLocations();

//    flyway.repair();
//        flyway.migrate();
    }
}
