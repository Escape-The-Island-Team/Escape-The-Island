package controllers;

import com.avaje.ebean.ExpressionList;
import models.*;
import models.Character;
import play.mvc.Controller;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 * Created by Maik Wandrei on 27.04.2015.
 */
public class GameManager extends Controller
{
    /**
     *
     * @param locationParams: there are several combinations always consiting of two elements inside the list
     *                      first: 2 locations where the first string is the start location and the second one the target
     *                      second: first string is "startNewGame" then the second one is the selected character
     *                      thirds: first string is "loadGame" then the second one contains the game_id
     * @return: the list contains first the target locations with string "accessGranted" or accessDenied" appended,
     *          the following string indicates how much objects follow, the following string are the objects on the
     *          location, afterwards the list contains the charactername of the game, then the number of items the character
     *          and all the items itself
     *
     */
    static List<String> getLocation(List<String> locationParams)
    {

        if (locationParams != null || locationParams.size() < 2)
        {
            List<String> result = new ArrayList<>();

            result.add("ErrorNotEnoughParams");

            return result;
        }

        if (locationParams.get(0).equals("startNewGame"))
        {
            String character = locationParams.get(1);

            if (CharacterParser.isCharacter(character))
            {
                return GameManager.newGame(character);
            }

            List<String> result = new ArrayList<>();

            result.add("ErrorCharacterUnknown");

            return result;
        }

        if (locationParams.get(0).equals("loadNewGame"))
        {
            return GameManager.loadGame(locationParams.get(1));
        }

        if (LocationParser.pathExists(locationParams.get(0), locationParams.get(1)))
        {
            String targetLocation = locationParams.get(1);

            List<String> result = new ArrayList<>();

            result.add(targetLocation + "Available");   // location

            String game_id = session().get("game_id");

            long parsedId;

            try
            {
                parsedId = Long.parseLong(game_id);
            }
            catch (Exception exc)
            {
                result.clear();
                result.add("ErrorParseGameId");
                return result;
            }

            Character currentCharacter = Character.findByGameId(parsedId);

            if (currentCharacter == null)
            {
                result.clear();

                result.add("ErrorGameNotFound");

                return result;
            }

            result.add(currentCharacter.name);

            List<String> objects = LocationParser.getObjects(currentCharacter.position, currentCharacter.old);

            String objectsString = "";

            for(String object: objects)
            {
                objectsString += object;                         // objects
            }

            result.add(objectsString);
            result.add(currentCharacter.name);

            /*
            List<String> npcs = LocationParser.getNpcs(targetLocation);

            for(String npc: npcs)
            {
                result.add("npc_" + npc);
            }

            if (!session().containsKey("game_id"))
            {
                result.clear();
                result.add("ErrorNoGameLoaded");
                return result;
            }
            */

            currentCharacter.position = targetLocation;
            currentCharacter.save();

            return result;
        }

        List<String> result = new ArrayList<>();

        result.add("ErrorInvalidLocationChange");

        return result;
    }

    public static List<String> newGame(String selectedChar)
    {
        List<String> currentGames = GameManager.getGames();
        List<String> result = new ArrayList<>();

        // check if character already used
        for (int i = 2; i < currentGames.size(); i += 2)
        {
            if (currentGames.get(i).equals(selectedChar))
            {
                result.add("ErrorCharacterAlreadyUsed");

                return result;
            }
        }

        User currentUser = User.findByUsername(session().get("username"));

        if (currentUser == null)
        {
            result.add("ErrorUserUnknown");

            return result;
        }

        Game newGame = new Game();

        newGame.user_id = currentUser.id;
        newGame.completed = false;
        newGame.start_time = new Date(System.currentTimeMillis());

        newGame.save();

        Character newCharacter = new Character();

        newCharacter.action_points = 50;
        newCharacter.game_id = newGame.id;
        newCharacter.name = selectedChar;

        try
        {
            newCharacter.old = CharacterParser.isOld(selectedChar);
        }
        catch (InvalidParameterException exc)
        {
            result.add("ErrorUnknown");

            return result;
        }

        String startPosition = "beachMid";
        newCharacter.position = startPosition;
        newCharacter.save();

        result.add(startPosition + "Available");

        List<String> objects = LocationParser.getObjects(startPosition, newCharacter.old);

        String objectsString = "";

        for(String object: objects)
        {
            objectsString += object;
        }

        result.add(objectsString);
        result.add(newCharacter.name);

        /*
        List<String> npcs = LocationParser.getNpcs(startPosition);

        result.add(Integer.toString(npcs.size()));

        for(String npc: npcs)
        {
            result.add("npc_" + npc);
        }
        */

        session().put("game_id", Long.toString(newGame.id));
        session().put("character_id", Long.toString(newCharacter.id));

        return result;
    }

