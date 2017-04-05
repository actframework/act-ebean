package act.db.ebean2;

import act.event.ActEvent;
import io.ebean.config.ServerConfig;

/**
 * The event get triggered when {@link ServerConfig} is loaded and
 * before the {@link io.ebean.EbeanServer} is created.
 *
 * Application can use this event to do further configuration on
 * {@link ServerConfig}
 */
public class EbeanConfigLoaded extends ActEvent<ServerConfig> {
    public EbeanConfigLoaded(ServerConfig source) {
        super(source);
    }
}
