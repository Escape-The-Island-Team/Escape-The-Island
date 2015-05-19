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
/*
    public static List<String> useObject(List<String> objectParams)
    {
        List<String> result = new ArrayList<>();

        if (objectParams == null || objectParams.size() < 1)
        {
            result.add("ErrorNotEnoughParams");

            return result;
        }

        long gameId;

        try
        {
            String gameString = session().get("game_id");
            gameId = Long.parseLong(gameString);
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

        String character = session().get("character");
        boolean old = CharacterParser.isOld(character) == 1;

        if (ObjectParser.isItem(object, old))
        {
            return GameManager.pickItem(object, old);
        }

        if (ObjectParser.isObject(object, old))
        {
            Object gameObject = Object.findGameObjectByName(gameId, object);

            String receivedItemName = ObjectParser.givesItem(object, old);

            // when the object is not yet used and will return an item
            if (receivedItemName != null && (gameObject == null || !gameObject.used || gameObject.usages_left != 0))
            {
                String character_id = session().get("character_id");

                if (character_id == null)
                {
                    result.clear();
                    result.add("ErrorSessionCharacterId");
                    return result;
                }

                long parsedId;

                try
                {
                    parsedId = Long.parseLong(character_id);
                }
                catch (Exception exc)
                {
                    result.clear();
                    result.add("ErrorParseCharacterId");
                    return result;
                }

                Item receivedItem = new Item();
                receivedItem.character_id = parsedId;
                receivedItem.name = receivedItemName;
                receivedItem.old = old;

                if (gameObject == null)
                {
                    gameObject = new Object();

                    gameObject.game_id = gameId;
                    gameObject.name = object;
                    gameObject.old = old;
                    gameObject.used = true;

                    gameObject.save();
                }

                receivedItem.save();

                String message = ItemParser.message(object, old);

                if (message == null)
                {
                    result.clear();
                    result.add("ErrorUnknownItem");
                    return result;
                }

                result.add(message);

                List<String> itemStrings = getItems(parsedId);

                result.add(itemStrings.get(0));
                result.add(itemStrings.get(1));

                return result;
            }

            String message = ObjectParser.message(object, old);

            if (message == null)
            {
                result.clear();
                result.add("ErrorUnknownObject");
                return result;
            }

            result.add(message);

            return result;
        }

        result.add("ErrorUnknownObject");
        return result;
    }
/*
    public static List<String> pickItem(String item, boolean old)
    {
        List<String> result = new ArrayList<>();

        Item pickedItem = new Item();

        pickedItem.old = old;
        pickedItem.name = item;

        try
        {
            String characterId = session().get("character_id");
            pickedItem.character_id = Long.parseLong(characterId);
        }
        catch (Exception exc)
        {
            result.add("ErrorParsedCharacterId");

            return result;
        }

        pickedItem.save();

        try
        {
            List<String> items = GameManager.getItems(pickedItem.character_id);

            result.add("successful");
            result.add(items.get(0));
            result.add(items.get(1));
        }
        catch (NullPointerException exc)
        {
            result.add(exc.getMessage());
        }

        return result;
    }*/

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
    /*
    public static String getObjects(Character character)
    {
        List<String> objects = LocationParser.getObjects(character.position, character.old);
        ExpressionList<Object> usedObjects = Object.findUsedGameObjects(character.game_id);

        String objectsString = "";

        for(String object: objects)
        {
            if (ObjectParser.isItem(object, character.old) && usedObjects.contains("name", object) != null)
            {
                continue;
            }

            objectsString += object + " ";                         // objects
        }

        if (objectsString.endsWith(" "))
        {
            objectsString = objectsString.substring(0, objectsString.length() - 1);
        }

        return objectsString;
    }

    public static List<String> getItems(long character_id)
    {
        List<String> result = new ArrayList<>();
        List<Item> backpack;

        try
        {
            backpack = Item.findByCharId(character_id).findList();
        }
        catch (NullPointerException exc)
        {
            throw new NullPointerException("There was an error in your backpack!");
        }

        result.add(Integer.toString(backpack.size()));

        String itemString = "";

        for (Item item: backpack)
        {
            itemString += " " + item.name;
        }

        result.add(itemString);

        return result;
    }*/
}