package controllersTest;

import controllers.ObjectParser;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;

/**
 * Created by Alisa on 15.05.2015 updated on 24.05.2015.
 */

public class ObjectParserTest extends TestCase {

//TODO durchschauen, fixen
    @Test
    public void testGetObjects() throws Exception {

        List<String> locationObjects = new ArrayList<>();

        List<String> locations = new ObjectParser().getObjects("beachMid",0);

       String actual= String.valueOf(locations.size());
        //String actual=locationObjects.get(0);

        String expected = String.valueOf(locationObjects.size());

        assertEquals(expected, actual);
    }


    @Test
    public void testUseObject() throws Exception {


        List<String> listItems = new ArrayList<String>(Arrays.asList("item1, item2, item3"));

        String actual = new ObjectParser().useObject("bear",listItems,1);




        String expected = "This item cannot be used on the bear.";

        assertEquals(expected, actual);
    }

    @Test
    public void testUseBear() throws Exception {
        String useBear = new ObjectParser().useBear(" ", 1);


        String actual = useBear;

        String expected = "This item cannot be used on the bear.";

        assertEquals(expected, actual);
    }

    @Test
    public void testUseBeehive() throws Exception {
        String result = new ObjectParser().useBeehive(" ", 0);

        String actual = result;

        String expected = "This item cannot be used on the beehive.";

        assertEquals(expected, actual);
    }


}