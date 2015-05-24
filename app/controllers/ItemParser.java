package controllers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Maik Wandrei on 27.04.2015.
 */
public class ItemParser
{
    final static String[] oldArray =
            {
                    "stick",
                    "flintstones"
            };
    final static String[] messageOldArray =
            {
                    "You found a stick. This could be useful.",
                    "There are some flintstones. Maybe you can use them to make fire."
            };

    final static String[] newArray =
            {
                    "stick",
                    "flintstones",
                    "volcanicStone",
                    "doorHinges",
                    "sailCloth",
                    "fruit",
                    "flower",
                    "bottleEmpty",
                    "treasureChest",
                    "valve",
                    "cloth",
                    "stickHatchet",
                    "stickFishingPole"
            };
    final static String[] messageNewArray =
            {
                    "You found a stick. This could be useful.",
                    "There are some flintstones. Maybe you can use them to make fire.",
                    "These are volcanic stones. They are lighter than expected.",
                    "Thee old door hinges are a bit rusty, but can still be used.",
                    "You found a large sail cloth. Undoubtable this had been the sail of a ship that crashed on the rocks.",
                    "You found a fruit, but you have never seen this type of fruit before.",
                    "This beautiful flower could make someone happy.",
                    "You found an empty bottle. Maybe you can put something inside.",
                    "You found a treasure chest! It is very heavy and cannot be opened.",
                    "This is a valve. It can be used to regulate the flow of water.",
                    "It seems that this piece of cloth had been part of a garment.",
                    "You found a thick and stable stick. Maybe you can use it to build a tool.",
                    "You found a long and stable stick. this could be useful."
            };

    final static ArrayList<String> oldEra = new ArrayList<String>(Arrays.asList(oldArray));
    final static ArrayList<String> oldMessages = new ArrayList<String>(Arrays.asList(messageOldArray));
    final static ArrayList<String> newEra = new ArrayList<String>(Arrays.asList(newArray));
    final static ArrayList<String> newMessages = new ArrayList<String>(Arrays.asList(messageNewArray));

    public int getId(String itemName, boolean old)
    {
        if (old)
        {
            return oldEra.indexOf(itemName);
        }

        return newEra.indexOf(itemName);
    }

    public String getName(int id, boolean old)
    {
        if (old)
        {
            if (id <= oldEra.size())
            {
                return oldEra.get(id);
            }

            return "noSuchItem" + id;
        }

        if (id <= newEra.size())
        {
            return newEra.get(id);
        }

        return "noSuchItem" + id;
    }

    public static String message(String itemName, boolean old)
    {
        if (old)
        {
            int itemIndex = oldEra.indexOf(itemName);

            if (itemIndex < 0)
            {
                return "noSuchItem";
            }

            return oldMessages.get(itemIndex);
        }

        int itemIndex = newEra.indexOf(itemName);

        if (itemIndex < 0)
        {
            return "noSuchItem";
        }

        return newMessages.get(itemIndex);
    }

    public static boolean isItem(String item)
    {
        if (oldEra.contains(item) || newEra.contains(item))
        {
            return true;
        }

        return false;
    }
}
