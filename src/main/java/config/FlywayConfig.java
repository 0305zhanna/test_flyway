package config;

import callback.AfterEachMigrationCallback;
import callback.FlywayCallback;
import org.flywaydb.core.Flyway;

public class FlywayConfig {

    private static Flyway flyway;

    public static Flyway getFlyway() {
        if (flyway == null) {
            flyway = new Flyway();
//            flyway = Flyway.configure()
//                    .dataSource("jdbc:oracle:thin:@localhost:1521:ORCLCDB", "fp", "qwer")
//                    //.locations("classpath:com/epam/flyway")
//                    .locations("filesystem:" + "C:\\Users\\zhanna_fedorova\\IdeaProjects\\test_flyway\\r_sql\\R__test_apply_normal.sql")
//                    .callbacks(new AfterEachMigrationCallback())
//                    .baselineOnMigrate(true)
//                    .load();
            flyway.setLocations("filesystem:" + "C:\\Users\\zhanna_fedorova\\IdeaProjects\\test_flyway\\r_sql\\temp");
            flyway.setBaselineOnMigrate(true);
            flyway.setDataSource("jdbc:oracle:thin:@localhost:1521:ORCLCDB", "fp", "qwer");
            flyway.setCallbacks(new FlywayCallback());
        }
        return flyway;
    }

}