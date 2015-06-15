package controllersTest;

import junit.framework.TestCase;
import org.junit.Ignore;
import play.mvc.Result;
import play.mvc.Controller;
import play.test.FakeRequest;
import play.test.Helpers;
import views.html.register;
import play.test.Helpers.*;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.status;

/**
 * Created by Alisa Koksjuk on 18.05.15.
 */
public class ApplicationTest  {
/*@Test
    public void testIndex() throws Exception {

        play.test.Helpers.running( play.test.Helpers.fakeApplication(), new java.lang.Runnable(){
            @Override
            public void run(){
                FakeRequest fake = new FakeRequest("GET", "/login");
               // fake.withSession("username","username");
                Result result = Helpers.callAction(controllers.routes.ref.Application.index(), fake);

                assertThat(status(result)).isEqualTo(OK);

                assertThat(Helpers.status(result)).isEqualTo(play.mvc.Http.Status.OK);

                assertThat(Helpers.contentType(result)).isEqualTo("text/html");

                assertThat(Helpers.charset(result)).isEqualTo("utf-8");

                // assertThat(Helpers.contentAsString(result)).contains("Login");


            }
        });

    }
@Test
    public void testLogin() throws Exception {

        play.test.Helpers.running(play.test.Helpers.fakeApplication(), new java.lang.Runnable() {

            @Override
            public void run() {
                // play.mvc.Controller.session().put("nickname",null);

                Result result = Helpers.callAction(controllers.routes.ref.Application.index());

                assertThat(Helpers.status(result)).isEqualTo(play.mvc.Http.Status.OK);

                assertThat(Helpers.contentType(result)).isEqualTo("text/html");

                assertThat(Helpers.charset(result)).isEqualTo("utf-8");

                assertThat(Helpers.contentAsString(result)).contains("Login");


            }
        });

    }
    public void testInitial() throws Exception {

    }
    @Test
    public void testRegister()  throws Exception {
        play.test.Helpers.running( play.test.Helpers.fakeApplication(), new java.lang.Runnable(){

            @Override
            public void run(){
                play.mvc.Controller.session().put("nickname",null);

                Result result = Helpers.callAction(controllers.routes.ref.Application.register()); //or index ()?

                assertThat(Helpers.status(result)).isEqualTo(play.mvc.Http.Status.OK);

                assertThat(Helpers.contentType(result)).isEqualTo("text/html");

                assertThat(Helpers.charset(result)).isEqualTo("utf-8");

                assertThat(Helpers.contentAsString(result)).contains("Register");


            }
      });






        //Result res = new Application().register();
        //assertEquals(expected, actual);
    }
    @Test
    public void testEditProfile() throws Exception {

            play.test.Helpers.running( play.test.Helpers.fakeApplication(), new java.lang.Runnable(){

                @Override
                public void run(){
                    play.mvc.Controller.session().put("nickname",null);

                    Result result = Helpers.callAction(controllers.routes.ref.Application.login());

                    assertThat(Helpers.status(result)).isEqualTo(play.mvc.Http.Status.OK);

                    assertThat(Helpers.contentType(result)).isEqualTo("text/html");

                    assertThat(Helpers.charset(result)).isEqualTo("utf-8");

                    assertThat(Helpers.contentAsString(result)).contains("Edit Your Profile");


                }
            });
    }

    @Test
    public void testCharacterSelection() throws Exception {

        play.test.Helpers.running( play.test.Helpers.fakeApplication(), new java.lang.Runnable(){

            @Override
            public void run(){
                play.mvc.Controller.session().put("nickname",null);

                Result result = Helpers.callAction(controllers.routes.ref.Application.login());

                assertThat(Helpers.status(result)).isEqualTo(play.mvc.Http.Status.OK);

                assertThat(Helpers.contentType(result)).isEqualTo("text/html");

                assertThat(Helpers.charset(result)).isEqualTo("utf-8");

                //assertThat(Helpers.contentAsString(result)).contains("Edit Your Profile");


            }
        });
    }

@Test
    public void testHome() throws Exception{
        play.test.Helpers.running( play.test.Helpers.fakeApplication(), new java.lang.Runnable(){

            @Override
            public void run(){
                play.mvc.Controller.session().put("username",null);

                Result result = Helpers.callAction(controllers.routes.ref.Application.login());

                assertThat(Helpers.status(result)).isEqualTo(play.mvc.Http.Status.OK);

                assertThat(Helpers.contentType(result)).isEqualTo("text/html");

                assertThat(Helpers.charset(result)).isEqualTo("utf-8");

                //assertThat(Helpers.contentAsString(result)).contains("Edit Your Profile");


            }
        });
    }
*/
@Ignore
    public void testAbout() throws Exception {


    }


@Ignore
    public void testContact() throws Exception {

    }
@Ignore
    public void testTermsofuse() throws Exception {

    }
    @Ignore
    public void testLoadBeachMid() throws Exception {

    }
    @Ignore
    public void testLoadBeachLeft() throws Exception {

    }
    @Ignore
    public void testLoadBeachRight() throws Exception {

    }
    @Ignore
    public void testLoadJungle() throws Exception {

    }
    @Ignore
    public void testDeleteAccount() throws Exception {

    }
}