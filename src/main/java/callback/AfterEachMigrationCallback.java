package callback;

import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;

public class AfterEachMigrationCallback implements Callback {

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
        System.out.println(context.getMigrationInfo().getDescription());

//        if (context.getMigrationInfo().getDescription().contains("fail")) {
//            throw new RuntimeException("FAILED!");
//        }
    }
}
