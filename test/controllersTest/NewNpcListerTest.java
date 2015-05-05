package controllersTest;

import controllers.LocationParser;
import junit.framework.TestCase;

import java.util.List;

public class NewNpcListerTest extends TestCase {

    public void testGetNpcs() throws Exception {

        List<String> list = LocationParser.getObjects("beachMid", false);

        String actual = list.get(0);

        String expected = "oldStone";

        assertEquals(expected, actual);
    }
}