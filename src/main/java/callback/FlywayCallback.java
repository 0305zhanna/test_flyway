package callback;

import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import tools.FileSystemTools;

public class FlywayCallback implements Callback {

    private FileSystemTools fileSystemTools = new FileSystemTools();

    @Override
    public boolean supports(Event event, Context context) {
        return event == Event.AFTER_EACH_MIGRATE_ERROR || event == Event.AFTER_EACH_MIGRATE;
    }

    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return false;
    }

    @Override
    public void handle(Event event, Context context) {
        String desc = context.getMigrationInfo().getDescription();

        switch (event) {
            case AFTER_EACH_MIGRATE: {
                System.out.println("Handling AFTER_EACH_MIGRATE event on ..." + desc);
                break;
            }
            case AFTER_EACH_MIGRATE_ERROR: {
                System.out.println("Handling AFTER_EACH_MIGRATE_ERROR event on..." + desc);
                fileSystemTools.deleteLink(desc);
                break;
            }
            default:
                System.out.println("Handling not supported event");
        }
    }
}
