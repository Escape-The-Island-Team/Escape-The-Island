package controllers;

import models.User;
import play.mvc.*;
import views.html.*;

import java.sql.Date;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Success"));
    }

    public static Result login() {
        return ok(login.render("Success"));
    }

    public static Result initial() {
        return ok(index.render("Success"));
    }

    public static Result register() {
        return ok(register.render("Success"));
    }

    public static Result editProfile() {
        return ok(editProfile.render("Success"));
    }

    public static Result characterSelection() {
        return ok(characterSelection.render("Success"));
    }

    public static Result about() { return ok(about.render("Success")); }

    public static Result contact() { return ok(contact.render("Success")); }

    public static Result addUser()
    {
        User user = new User();

        user.nickname = "guenther";
        user.password_hash = "jgkahskfdhgkjashdfkjg";
        user.password_salt = "hjgkahskjdfhgkjsh";
        user.e_mail = "guenther@web.de";
        user.time_creation = new Date(System.currentTimeMillis());
        user.time_password = new Date(System.currentTimeMillis());

        user.save();

        return ok(login.render("ok"));
    }

    public static Result displayUser()
    {
        User user = User.findByUsername("guenther");

        if(user == null)
        {
            return ok("not found");
        }

        return ok(user.nickname);
    }

}
