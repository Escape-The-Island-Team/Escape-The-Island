package controllers;

import java.util.ArrayList;
import java.util.Arrays;

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

    public int getId(String locationName)
    {
        return locations.indexOf(locationName);
    }

    public String getName(int id)
    {
        if (id <= locations.size())
        {
            return "loc_" + locations.get(id);
        }

        return "noSuchLocation" + id;
    }
}
