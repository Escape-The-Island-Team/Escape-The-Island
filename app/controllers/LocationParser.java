package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maik Wandrei on 27.04.2015.
 */
public class LocationParser
{
    static String[] nameArray =
        {
                "beachMid",
                "beachRight",
                "beachLeft",
                "jungle"
        };
    static ArrayList<String> locations = new ArrayList<String>(Arrays.asList(nameArray));

    // array containing all possible location changed: currentLocation_targetLocation
    static String[] pathArray =
            {
                    "beachMid_beachLeft",
                    "beachMid_beachRight",
                    "beachMid_Jungle",
                    "beachRight_beachMid",
                    "beachLeft_beachMid",
                    "jungle_beachMid"
            };
    static ArrayList<String> paths = new ArrayList<String>(Arrays.asList(pathArray));

    public static int getId(String locationName)
    {
        return locations.indexOf(locationName);
    }

    public static String getName(int id)
    {
        if (id <= locations.size())
        {
            return "loc_" + locations.get(id);
        }

        return "noSuchLocation" + id;
    }

    public static List<String> getObjects(String location)
    {
        List<String> result = new ArrayList<>();

        switch (location)
        {
        case "beachMid":   result.add("Stone");
                            break;
        case "beachRight": result.add("Banana");
                            break;
        case "beachLeft":  result.add("Bottle");
                            break;
        }

        return result;
    }

    public static List<String> getNpcs(String location)
    {
        List<String> result = new ArrayList<>();

        switch (location)
        {
        case "beachLeft":  result.add("Alisa");
                            break;
        }

        return result;
    }

    public static boolean pathExists(String currentLocation, String targetLocation)
    {
        for (String path: paths)
        {
            if (path.startsWith(currentLocation) && path.endsWith(targetLocation))
            {
                return true;
            }
        }

        return false;
    }
}
