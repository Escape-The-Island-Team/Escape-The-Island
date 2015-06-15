package controllersTest;

import controllers.ItemParser;
import junit.framework.TestCase;
import org.junit.Test;
import scalaz.effect.ST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemParserTest extends TestCase {

    @Test
    public void testGetId() throws Exception {
    int getId = new ItemParser().getId("stick", true);
    String actual= String.valueOf(getId);
    String expected = String.valueOf(0);

    assertEquals(actual,expected);

    }
    @Test
    public void testGetId2() throws Exception {
        int getId = new ItemParser().getId("flintstones", true);
        String actual= String.valueOf(getId);
        String expected = String.valueOf(1);

        assertEquals(actual,expected);

    }
    @Test
    public void testGetName() throws Exception {
        String getName = new ItemParser().getName(0, true);
        String actual= String.valueOf(getName);
        String expected = "stick";

        assertEquals(actual,expected);

    }

    @Test
    public void testGetName2() throws Exception {
        List<String> test = new ArrayList<>(Arrays.asList("test1"));
        String getName = new ItemParser().getName(1, false);
        String actual= String.valueOf(getName);
        String expected = "flintstones";

        assertEquals(actual,expected);

    }
    @Test
    public void testMessage() throws Exception {

        List<String> test = new ArrayList<>(Arrays.asList("test1", "test2"));
        String message = new ItemParser().message("stick", true);
        String actual= String.valueOf(message);
        String expected = "You found a stick. This could be useful.";

        assertEquals(actual,expected);
    }

    @Test
    public void testMessage2() throws Exception {

        List<String> test = new ArrayList<>(Arrays.asList("test1", "test2"));
        String message = new ItemParser().message("flintstones", false);
        String actual= String.valueOf(message);
        String expected = "There are some flintstones. Maybe you can use them to make fire.";

        assertEquals(actual,expected);
    }

    @Test
    public void testIsItem() throws Exception {

    boolean isItem = new ItemParser().isItem("stick");
    String actual= String.valueOf(isItem);
    String expected = String.valueOf(true);

    assertEquals(actual,expected);

    }
    public void testIsItem2() throws Exception {

        boolean isItem = new ItemParser().isItem("pen");
        String actual= String.valueOf(isItem);
        String expected = String.valueOf(false);

        assertEquals(actual,expected);

    }
}