package controllersTest;

import controllers.DialogSystem;
import junit.framework.TestCase;
import org.junit.*;
import play.mvc.Result;
import play.test.FakeRequest;
import play.test.Helpers;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.status;

/***
 * Created by Alisa Koksjuk on 21.05.2015, updated on 27.05.2015.
 * @testSplitMessage: TODO fix
 */
public class DialogSystemTest extends TestCase {

    @Test
    public void testRetieveMessage() throws Exception {
 List<String> reMes = new DialogSystem().retieveMessage("maya",0,0);
        String actual= String.valueOf(reMes);
        String expected ="[Ugh! You do not belong to my people. Who are you?  I am Abu Simple, the chieftain of this temple., , , welcome]";

        assertEquals(expected,actual);

    }
    /*@Test
    public void testSplitMessage()throws Exception {
        List<String> spMes = new DialogSystem().splitMessage("1");
        String actual= String.valueOf(spMes);
        String expected ="[Ugh! You do not belong to my people. Who are you?  I am Abu Simple, the chieftain of this temple., , ]";

        assertEquals(expected,actual);
    }*/
@Ignore
    public void testMayaQuest() throws Exception {

    }
}