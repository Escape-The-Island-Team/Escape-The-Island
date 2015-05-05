package controllers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Maik Wandrei on 27.04.2015.
 */
public class ItemParser
{
    static String[] nameArray =
            {
                    "rope",
                    "hook"
            };
    static ArrayList<String> items = new ArrayList<String>(Arrays.asList(nameArray));

    public int getId(String locationName)
    {
        return items.indexOf(locationName);
    }

    public String getName(int id)
    {
        if (id <= items.size())
        {
            return "itm_" + items.get(id);
        }

        return "noSuchItem" + id;
    }
}
