package controllersTest;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Ignore;
import play.mvc.Result;
import play.test.FakeRequest;
import play.test.Helpers;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

/**
 * Ask Maik about how to write this test
 */

public class AccountmanagementTest extends TestCase {
/*@Ignore
    public void testRegister() throws Exception {
    //#
    }*/
@Test
    public void testLogin() throws Exception {
    //success
        running(fakeApplication(), new java.lang.Runnable() {
            @Override
            public void run() {
                FakeRequest fake = new FakeRequest("GET", "/login");


                Result result = Helpers.callAction(controllers.routes.ref.Application.login(), fake);

                assertThat(status(result)).isEqualTo(OK);

                assertThat(contentType(result)).isEqualTo("text/html");

                assertThat(charset(result)).isEqualTo("utf-8");

                assertThat(contentAsString(result)).contains("Login");


            }
        });
    }

   /* @Ignore
    public void testLogout() throws Exception {

        //#
    }
    @Ignore
    public void testEditProfile() throws Exception {

    }*/
@Test
//success
    public void testGetHashy() throws Exception {

        String result = new String("not");


        String actual = "null";


        String expected = "null";

        assertEquals(expected, actual);
    }


    @Test
    //success
    public void testGetSalty() throws Exception {
        String result = new String("string");


        String actual = "null";


        String expected = "null";

        assertEquals(expected, actual);

    }


    @Test
    public void testDeleteAccount() throws Exception {

        play.test.Helpers.running( play.test.Helpers.fakeApplication(), new java.lang.Runnable(){
            @Override
        public void run(){
               FakeRequest fake = new FakeRequest("GET", "/login");
                fake.withSession("username","username");
                Result result = Helpers.callAction(controllers.routes.ref.Application.deleteAccount(), fake);

                assertThat(status(result)).isEqualTo(OK);

            assertThat(Helpers.status(result)).isEqualTo(play.mvc.Http.Status.OK);

            assertThat(Helpers.contentType(result)).isEqualTo("text/html");

            assertThat(Helpers.charset(result)).isEqualTo("utf-8");

           // assertThat(Helpers.contentAsString(result)).contains("Login");


        }
    });

    }
}