package controllers;

import controllers.Locationcontent.*;
import models.Item;
import models.Location;
import models.User;
import models.Game;
import models.Character;

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
                "volcano",
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
                    "Volcano",
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
                "beachMid_jungle",
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

    public static String pathChangeResult(String position, String target, String username)
    {
        if (position.equals("getStartLocation"))
        {
            return "available";
        }
        switch(position)
        {
        case "beachMid":
            switch(target)
            {
            case "beachLeft":
                return "available";
            case "beachRight":
                return "available";
            case "jungle":
                long userId = User.findByUsername(username).id;
                long gameId = Game.findActive(userId).id;
                if (Location.visited(gameId, target) || Item.characterHoldsItem("torch", Character.findByGameId(gameId).id))
                {
                    return "available";
                }
                return "The jungle is a dangerous place with many wild animals. It is not save to go there right now.";
            }
        case "beachLeft":
            switch(target)
            {
            case "beachMid":
                return "available";
            }
        case "beachRight":
            switch(target)
            {
            case "beachMid":
                return "available";
            }
        case "jungle":
            switch(target)
            {
            case "beachMid":
                return "available";
            case "temple":
                return "available";
            case "river":
                return "available";
            }
        case "temple":
            switch(target)
            {
            case "jungle":
                return "available";
            }
        case "river":
            switch(target)
            {
            case "jungle":
                return "available";
            case "opening":
                return "available";
            }
        case "opening":
            switch(target)
            {
            case "river":
                return "available";
            case "lake":
                return "available";
            case "rocks":
                return "available";
            }
        case "lake":
            switch(target)
            {
            case "opening":
                return "available";
            case "cave":
                return "available";
            case "cliff":
                long userId = User.findByUsername(username).id;
                long gameId = Game.findActive(userId).id;
                if (Location.visited(gameId, target) || Item.characterHoldsItem("grapplingHook", Character.findByGameId(gameId).id))
                {
                    //TODO using items ----> remove from backpack
                    return "available";
                }
                return "These rocks are too high. You need something to surmount them.";
            }
        case "cave":
            switch(target)
            {
            case "lake":
                return "available";
            }
        case "cliff":
            switch(target)
            {
            case "lake":
                return "available";
            }
        case "rocks":
            switch(target)
            {
            case "opening":
                return "available";
            case "volcano":
                return "available";
            case "waterfall":
                return "available";
            }
        case "volcano":
            switch(target)
            {
            case "rocks":
                return "available";
            }
        case "waterfall":
            switch(target)
            {
            case "rocks":
                return "available";
            case "treehouse":
                return "available";
            case "laboratory":
                return "available";
            case "timeLocation":
                long userId = User.findByUsername(username).id;
                long gameId = Game.findActive(userId).id;
                int old = Character.findByGameId(gameId).old;

                if (old == 1)
                {
                    return "available";
                }

                return "available";
            }
        case "treehouse":
            switch(target)
            {
            case "waterfall":
                return "available";
            }
        case "laboratory":
            switch(target)
            {
            case "waterfall":
                return "available";
            }
        }

        return "*GLaDOS voice* JAVASCRIPT-MANIPULATION DETECTED!!! Selfdestruction iminent.";
    }
}
