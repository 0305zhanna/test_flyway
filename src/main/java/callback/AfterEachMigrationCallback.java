package callback;

import org.flywaydb.core.api.MigrationVersion;
import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AfterEachMigrationCallback implements Callback {

    private String migrationRootDir = "C:\\Users\\zhanna_fedorova\\IdeaProjects\\test_flyway\\r_sql\\temp";

    @Override
    public boolean supports(Event event, Context context) {
        return event == Event.AFTER_EACH_MIGRATE;
    }

    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return true;
    }

    @Override
    public void handle(Event event, Context context) {
        System.out.println("Event: " + event.toString());
        String desc = context.getMigrationInfo().getDescription();
        MigrationVersion version = context.getMigrationInfo().getVersion();
        System.out.println(desc);
    }
}
