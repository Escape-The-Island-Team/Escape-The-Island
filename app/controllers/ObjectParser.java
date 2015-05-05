package controllers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Maik Wandrei on 27.04.2015.
 */
public class ObjectParser
{
    static String[] nameArray =
            {
                    "chest",
                    "brush"
            };
    static ArrayList<String> objects = new ArrayList<String>(Arrays.asList(nameArray));

    public int getId(String objectName)
    {
        return objects.indexOf(objectName);
    }

    public String getName(int id)
    {
        if (id <= objects.size())
        {
            return "obj_" + objects.get(id);
        }

        return "noSuchObject" + id;
    }

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

    public static String needsItem(String objectName, boolean old)
    {
        if (old)
        {
            switch (objectName)
            {
                case "beehive": return "torch";
            }

            return null;
        }

        switch (objectName)
        {
            case "beehive": return "torch";
        }

        return null;
    }
}
