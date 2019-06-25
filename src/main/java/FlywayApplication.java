import config.FlywayConfig;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import tools.FileManipulationService;

public class FlywayApplication {

    private static FileManipulationService fileManipulationService = new FileManipulationService();

    public static void main(String[] args) {
        Flyway flyway = FlywayConfig.getFlyway();

        fileManipulationService.createTempDirectoryWithLinks();

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

        fileManipulationService.deleteTempDirectory();
    }
}
