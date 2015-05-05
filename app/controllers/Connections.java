package controllers;

import play.mvc.Controller;

import play.libs.Json;
import play.mvc.*;

import play.Logger;

import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class Connections extends Controller
{
    // obsolete but still used for testing purposes
    public static Result zweipluszwei(long variable)
    {
        return ok(Json.toJson(variable));
    }


    // giveLocation is being called from Javascript. It calls a method to get the details for the next location
    public static Result giveLocation(String location)
    {

        List<String> locations = parseLocationFromJS(location);


        // following line obsolet
        String toReturn = "";
        if(locations.get(0).equals("getStartLocation"))
        {
            toReturn="beachMidAvailable-platziereRumfass-platziereSeil-";
        }
        else
        {
            switch(locations.get(0))
            {
                case "beachMid":
                    switch(locations.get(1))
                    {
                        case "beachLeft":
                            toReturn="beachLeftAvailable-";
                            break;
                        case "beachRight":
                            toReturn="beachRightAvailable-";
                            break;
                        case "jungle":
                            toReturn="jungleAvailable-";
                            break;
                    }
                    break;
                case "beachLeft":
                    switch(locations.get(1))
                    {
                        case "beachMid":
                            toReturn="beachMidAvailable-";
                            break;
                    }
                    break;
                case "beachRight":
                    switch(locations.get(1))
                    {
                        case "beachMid":
                            toReturn="beachMidAvailable-";
                            break;
                    }
                    break;
                case "jungle":
                    switch(locations.get(1))
                    {
                        case "beachMid":
                            toReturn="beachMidAvailable-";
                            break;
                        case "temple":
                            toReturn="templeAvailable-";
                            break;
                        case "river":
                            toReturn="riverAvailable-";
                            break;
                    }
                    break;
                case "temple":
                    switch(locations.get(1))
                    {
                        case "jungle":
                            toReturn="jungleAvailable-";
                            break;
                    }
                    break;
                case "river":
                    switch(locations.get(1))
                    {
                        case "jungle":
                            toReturn="jungleAvailable-";
                            break;
                        case "opening":
                            toReturn="openingAvailable-";
                            break;
                    }
                    break;
                case "opening":
                    switch(locations.get(1))
                    {
                        case "river":
                            toReturn="riverAvailable-";
                            break;
                        case "lake":
                            toReturn="lakeAvailable-";
                            break;
                    }
                    break;
                case "lake":
                    switch(locations.get(1))
                    {
                        case "opening":
                            toReturn="openingAvailable-";
                            break;
                        case "cave":
                            toReturn="caveAvailable-";
                            break;
                        case "cliff":
                            toReturn="cliffAvailable-";
                            break;
                    }
                    break;
                case "cave":
                    switch(locations.get(1))
                    {
                        case "lake":
                            toReturn="lakeAvailable-";
                            break;
                    }
                    break;
                case "cliff":
                    switch(locations.get(1))
                    {
                        case "lake":
                            toReturn="lakeAvailable-";
                            break;
                    }
                    break;
            }
        }

        // ToDo: call method for details for the next location and return a list which is being parsed for JS
        // method to write: receives String array with 2 values (arr[0]=actualLocation, arr[1]=location to switch to)
        // -> parseLocationFromJS(location)
        // returns a List of Strings with all parameters required (-> ids have to be parsed to Strings before in a separate method)


        //List<String> locationsForJS = new ArrayList<String>();


        //String toReturn = parseForJS(locationsForJS);

         return ok(Json.toJson(toReturn));

        //return ok(Json.toJson("beach-leftAvailable#"));
        //return ok(Json.toJson(toReturn));
    }

    // takes the String from JS and returns an array of strings with 2 parameters (arr[0]=actualLocation, arr[1]=location to switch to)
    public static List<String> parseLocationFromJS(String infoGiven)
    {
        List<String> locations;
        locations=new ArrayList<String>();
        for(int i=0; i<infoGiven.length(); i++)
        {
            if(infoGiven.substring(i, i+1).equals("-"))
            {
                locations.add(infoGiven.substring(0,i));
                infoGiven=infoGiven.substring(i+1);
                i=0;
            }
        }
        return(locations);
    }

    // returns one large string with hashes to JS
    public static String parseForJS(List<String> listOfStrings)
    {
       String toReturn="";
       for(int i=0; i<listOfStrings.size(); i++)
       {
           toReturn+=listOfStrings.get(i)+"-";
       }
        return(toReturn);
    }







    // obsolete but still used
    public static Result giveLocationTest(String location)
    {
        for(int i=0; i<100000; i++)
        {

        }
        return ok(Json.toJson("test#"));
    }


}
