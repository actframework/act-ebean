package act.db.ebean;

/*-
 * #%L
 * ACT AAA Plugin
 * %%
 * Copyright (C) 2015 - 2017 ActFramework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import static act.app.event.SysEventId.DEPENDENCY_INJECTOR_PROVISIONED;

import act.event.ActEvent;
import act.event.BindOn;
import io.ebean.config.ServerConfig;

/**
 * NOTE this class is deprecated. Application that needs to configure
 * {@link ServerConfig Ebean ServerConfig} shall implement
 * {@link EbeanConfigurator} interface
 *
 * The event get triggered when {@link ServerConfig} is loaded and
 * before the {@link io.ebean.EbeanServer} is created.
 *
 * Application can use this event to do further configuration on
 * {@link ServerConfig}
 */
@Deprecated
@BindOn(DEPENDENCY_INJECTOR_PROVISIONED)
public class EbeanConfigLoaded extends ActEvent<ServerConfig> {
    public EbeanConfigLoaded(ServerConfig source) {
        super(source);
    }
}
