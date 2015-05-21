package controllers;

import models.Item;
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
                "beehive"
            };
    private final static ArrayList<String> objects = new ArrayList<String>(Arrays.asList(objectArray));

    private final static String[] locationArray =
            {
                    "beachLeft",
                    "beachRight",
                    "cave",
                    "cave"
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

    public static List<String> getObjects(String currentLocation)
    {
        List<String> locationObjects = new ArrayList<>();

        for(int index = 0; index < locations.size(); index++)
        {
            String location = locations.get(index);

            if (location.equals(currentLocation))
            {
                locationObjects.add(objects.get(index));
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
}
