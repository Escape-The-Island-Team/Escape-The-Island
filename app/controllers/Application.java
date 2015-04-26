package controllers;

import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(""));
    }

    public static Result login() {
        if(session("nickname") == null || session("nickname").equals(""))
        {
            return ok(login.render(""));
        }
        else
        {
            return redirect(routes.Application.index());
        }
    }

    public static Result initial() {
        return ok(index.render(""));
    }

    public static Result register() {
        if(session("nickname") == null || session("nickname").equals(""))
        {
            return ok(register.render(""));
        }
        else
        {
            return redirect(routes.Application.index());
        }
    }

    public static Result editProfile() {
        if(session("nickname") == null || session("nickname").equals(""))
        {
            return redirect(routes.Application.login());
        }
        else
        {
            return ok(editProfile.render(""));
        }
    }

    public static Result characterSelection() {
        if(session("nickname") == null || session("nickname").equals(""))
        {
            return redirect(routes.Application.login());
        }
        else
        {
            return ok(characterSelection.render(""));
        }
    }

    public static Result about() { return ok(about.render("")); }

    public static Result contact() { return ok(contact.render("")); }

    public static Result termsofuse() { return ok(termsofuse.render(""));}

    //public static Result loadingGame() { return ok(gameSurface.render("")); }


    public static Result loadBeachMid() { return ok(locBeachMid.render("")); }
    public static Result loadBeachLeft() { return ok(locBeachLeft.render("")); }
    public static Result loadBeachRight() { return ok(locBeachRight.render("")); }
    public static Result loadJungle() { return ok(locJungle.render("")); }

    //public static Result loadingGame() { return ok(loadingScreen.render("")); }

    //public static Result loadingScreen() { return ok(gameSurface.render(""));}
}
