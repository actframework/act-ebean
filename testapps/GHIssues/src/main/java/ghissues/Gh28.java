package ghissues;

import act.controller.annotation.UrlContext;
import act.db.ebean.EbeanDao;
import act.util.*;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.Id;

@UrlContext("28")
public class Gh28 extends LogSupport {

    @Entity(name = "foo")
    public static class Foo implements SimpleBean {
        @Id
        public Integer id;

        public String name;
    }

    @Inject
    private EbeanDao<Integer, Foo> dao;

    @PostAction
    @PropertySpec("id")
    public Foo create(Foo foo) {
        return dao.save(foo);
    }

    @GetAction("first")
    public Foo first() {
        return dao.q().first();
    }

}
