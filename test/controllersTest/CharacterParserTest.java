package controllersTest;

import controllers.CharacterParser;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

public class CharacterParserTest extends TestCase {

    @Test
    public void testIsCharacter() throws Exception {



            int result = new CharacterParser().isCharacter("Bob");

            String actual = String.valueOf(result);

            String expected = "1";

            assertEquals(expected, actual);
        }


    @Test
    public void testIsOld() throws Exception {


            int result = new CharacterParser().isCharacter("Bob");

            String actual = String.valueOf(result);

            String expected = "1";

            assertEquals(expected, actual);
        }

    }
