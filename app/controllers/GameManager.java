package controllers;

import com.avaje.ebean.ExpressionList;
import models.*;
import play.mvc.Controller;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maik Wandrei on 27.04.2015.
 */
public class GameManager extends Controller
{
    /**
     *
     * @param locationParams: first either "startNewGame" for a new game, "loadGame" for loading a game or the current location
     *                        second one either selected char, the game id or the target location
     * @return: the list containing the location with suffice for either
     *          granted or denied access as well as all obj and npc to be placed
     */
    static List<String> getLocation(List<String> locationParams)
    {
        throw new NotImplementedException();
    }

    /**
     *
     * @return List containing the games where name of character and game id is contained
     *
     * List<String>{NumberOfGames, GameId1, CharName1, GameId2, CharName2, ...}
     */
    static List<String> getGames()
    {
        List<String> result = new ArrayList<String>();
        long userId = User.findByUsername(session("username")).id;
        ExpressionList<Game> query = Game.findByUserId(userId);

        if (query == null)
        {
            result.add(Integer.toString(0));
            return result;
        }

        result.add(Long.toString(query.findRowCount()));

        List<Game> listOfGames = query.findList();

        for(Game game: listOfGames)
        {
            if (game.completed)
            {
                continue;
            }

            models.Character character = models.Character.findByGameId(game.id);

            if(character != null)
            {
                result.add(Long.toString(game.id));
                result.add("Corrupt");
                continue;
            }

            result.add(Long.toString(game.id));
            result.add(character.name);
        }

        return result;
    }
}
