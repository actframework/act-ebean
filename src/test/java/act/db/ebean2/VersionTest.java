package act.db.ebean2;

import org.junit.Test;
import org.osgl.ut.TestBase;

public class VersionTest extends TestBase {

    @Test
    public void versionShallContainsEbean2() {
        yes(EbeanPlugin.VERSION.toString().contains("ebean2"));
    }
}
