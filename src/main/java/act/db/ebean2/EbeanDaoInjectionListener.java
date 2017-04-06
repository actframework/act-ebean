package act.db.ebean2;

import act.Act;
import act.app.App;
import act.app.event.AppEventId;
import act.db.DbService;
import act.db.di.DaoInjectionListenerBase;
import act.event.AppEventListenerBase;
import org.osgl.$;
import org.osgl.inject.BeanSpec;
import org.osgl.util.Generics;
import org.osgl.util.S;

import java.lang.reflect.Type;
import java.util.EventObject;
import java.util.List;

public class EbeanDaoInjectionListener extends DaoInjectionListenerBase {

    @Override
    public Class[] listenTo() {
        return new Class[]{EbeanDao.class};
    }

    @Override
    public void onInjection(Object injectee, BeanSpec spec) {
        List<Type> typeParameters = spec.typeParams();
        if (typeParameters.isEmpty()) {
            typeParameters = Generics.typeParamImplementations(spec.rawType(), EbeanDao.class);
        }
        if (null == typeParameters) {
            logger.warn("No type parameter information provided");
            return;
        }
        final $.T2<Class, String> resolved = resolve(typeParameters);
        DbService dbService = App.instance().dbServiceManager().dbService(resolved._2);
        if (dbService instanceof EbeanService) {
            final EbeanService service = $.cast(dbService);
            final EbeanDao dao = $.cast(injectee);
            Act.eventBus().bind(AppEventId.PRE_START, new AppEventListenerBase(S.concat(resolved._2, "-ebean-on-dao-injection")) {
                @Override
                public void on(EventObject eventObject) throws Exception {
                    dao.ebean(service.ebean());
                    dao.modelType(resolved._1);
                }
            });
        }
    }
}
