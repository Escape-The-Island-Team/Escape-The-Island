package controllers;

import models.Item;
import models.NPC;
import models.Object;
import models.Character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maik Wandrei on 27.04.2015.
 */
public class ObjectParser
{
    // TODO go on putting in dem objects on locations
    private final static String[] objectArray =
            {
                    "stick",
                    "flintstones",
                    "bear",
                    "beehive",
                    "volcanicStones",
                    "doorHinges",
                    "sailCloth",
                    "fruit",
                    "flower",
                    "bottleEmpty",
                    "treasureChest",
                    "valve",
                    "cloth",
                    "stickHatchet",
                    "stickFishingPole",
                    "fish",
                    "clearWater",
                    "fiberCrop1",
                    "fiberCrop2",
                    "fiberCrop3"
            };
    private final static ArrayList<String> objects = new ArrayList<String>(Arrays.asList(objectArray));

    private final static String[] locationArray =
            {
                    "beachLeft",
                    "beachRight",
                    "cave",
                    "cave",
                    "volcano",
                    "cliff",
                    "cliff",
                    "jungle",
                    "opening",
                    "beachRight",
                    "lake",
                    "laboratory",
                    "rocks",
                    "jungle",
                    "waterfall",
                    "lake",
                    "waterfall",
                    "river",
                    "jungle"
            };
    private final static ArrayList<String> locations = new ArrayList<String>(Arrays.asList(locationArray));

    public static boolean isItem(String objectName, boolean old)
    {
        if (old)
        {
            switch (objectName)
            {
                case "bottle":
                case "torch" : return true;
            }

            return false;
        }

        switch (objectName)
        {
            case "bottle":
            case "torch" : return true;
        }

        return false;
    }

    public static boolean isObject(String objectName)
    {
        return objects.contains(objectName);
    }

    public static List<String> getObjects(String currentLocation, int old)
    {
        List<String> locationObjects = new ArrayList<>();

        for(int index = 0; index < locations.size(); index++)
        {
            String location = locations.get(index);

            if (location.equals(currentLocation))
            {
                String object = objects.get(index);

                if (object.equals("valve") && old == 1)
                {
                    continue;
                }

                locationObjects.add(object);
            }
        }

        return locationObjects;
    }

    public static String useObject(String objectName, List<String> items, long charId)
    {
        if (items.size() > 1)
        {
            return "These items cannot be used on this object.";
        }

        String item = "";

        if (items.size() == 1)
        {
            item = items.get(0);
        }

        switch (objectName)
        {
        case "beehive":
            return useBeehive(item, charId);
        case "bear":
            return useBear(item, charId);
        case "fish":
            return useFish(item, charId);
        case "fiberCrop1":
        case "fiberCrop2":
        case "fiberCrop3":
            return useFiberCrop(objectName, item, charId);
        case "thicket":
            return useThicket(item, charId);
        default:
            return "Error";
        }
    }

    public static String useBeehive(String item, long charId)
    {
        if (item.equals(""))
        {
            return "The bees in this beehive are buzzing happily in the sun and produce delicious honey.";
        }

        if (item.equals("torch"))
        {
            if (!Item.characterHoldsItem("torch", charId))
            {
                return "You filthy little javascript manipulator";
            }

            if (Item.itemCollected("honeycomb", charId))
            {
                return "You cannot get more honeycomb out of this beehive.";
            }

            Item.collectItem("honeycomb", charId);

            return "The fire scared the bees away. You took a honeycomb out of the beehive.";
        }

        return "This item cannot be used on the beehive.";
    }

    public static String useBear(String item, long charId)
    {
        if (item.equals(""))
        {
            return "This huge bear looks really dangerous.";
        }

        if (item.equals("honeycomb"))
        {
            if (!Item.characterHoldsItem("honeycomb", charId))
            {
                return "You filthy little javascript manipulator";
            }

            List<String> remove = new ArrayList<>();
            remove.add("honeycomb");

            Item.removeItems(remove, charId);

            Object.useObject("bear", Character.findById(charId).game_id);

            return "The bear likes honey a lot. You have enticed the bear away with the honeycomb.[bear]";
        }

        return "This item cannot be used on the bear.";
    }

    public static String useFish(String item, long charId)
    {
        if (item.equals(""))
        {
            return "This is a yellow fish. You will not be able to catch it so easily.";
        }

        if (item.equals("fishingPole"))
        {
            if (!Item.characterHoldsItem("fishingPole", charId))
            {
                return "You filthy little javascript manipulator";
            }

            Object.useObject("fish", Character.findById(charId).game_id);

            return "You managed to cath the yellow fish with the rope! Congratulations![fish]";
        }

        return "This item cannot be used on the fish.";
    }

    public static String useFiberCrop(String object, String item, long charId)
    {
        long gameId = Character.findById(charId).game_id;
        int mayaQuestStatus = NPC.getStatus(Character.findById(charId).game_id, "maya");

        if (mayaQuestStatus < 3)
        {
            return "These are fiber crops. Their stalks can be quite strong.";
        }

        if (item.equals(""))
        {
            int number = object.charAt(object.length() - 1) - 48;

            if (Object.objectIsUsed(object, gameId))
            {
                return "You already have enough cords at the moment.";
            }

            for (int index = 1; index < 4; index++)
            {
                if (Item.characterHoldsItem("fiberCords" + index, charId))
                {
                    return "These are fiber crops. Their stalks can be quite strong.";
                }
            }

            Item.collectItem("fiberCords" + number, charId);
            Object.useObject(object, gameId);

            return "You have created some cords out of these fiber crops![" + object + "]";
        }

        return "You can't use that item on the fiber crop";
    }

    public static String useThicket(String item, long charId)
    {
        if (item.equals(""))
        {
            return "This thicket contains very good lumber.";
        }

        if (item.equals("hatchet"))
        {
            Item.collectItem("lumber", charId);
            Object.useObject("thicket", Character.findById(charId).game_id);

            return "You chopped some lumber out of the thicket.";
        }

        return "You can't use that item on the thicket!";
    }
}
