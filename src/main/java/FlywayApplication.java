import config.FlywayConfig;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import tools.FileSystemTools;

public class FlywayApplication {

    private static FileSystemTools fileSystemTools = new FileSystemTools();

    public static void main(String[] args) {
        Flyway flyway = FlywayConfig.getFlyway();

        fileSystemTools.createTempDirectoryWithLinks();

        boolean migrationFininshed = true;
        while (migrationFininshed) {
            try {
                flyway.migrate();
                migrationFininshed = false;
            } catch (FlywayException ex) {
                System.out.println("REPAIRING...");
                flyway.repair();
            }
        }

        fileSystemTools.deleteTempDirectory();
    }
}
