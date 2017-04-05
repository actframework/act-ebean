package act.db.ebean2;

import act.event.ActEvent;
import io.ebean.config.ServerConfig;

/**
 * The event triggered right before Ebean server is created
 */
public class PreEbeanCreation extends ActEvent<ServerConfig> {
    public PreEbeanCreation(ServerConfig source) {
        super(source);
    }
}
