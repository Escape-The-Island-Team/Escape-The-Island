package controllersTest;

import controllers.LocationContent;
import junit.framework.TestCase;

import java.util.List;

public class LocationParserTest extends TestCase {
    public void testGetNpcs() throws Exception {

        List<String> list = LocationContent.getObjects("beachMid", true);

        String actual = list.get(0);

        String expected = "oldStone";

        assertEquals(expected, actual);
    }

}