package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

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

}
