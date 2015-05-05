package controllers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Maik Wandrei on 27.04.2015.
 */
public class ItemParser
{
    static String[] oldArray =
            {
                    "rope",
                    "hook",
                    "honeycomb",
                    "torch"
            };
    static String[] messageOldArray =
            {
                    "It's a rope!",
                    "It's a hook!",
                    "Torch! So hot right now!",
                    "It's beepuke!"
            };
    static String[] newArray =
            {
                    "rope",
                    "hook",
                    "honeycomb",
                    "flashlight"
            };
    static String[] messageNewArray =
            {
                    "It's a rope!",
                    "It's a hook!",
                    "Let there be light!",
                    "It's beepuke!"
            };

    static ArrayList<String> oldEra = new ArrayList<String>(Arrays.asList(oldArray));
    static ArrayList<String> oldMessages = new ArrayList<String>(Arrays.asList(messageOldArray));
    static ArrayList<String> newEra = new ArrayList<String>(Arrays.asList(newArray));
    static ArrayList<String> newMessages = new ArrayList<String>(Arrays.asList(messageNewArray));

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
}
