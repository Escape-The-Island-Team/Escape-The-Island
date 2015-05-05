package controllersTest;

import controllers.LocationParser;
import controllers.Locationcontent.NewNpcLister;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class NewNpcListerTest extends TestCase {

    public void testGetNpcs() throws Exception {

        List<String> result = new NewNpcLister().getNpcs("temple");


        String actual = result.get(0);
        assertEquals(1,result.size());

        String expected = "nativeMaya";

        assertEquals(expected, actual);
    }
}