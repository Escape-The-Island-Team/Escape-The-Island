package controllers;

import models.*;
import models.Character;
import play.mvc.*;
import views.html.*;
import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(""));
    }

    public static Result login() {
        if(session("username") == null || session("username").equals(""))
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
        if(session().get("username") == null || session().get("username").equals(""))
        {
            return ok(register.render(""));
        }
        else
        {
            return redirect(routes.Application.index());
        }
    }

    public static Result editProfile() {
        if(session("username") == null || session("username").equals(""))
        {
            return redirect(routes.Application.login());
        }
        else
        {
            return ok(editProfile.render(""));
        }
    }

    public static Result characterSelection() {
        if(session("username") == null || session("username").equals(""))
        {
            return redirect(routes.Application.login());
        }
        else
        {
            return ok(characterSelection.render(""));
        }
    }

    public static Result home() {
        if(session("username") == null || session("username").equals(""))
        {
            return redirect(routes.Application.login());
        }
        else
        {
            return ok(home.render(""));
        }
    }

    public static Result statistics() {
        String statisticsBestCharacter="";
        String statisticsActionPoints="";

        if(session("username") == null || session("username").equals(""))
        {
            return redirect(routes.Application.login());
        }
        else
        {
            List<String> returnlist = new ArrayList<String>();
            returnlist = GameManager.getStatistics();

            statisticsBestCharacter = returnlist.get(0);
            statisticsActionPoints = returnlist.get(1);

            return ok(statistics.render("", statisticsBestCharacter, statisticsActionPoints));
        }
    }

    public static Result loadGame() {
        if(session("username") == null || session("username").equals(""))
        {
            return redirect(routes.Application.login());
        }
        else
        {
            return ok(loadGame.render(""));
        }
    }

    public static Result about() { return ok(about.render("")); }

    public static Result contact() { return ok(contact.render("")); }

    public static Result termsofuse() { return ok(termsofuse.render(""));}

    //public static Result loadingGame() { return ok(gameSurface.render("")); }
    //public static Result loadingGame() { return ok(loadingScreen.render("")); }
    //public static Result loadingScreen() { return ok(gameSurface.render(""));}

    public static Result loadLocation()
    {
        String username = session().get("username");
        long userId = User.findByUsername(username).id;
        long gameId = Game.findActive(userId).id;
        Character character = Character.findByGameId(gameId);
        String location = character.position;

        switch (location)
        {
            case "beachMid": return ok(locBeachMid.render(""));
            case "beachLeft": return ok(locBeachLeft.render(""));
            case "beachRight": return ok(locBeachRight.render(""));
            case "jungle": return ok(locJungle.render(""));
            case "temple": return ok(locTemple.render(""));
            case "river": return ok(locRiver.render(""));
            case "opening": return ok(locOpening.render(""));
            case "lake": return ok(locLake.render(""));
            case "cave": return ok(locCave.render(""));
            case "cliff": return ok(locCliff.render(""));
            case "rocks": return ok(locRocks.render(""));
            case "volcano": return ok(locVolcano.render(""));
            case "waterfall": return ok(locWaterfall.render(""));
            case "treehouse": return ok(locTreehouse.render(""));
            case "laboratory": return ok(locLaboratory.render(""));
        }

        return ok(locBeachMid.render(""));
    }

    public static Result loadEscapeScreen() { return ok(escapeScreen.render("")); }

    public static Result loadImg() { return ok(); }
}
