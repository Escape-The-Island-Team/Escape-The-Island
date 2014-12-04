package controllers;

import play.mvc.*;
import models.User;
import java.util.Date;

/**
 * Created by Maik Wandrei on 03.12.2014.
 */
public class Tester extends Controller
{
    public static Result test()
    {
        User usertest = new User();

        usertest.e_mail = "test@web.de";
        usertest.nickname = "peter";
        usertest.password_hash = "passwordhashing";
        usertest.password_salt = "passwordsalting";
        usertest.time_creation = new Date(System.currentTimeMillis());
        usertest.time_password = new Date(System.currentTimeMillis());

        usertest.save();

        User userfind = User.findByEmail("test@web.de");

        if(userfind == null)
        {
            return ok("no user found");
        }
        else
        {
            String answer = "";
            answer += userfind.nickname + "; ";
            answer += userfind.e_mail +  "; ";
            answer += userfind.id + "; ";
            answer += userfind.time_creation.toString() + "; ";
            answer += userfind.password_hash;
            return ok(answer);
        }
    }
}
