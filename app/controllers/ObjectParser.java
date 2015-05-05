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

    public static boolean isObject(String objectName, boolean old)
    {
        if (old)
        {
            switch (objectName)
            {
            case "beehive": return true;
            }

            return false;
        }

        switch (objectName)
        {
        case "beehive":
        case "chest":   return true;
        }

        return false;
    }

    public static String needsItem(String objectName, boolean old)
    {
        if(old)
        {
            switch(objectName)
            {
            case "beehive":
                return "torch";
            }

            return null;
        }

        switch(objectName)
        {
        case "beehive":
            return "torch";
        case "chest":
            return "key";
        }

        return null;
    }

    public static String givesItem(String objectName, boolean old)
    {
        if(old)
        {
            switch(objectName)
            {
            case "beehive":
                return "honeycomb";
            }

            return null;
        }

        switch(objectName)
        {
        case "beehive":
            return "homeycomb";
        case "chest":
            return "2_golddoubloons";
        }

        return null;
    }

    public static String message(String objectName, boolean old)
    {
        if(old)
        {
            switch(objectName)
            {
            case "beehive":
                return "Honeycomb received!";
            }

            return null;
        }

        switch(objectName)
        {
        case "beehive":
            return "Honeycomb received!";
        case "chest":
            return "2 golddoubloons received!";
        }

        return null;
    }
}
