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

        List<String> result = new ArrayList<String>();

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

        NPC.createNpc(newGame.id, "maya", newCharacter.old);
        if (newCharacter.old == 1)
        {
            NPC.createNpc(newGame.id, "versutus", 1);
        }

        if (newCharacter.old == 0)
        {
            NPC.createNpc(newGame.id, "scientist", 0);
        }


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

    public static List<String> changeLocation(List<String> location)
    {
        String username = session().get("username");
        long userId = User.findByUsername(username).id;
        long gameId = Game.findActive(userId).id;
        Character character = Character.findByGameId(gameId);
        long characterId = character.id;

        List<String> result = new ArrayList<>();

        if (location.size() < 2)
        {
            result.add("ErrorChangeLocationParameter");
            return result;
        }

        // TODO anticheat ... check if character is at position
        String position = location.get(0);
        String target = location.get(1);

        String pathResult = LocationContent.pathChangeResult(position, target, username);

        if (pathResult.endsWith("Available"))
        {
            Character.changeLocation(characterId, target);

            result.add(pathResult);
            return result;
        }

        result.add("messageInfo");
        result.add(pathResult);
        return result;
    }

    public static List<String> combineItems(List<String> items)
    {
        System.out.println("combineItems");
        List<String> result = new ArrayList<String>();

        if (items == null)
        {
            result.add("messageInfo");
            result.add(ItemBlender.combineItems(new ArrayList<String>()));
            return result;
        }

        String item = ItemBlender.combineItems(items);

        if (item.contains("cannot"))
        {
            result.add("messageInfo");
            result.add(item);
            return result;
        }

        String username = session().get("username");
        long userId = User.findByUsername(username).id;
        long gameId = Game.findActive(userId).id;
        long characterId = Character.findByGameId(gameId).id;

        Item.collectItem(item, characterId);

        //TODO remove
        System.out.println("Created item: " + item);

        List<String> removeItems = ItemBlender.removeItems(item);

        if (removeItems.contains("fiberCords"))
        {
            for (int number = 1; number < 4; number++)
            {
                if (Item.characterHoldsItem("fiberCords" + number, characterId))
                {
                    removeItems.remove("fiberCords");
                    removeItems.add("fiberCords" + number);
                    break;
                }
            }
        }

        if (!Item.removeItems(removeItems, characterId))
        {
            result.add("messageInfo");
            result.add("You filthy little java script manipulator!");
            return result;
        }

        result.add("successful");
        result.add("messageInfo");
        result.add(ItemBlender.messageOfCombination(item));
        return result;
    }

    public static List<String> getBackpack()
    {
        String username = session().get("username");
        long userId = User.findByUsername(username).id;
        long gameId = Game.findActive(userId).id;
        long characterId = Character.findByGameId(gameId).id;

        List<String> items = Item.backpackContent(characterId);

        return items;
    }

    public static List<String> collectItem(List<String> itemParameters)
    {
        System.out.println("collectItem");
        List<String> result = new ArrayList<>();

        String username = session().get("username");
        long userId = User.findByUsername(username).id;
        long gameId = Game.findActive(userId).id;
        long characterId = Character.findByGameId(gameId).id;

        if (itemParameters.size() < 1)
        {
            result.add("ErrorToLittleParameters");
            return result;
        }

        String itemName = itemParameters.get(0);

        /*if (!ItemParser.isItem(itemName))
        {
            result.add("ErrorUnknownItem");
            return result;
        }*/

        if (Item.itemCollected(itemName, characterId))
        {
            result.add("ErrorJavaScriptManipulation");
            return result;
        }

        Item.collectItem(itemName, characterId);

        result.add("successful");
        result.add("messageInfo");
        result.add(ItemParser.message(itemName, false));
        return result;
    }

    public static List<String> objectsOnLocation(List<String> locationParameters)
    {
        String username = session().get("username");
        long userId = User.findByUsername(username).id;
        long gameId = Game.findActive(userId).id;
        Character character = Character.findByGameId(gameId);
        long characterId = character.id;

        List<String> result = new ArrayList<>();

        if (locationParameters.size() < 1)
        {
            result.add("EyDuArschGibMirMehr");
            return result;
        }
        String location = locationParameters.get(0);

        List<String> objects = ObjectParser.getObjects(location, character.old);

        List<Item> collectedItems = Item.getUsedItems(characterId);

        for(Item item: collectedItems)
        {
            if (objects.contains(item.name))
            {
                objects.remove(item.name);
            }
        }

        List<Object> usedObjects = Object.findUsedGameObjects(gameId);

        for (Object object: usedObjects)
        {
            if (objects.contains(object.name))
            {
                objects.remove(object.name);
            }
        }

        return objects;
    }

    public static List<String> interactWithObjects(List<String> objectParameter)
    {
        System.out.println("interact");
        List<String> result = new ArrayList<>();

        if (objectParameter.size() < 1)
        {
            result.add("ErrorDuBistDoof");
            return result;
        }

        String object = objectParameter.get(0);
        List<String> items = new ArrayList<>();

        for (int index = 1; index < objectParameter.size(); index++)
        {
            items.add(objectParameter.get(index));
        }

        String username = session().get("username");
        long userId = User.findByUsername(username).id;
        long gameId = Game.findActive(userId).id;
        long characterId = Character.findByGameId(gameId).id;

        String message = ObjectParser.useObject(object, items, characterId);

        if (message.equals("Error"))
        {
            result.add("notSuccessful");
            return result;
        }

        result.add("successful");

        if (message.contains("["))
        {
            int indexOpen = message.indexOf('[');
            int indexClose = message.indexOf(']');

            String remove = message.substring(indexOpen + 1, indexClose);
            message = message.substring(0, indexOpen);

            result.add("messageInfo");
            result.add(message);
            result.add("removeObject");
            result.add(remove);

            return result;
        }

        result.add("messageInfo");
        result.add(message);
        return result;
    }

    public static List<String> talkToNpc(List<String> npcParameter)
    {
        if (npcParameter.size() == 0)
        {
            List<String> result = new ArrayList<>();

            result.add("ErrorNoParameters");
            return result;
        }

        String username = session().get("username");
        long userId = User.findByUsername(username).id;
        long gameId = Game.findActive(userId).id;
        long characterId = Character.findByGameId(gameId).id;

        String npc = npcParameter.get(0);
        int status = NPC.getStatus(gameId, npc);


        //TODO remove
        System.out.println("Test before dialog");

        List<String> questList = DialogSystem.retieveMessage(npcParameter.get(0), status, characterId);

        if (!questList.get(3).equals("wait") && !questList.get(2).equals(""))
        {
            //TODO remove
            System.out.println("Test before collect");

            Item.collectItem(questList.get(2), characterId);
        }

        if (questList.get(3).equals("complete"))
        {
            //TODO remove
            System.out.println("Test remove items");

            List<String> remove = new ArrayList<>();
            remove.add(questList.get(1));

            Item.removeItems(remove, characterId);
        }

        if (!questList.get(3).equals("wait"))
        {
            //TODO remove
            System.out.println("Test before status");

            NPC.increaseStatus(gameId, npc);
        }

        List<String> result = new ArrayList<>();

        result.add(questList.get(0));

        //TODO: remove
        System.out.println("result "+result);
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
        List<String> result = new ArrayList<String>();
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