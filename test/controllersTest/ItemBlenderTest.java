package controllersTest;

import controllers.ItemBlender;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBlenderTest extends TestCase {

    @Test
    public void testCombineItems() throws Exception {

        List<String> itemlist = new ArrayList<>(Arrays.asList("stick"));
        String result = new ItemBlender().combineItems(itemlist);

        String actual= result;
        String expected= "You cannot combine an item with itself.";
        assertEquals(expected,actual);
    }

    @Test
    public void testCombineItems3() throws Exception {

        List<String> itemlist = new ArrayList<>(Arrays.asList("stick","stick"));
        String result = new ItemBlender().combineItems(itemlist);

        String actual= result;
        String expected= "These items cannot be combined with each other!";
        assertEquals(expected,actual);
    }

    @Test
    public void testCombineItems2() throws Exception {

        List<String> itemlist = new ArrayList<>(Arrays.asList("stick","flintstones"));
        String result = new ItemBlender().combineItems(itemlist);

        String actual= result;
        String expected= "torch";
        assertEquals(expected,actual);
    }

    @Test
    public void testMessageOfCombination() throws Exception {

        String result = new ItemBlender().messageOfCombination("12345");

        String actual=result;
        String expected= "Gr8 b8 m8 I r8 8/8!";

        assertEquals(expected,actual);
    }
//TODO schauen, das vergleichbare werte rauskommen können
    @Test
    public void testRemoveItems() throws Exception {

        List<String> result = new ItemBlender().removeItems("12345");
        List<String> result2 = new ArrayList<String>();

        String actual= String.valueOf(result.size());
        String expected= String.valueOf(result2.size());

        assertEquals(expected,actual);
    }

}