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
                "beach_mid",
                "beach_right",
                "beach_left",
                "jungle"
            };
    static ArrayList<String> locations = new ArrayList<String>(Arrays.asList(nameArray));

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
        case "beach_mid":   result.add("Stone");
                            break;
        case "beach_right": result.add("Banana");
                            break;
        case "beach_left":  result.add("Bottle");
                            break;
        }

        return result;
    }

    public static List<String> getNpcs(String location)
    {
        List<String> result = new ArrayList<>();

        switch (location)
        {
        case "beach_left":  result.add("Alisa");
                            break;
        }

        return result;
    }
}
