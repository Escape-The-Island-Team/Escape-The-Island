package controllers;

import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(""));
    }

    public static Result login() {
        return ok(login.render(""));
    }

    public static Result initial() {
        return ok(index.render(""));
    }

    public static Result register() {
        return ok(register.render(""));
    }

    public static Result editProfile() {
        return ok(editProfile.render(""));
    }

    public static Result characterSelection() {
        return ok(characterSelection.render(""));
    }

    public static Result about() { return ok(about.render("")); }

    public static Result contact() { return ok(contact.render("")); }

    public static Result termsofuse() { return ok(termsofuse.render(""));}

}
