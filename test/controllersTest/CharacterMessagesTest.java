package controllersTest;

import controllers.CharacterMessages;
import junit.framework.TestCase;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

/***
 * Created by Alisa Koksjuk 24.05.2015.
 */


public class CharacterMessagesTest extends TestCase {
    @Test
    public void testGetMessage() throws Exception {

      String list = new CharacterMessages().getMessage("Bob",0);

        String actual = list;

        String expected = "Hey, I'm Bob. Bob what? Just Bob.";

        assertEquals(expected, actual);
    }

    @Test
    public void testGetMessage2() throws Exception {

        String list = new CharacterMessages().getMessage("Alice",0);

        String actual = list;

        String expected = "I am Alice, the dangerous pirate. Don't bother me or you will regret it!";

        assertEquals(expected, actual);
    }

    @Test
    public void testGetMessage3() throws Exception {

        String list = new CharacterMessages().getMessage("Nova",0);

        String actual = list;

        String expected = "Special agent Nova, ready for beauty... excuse me, duty!";

        assertEquals(expected, actual);
    }

    @Test
    public void testGetMessage4() throws Exception {

        String list = new CharacterMessages().getMessage("HomTanks",0);

        String actual = list;

        String expected = "Hello, my name is Hom Tanks from Ted Ex. Now let's get this task started.";

        assertEquals(expected, actual);
    }

    @Test
    public void testGetMessage5() throws Exception {

        String list = new CharacterMessages().getMessage("BerryStraw",0);

        String actual = list;

        String expected =  "Hey! I'm Berry! Do you want to play with me?";

        assertEquals(expected, actual);
    }

    @Test
    public void testGetMessage6() throws Exception {

        String list = new CharacterMessages().getMessage("CaptainSpeckJarrow",0);

        String actual = list;

        String expected = "I'm Captain Speck Jarrow, the famous captain of the british royal navy! *Hicks*";

        assertEquals(expected, actual);
    }
}