package controllersTest;

import controllers.Connections;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Result;
import play.test.FakeRequest;
import play.test.Helpers;
import java.lang.String;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

/***
 * Created by Alisa Koksjuk on 24.05.2015
 */

public class ConnectionsTest extends TestCase {

   /* public void testZweipluszwei() throws Exception {
       //#
        play.test.Helpers.running( play.test.Helpers.fakeApplication(), new java.lang.Runnable(){

            @Override
            public void run(){

                FakeRequest fake = new FakeRequest("GET", "/login");


                Result result = Helpers.callAction(controllers.routes.ref.Application.zweipluszwei(2), fake);

                assertThat(status(result)).isEqualTo(OK);

                assertThat(contentType(result)).isEqualTo("text/html");

                assertThat(charset(result)).isEqualTo("utf-8");

                assertThat(contentAsString(result)).contains("Login");



            }
        });

    }*/
@Ignore
    public void testGiveLocation() throws Exception {
//#
    }
@Ignore
    public void testCollectItem() throws Exception {

    }
@Ignore
    public void testGetItems() throws Exception {

    }
@Ignore
    public void testGetObjects() throws Exception {

    }
@Ignore
    public void testGetLocationMessage() throws Exception {

    }
@Ignore
    public void testGetCharIngame() throws Exception {

    }
@Ignore
    public void testGetCharInteraction() throws Exception {

    }
@Ignore
    public void testGetCombination() throws Exception {

    }
    @Ignore
    public void testGetAction() throws Exception {

    }
    @Ignore
    public void testInteractWithNPC() throws Exception {

    }
    @Ignore
    public void testCreateGame() throws Exception {

    }

    @Test
    public void testParseFromJS() throws Exception {
        List<String> list = new Connections().parseFromJS("test");
        String actual = String.valueOf(list);


        String expected = "[]";

        assertEquals(expected, actual);
    }

    /*@Test
    public void testParseForJS() throws Exception {
        List<String> listOfstrings;
        listOfstrings = new ArrayList<String>(Arrays.asList("test1","test2")) ;



            String string = new Connections().parseForJS(listOfstrings);




            String expected = "test1-test2-";

            assertEquals(expected, string);
        }
*/
    @Ignore
    public void testGiveLocationTest() throws Exception {

    }
}