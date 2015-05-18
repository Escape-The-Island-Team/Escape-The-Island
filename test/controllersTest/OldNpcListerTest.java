package controllersTest;


import controllers.Locationcontent.OldNpcLister;
import junit.framework.TestCase;

import java.util.List;

public class OldNpcListerTest extends TestCase {

    public void testGetNpcs() throws Exception {

        List<String> result = new OldNpcLister().getNpcs("treehouse");


        String actual = result.get(0);
        assertEquals(1,result.size());

        String expected = "versutus";

        assertEquals(expected, actual);
    }
}