    public static List<String> loadGame(String gameId)
    {
        List<String> result = new ArrayList<>();
        long parsedId = 0;

        try
        {
            parsedId = Long.parseLong(gameId);
        }
        catch (Exception exc)
        {
            result.add("ErrorParseGameId");

            return result;
        }

        Character loadedCharacter = Character.findByGameId(parsedId);

        if (loadedCharacter == null)
        {
            result.add("ErrorGameNotFound");

            return result;
        }

        result.add(loadedCharacter.position + "Available");

        List<String> objects = LocationParser.getObjects(loadedCharacter.position, loadedCharacter.old);

        String objectsString = "";

        for(String object: objects)
        {
            objectsString += object;
        }

        result.add(objectsString);
        result.add(loadedCharacter.name);

        /*
        List<String> npcs = LocationParser.getNpcs(loadedCharacter.position);

        result.add(Integer.toString(npcs.size()));

        for(String npc: npcs)
        {
            result.add(npc);
        }
        */

        session().put("game_id", Long.toString(parsedId));
        session().put("character_id", Long.toString(loadedCharacter.id));

        return result;
    }

    public static List<String> useObject(List<String> objectParams)
    {
        List<String> result = new ArrayList<>();

        if (objectParams == null || objectParams.size() < 1)
        {
            result.add("ErrorNotEnoughParams");

            return result;
        }

        String username = "";

        if (!session().containsKey("username"))
        {
            result.add("ErrorUnknownUser");

            return result;
        }

        username = session().get("username");

        long gameId = 0;

        try
        {
            gameId = Long.parseLong(session().get("game_id"));
        }
        catch (Exception exc)
        {
            result.add("ErrorUnknownGame");

            return result;
        }

        String object = objectParams.get(0);

        if (!session().containsKey("character_id"))
        {
            result.add("ErrorCharacterUnknown");

            return result;
        }

        boolean old = CharacterParser.isOld(session().get("character"));

        if (ObjectParser.isItem(object, old))
        {
            GameManager.pickItem(object, old);
        }

        //TODO finish him fatalaty

        throw new NotImplementedException();
    }

    public static List<String> pickItem(String item, boolean old)
    {
        List<String> result = new ArrayList<>();

        Item pickedItem = new Item();

        pickedItem.old = old;
        pickedItem.name = item;

        try
        {
            pickedItem.character_id = Long.parseLong(session().get("character_id"));
        }
        catch (Exception exc)
        {
            result.add("ErrorParsedCharacterId");

            return result;
        }

        pickedItem.save();

        ExpressionList<Item> backpack = Item.findByCharId(pickedItem.character_id);

        if (backpack == null || backpack.findList().size() == 0)
        {
            result.add("ErrorPickItem");

            return result;
        }

        result.add(Integer.toString(backpack.findList().size()));



        //TODO finish it so returning all dem items and stuffsch + change return lists to concatenate all items into one string and so on

        result.add("successful");

        throw new NotImplementedException();
    }

    /**
     * removes the game_id and character_id representing a quit of the game
     */
    public static void quitGame()
    {
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
