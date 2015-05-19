package controllers;

import controllers.Locationcontent.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maik Wandrei on 27.04.2015.
 */
public class LocationContent
{
    static String[] nameArray =
        {
                "beachMid",
                "beachLeft",
                "beachRight",
                "jungle",
                "temple",
                "river",
                "opening",
                "lake",
                "cave",
                "cliff",
                "rocks",
                "vulcano",
                "waterfall",
                "treehouse",
                "laboratory"
        };
    static ArrayList<String> locations = new ArrayList<String>(Arrays.asList(nameArray));

    static String[] displayNameArray =
            {
                    "Beach Mid",
                    "Beach Left",
                    "Beach Right",
                    "Jungle",
                    "Temple",
                    "River",
                    "Opening",
                    "Lake",
                    "Cave",
                    "Cliff",
                    "Rocks",
                    "Vulcano",
                    "Waterfall",
                    "Treehouse",
                    "Laboratory"
            };
    static ArrayList<String> displayName = new ArrayList<String>(Arrays.asList(displayNameArray));

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

    public static boolean isLocation(String possibleLocation)
    {
        for(String actualLocation: locations)
        {
            if (actualLocation.equals(possibleLocation))
            {
                return true;
            }
        }

        return false;
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

    public static List<String> getObjects(String location, boolean old)
    {
        List<String> result = new ArrayList<>();

        if (old)
        {
            switch (location)
            {
            case "beachMid":   result.add("oldStone");
                               break;
            case "beachRight": result.add("oldBanana");
                               break;
            case "beachLeft":  result.add("oldBottle");
                               break;
            }

            return result;
        }

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

    /**
     *
     * @param location: the location where the npcs are requested for
     * @param old: if the npcs are in the old or new era
     * @return: a list of strings containing all npcs located on a location
     */
    public static List<String> getNpcs(String location, boolean old)
    {
        INpcLister npcLister = new NewNpcLister();

        if (old)
        {
            npcLister = new OldNpcLister();
        }

        return npcLister.getNpcs(location);
    }

    public static String getMessage(String location)
    {
        int locationIndex = locations.indexOf(location);

        if (locationIndex < 0)
        {
            return null;
        }

        String locationName = displayName.get(locationIndex);

        if (locationName == null)
        {
            return null;
        }

        return "You discovered a new region: " + locationName + ".";
    }
}
