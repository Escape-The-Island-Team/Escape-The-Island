package controllersTest;

import junit.framework.TestCase;
import play.mvc.Result;
import play.test.FakeRequest;
import play.test.Helpers;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.status;

/***
 * Created by Alisa koksjuk on 24.05.2015
 * @testGetCharacterMassage: is not complete!!
 */
public class GameManagerTest extends TestCase {

   /* public void testGetCharacter() throws Exception {

        play.test.Helpers.running( play.test.Helpers.fakeApplication(), new java.lang.Runnable(){
            @Override
            public void run(){
                FakeRequest fake = new FakeRequest("GET", "/login");

                Result result = Helpers.callAction(controllers.routes.ref.Application.getCharacter(), fake);

                assertThat(status(result)).isEqualTo(OK);

                assertThat(Helpers.status(result)).isEqualTo(play.mvc.Http.Status.OK);

                assertThat(Helpers.contentType(result)).isEqualTo("text/html");

                assertThat(Helpers.charset(result)).isEqualTo("utf-8");

                // assertThat(Helpers.contentAsString(result)).contains("Login");


            }
        });
    }
*/
    public void testGetCharacterMessage() throws Exception {

    }

    public void testNewGame() throws Exception {

    }

    public void testLoadGame() throws Exception {

    }

    public void testLocationMessage() throws Exception {

    }

    public void testChangeLocation() throws Exception {

    }

    public void testCombineItems() throws Exception {

    }

    public void testGetBackpack() throws Exception {

    }

    public void testCollectItem() throws Exception {

    }

    public void testObjectsOnLocation() throws Exception {

    }

    public void testInteractWithObjects() throws Exception {

    }

    public void testTalkToNpc() throws Exception {

    }

    public void testQuitGame() throws Exception {

    }

    public void testGetGames() throws Exception {

    }
}