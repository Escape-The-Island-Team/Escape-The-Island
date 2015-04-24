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
        String[] locations = parseLocationFromJS(location);
        if(locations[0]=="getStartLocation")
        {

        }
        else
        {

        }
        // ToDo: call method for details for the next location and return a list which is being parsed for JS
        // method to write: receives String array with 2 values (arr[0]=actualLocation, arr[1]=location to switch to)
        // -> parseLocationFromJS(location)
        // returns a List of Strings with all parameters required (-> ids have to be parsed to Strings before in a separate method)


        //List<String> locationsForJS = new ArrayList<String>();


        //String toReturn = parseForJS(locationsForJS);
        return ok(Json.toJson("beach-leftAvailable#platziereRumfass#platziereSeil#"));
    }

    // takes the String from JS and returns an array of strings with 2 parameters (arr[0]=actualLocation, arr[1]=location to switch to)
    public static String[] parseLocationFromJS(String infoGiven)
    {
        String[] locations = new String[2];
        for(int i=0; i<infoGiven.length(); i++)
        {
            if(infoGiven.substring(i,i+1)=="#")
            {
                locations[i]=infoGiven.substring(i,infoGiven.length()-i)+" ";
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
           toReturn+=listOfStrings.get(i)+"#";
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
