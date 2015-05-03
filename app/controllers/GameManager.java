package controllers;

import com.avaje.ebean.ExpressionList;
import models.*;
import models.Character;
import play.mvc.Controller;

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
     * @param locationParams: first either "startNewGame" for a new game, "loadGame" for loading a game or the current location
     *                        second one either selected char, the game id or the target location
     * @return: the list containing the location with suffice for either
     *          granted or denied access as well as all obj and npc to be placed
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

            result.add("loc_" + targetLocation + "_AccessGranted");

            List<String> objects = LocationParser.getObjects(targetLocation);

            for(String object: objects)
            {
                result.add("obj_" + object);
            }

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

            String game_id = session().get("game_id");

            long parsedId = 0;

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
                result.add("ErrorCharacterNotFound");
                return result;
            }

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

        String startPosition = "beach_mid";
        newCharacter.position = startPosition;
        newCharacter.save();

        result.add("loc" + startPosition + "_AccessGranted");

        List<String> objects = LocationParser.getObjects(startPosition);

        for(String object: objects)
        {
            result.add("obj_" + object);
        }

        List<String> npcs = LocationParser.getNpcs(startPosition);

        for(String npc: npcs)
        {
            result.add("npc_" + npc);
        }

        session().put("game_id", Long.toString(newGame.id));

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

        result.add("loc_" + loadedCharacter.position + "_AccessGranted");

        List<String> objects = LocationParser.getObjects(loadedCharacter.position);

        for(String object: objects)
        {
            result.add("obj_" + object);
        }

        List<String> npcs = LocationParser.getNpcs(loadedCharacter.position);

        for(String npc: npcs)
        {
            result.add("npc_" + npc);
        }

        session().put("game_id", Long.toString(parsedId));

        return result;
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
