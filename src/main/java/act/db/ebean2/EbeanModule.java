package act.db.ebean2;

import act.db.ebean2.util.EbeanDaoLoader;
import org.osgl.inject.Module;

@SuppressWarnings("unused")
public class EbeanModule extends Module {

    @Override
    protected void configure() {
        registerGenericTypedBeanLoader(EbeanDao.class, new EbeanDaoLoader());
    }

}
