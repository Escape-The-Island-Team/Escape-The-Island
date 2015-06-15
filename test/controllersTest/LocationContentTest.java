package controllersTest;

import controllers.ItemParser;
import controllers.LocationContent;
import junit.framework.TestCase;

public class LocationContentTest extends TestCase {

    public void testGetId() throws Exception {
        int getId = new LocationContent().getId("beachMid");
        String actual= String.valueOf(getId);
        String expected = "0";

        assertEquals(actual,expected);


    }

    public void testGetName() throws Exception {
        String getName = new LocationContent().getName(0);
        String actual= String.valueOf(getName);
        String expected = "loc_beachMid";

        assertEquals(actual,expected);
    }

    public void testIsLocation() throws Exception {
        boolean getName = new LocationContent().isLocation("beachRight");
        String actual= String.valueOf(getName);
        String expected = "true";

        assertEquals(actual,expected);
    }

    public void testPathExists() throws Exception {
        boolean path = new LocationContent().pathExists("beachRight","beachMid");
        String actual= String.valueOf(path);
        String expected = "true";

        assertEquals(actual,expected);
    }

    public void testGetObjects() throws Exception {

    }

    public void testGetNpcs() throws Exception {

    }

    public void testGetMessage() throws Exception {

    }

    public void testPathChangeResult() throws Exception {

    }
}