package controllers;

import com.avaje.ebean.ExpressionList;
import models.*;
import models.Character;
import models.Object;
import play.mvc.Controller;

import java.security.InvalidParameterException;
import java.util.*;
import java.sql.Date;

/**
 * Created by Maik Wandrei on 27.04.2015.
 */
public class GameManager extends Controller
{
    public static List<String> getCharacter()
    {
        String username = session().get("username");
        long userId = User.findByUsername(username).id;
        long gameId= Game.findActive(userId).id;

        Character loadedCharacter = Character.findByGameId(gameId);

        List<String> character = new ArrayList<String>();
        character.add(loadedCharacter.name);
        return character;
    }

    public static List<String> getCharacterMessage()
    {
        String username = session().get("username");
        long userId = User.findByUsername(username).id;
        long gameId= Game.findActive(userId).id;
        Character loadedCharacter = Character.findByGameId(gameId);
        int message = Character.getNextMessage(loadedCharacter.id);

        List<String> result = new ArrayList<>();

        result.add(CharacterMessages.getMessage(loadedCharacter.name, message));

        return result;
    }

    public static List<String> newGame(String selectedChar)
    {
        List<String> result = new ArrayList<String>();

        String username = session().get("username");

        User currentUser = User.findByUsername(username);

        if (currentUser == null)
        {
            result.add("ErrorUserUnknown");
            return result;
        }

        /*if (Game.findIncomplete(selectedChar, currentUser.id))
        {
            result.add("ErrorCharacterAlreadyUsed");
            return result;
        }*/

        Game newGame = new Game();

        newGame.user_id = currentUser.id;
        newGame.completed = 0;
        newGame.start_time = new Date(System.currentTimeMillis());
        newGame.active = 0;

        newGame.save();

        Character newCharacter = new Character();

        newCharacter.action_points = 50;
        newCharacter.game_id = newGame.id;
        newCharacter.name = selectedChar;

        newCharacter.old = CharacterParser.isOld(selectedChar);

        String startPosition = "beachMid";
        newCharacter.position = startPosition;
        newCharacter.save();

        return loadGame(Long.toString(newGame.id));
    }

    public static List<String> loadGame(String gameId)
    {
        List<String> result = new ArrayList<>();
        long parsedId;

        try
        {
            parsedId = Long.parseLong(gameId);
        }
        catch (Exception exc)
        {
            result.add("ErrorParseGameId");

            return result;
        }

        System.out.println(parsedId);

        Character loadedCharacter = Character.findByGameId(parsedId);

        if (loadedCharacter == null)
        {
            result.add("ErrorGameNotFound");

            return result;
        }

        Game.setGameActive(loadedCharacter.game_id);

        result.add("successful");

        return result;
    }

    public static List<String> locationMessage(List<String> location)
    {
        System.out.println(location.get(0));

        List<String> result = new ArrayList<String>();

        if (location.size() < 1)
        {
            result.add("ErrorNoLocationRetrieved");
            return result;
        }

        String locationName = location.get(0);
        String username = session().get("username");
        long userId = User.findByUsername(username).id;
        long gameId = Game.findActive(userId).id;

        if (Location.newlyDiscovered(gameId, locationName) && !locationName.equals("beachMid"))
        {
            String message = LocationContent.getMessage(locationName);

            if (message == null)
            {
                result.add("ErrorInvalidLocation");
                return result;
            }

            result.add("MessageInfo");
            result.add(message);

            Location.visit(gameId, locationName);
        }

        return result;
    }

    /**
     * removes the game_id and character_id representing a quit of the game
     */
    public static void quitGame()
    {
        // TODO umschreiben
        if (session().containsKey("game_id"))
        {
            session().remove("game_id");
        }

        if (session().containsKey("character_id"))
        {
            session().remove("character_id");
        }
    }

    /**
     *
     * @return List containing the games where name of character and game id is contained
     *
     * List<String>{NumberOfGames, GameId1, CharName1, GameId2, CharName2, ...}
     */
    public static List<String> getGames()
    {
        List<String> result = new ArrayList<>();
        long userId = User.findByUsername(session("username")).id;
        List<Game> userGames = Game.findByUserId(userId);

        if (userGames == null)
        {
            result.add(Integer.toString(0));
            return result;
        }

        result.add(Integer.toString(userGames.size()));

        for(Game game: userGames)
        {
            Character character = Character.findByGameId(game.id);

            if(character == null)
            {
                game.delete();
                continue;
            }

            result.add(Long.toString(game.id));
            result.add(character.name);
        }

        return result;
    }
}