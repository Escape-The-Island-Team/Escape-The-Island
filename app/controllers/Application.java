package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        String url = request().path().toString();
        if (url.equals("/index.scala.html")) {
            return ok(index.render("Success"));
        }
        else return redirect("login.scala.html");
    }

    public static Result login() {
        return ok(login.render());
    }

    public static Result initial() {
        return ok(index.render("Success"));
    }

    public static Result register() {
        return ok(register.render("Success"));
    }

    public static Result editProfile() {
        return ok(editProfile.render());
    }

    public static Result characterSelection() {
        return ok(characterSelection.render());
    }

}
