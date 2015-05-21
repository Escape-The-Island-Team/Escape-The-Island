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
        List<String> locations = parseFromJS(location);


        // following line obsolet
        String toReturn = "";

        toReturn = parseForJS(GameManager.changeLocation(locations));

         return ok(Json.toJson(toReturn));

        //return ok(Json.toJson("beach-leftAvailable#"));
        //return ok(Json.toJson(toReturn));
    }





    public static Result collectItem(String item)
    {
        List<String> infoList = parseFromJS(item);
        String toReturn = "";

        // call collecting items method with the info list given and another info list returned
        // toReturn = "successful-itembar-1-stick-messageInfo-You have found a stick!-";

        toReturn = parseForJS(GameManager.collectItem(infoList));

        return ok(Json.toJson(toReturn));
    }



    /*
    Submethods
    */


    public static Result getItems()
    {
        String toReturn = "";

        // call collecting items method with the info list given and another info list returned
        // toReturn = "itembar-1-stick-";
        toReturn = parseForJS(GameManager.getBackpack());

        System.out.println(toReturn);
        return ok(Json.toJson(toReturn));
    }

    public static Result getObjects(String location)
    {
        System.out.println(location);
        List<String> infoList = parseFromJS(location);
        String toReturn = "";

        // call collecting items method with the info list given and another info list returned

        toReturn = parseForJS(GameManager.objectsOnLocation(infoList));
        System.out.println(toReturn);
        return ok(Json.toJson(toReturn));
    }

    public static Result getLocationMessage(String location)
    {
        List<String> infoList = parseFromJS(location);

        String toReturn = "";

        toReturn = parseForJS(GameManager.locationMessage(infoList));
        // call collecting items method with the info list given and another info list returned

        return ok(Json.toJson(toReturn));
    }


    public static Result getCharIngame()
    {
        String toReturn = "";

        List<String> infoList = GameManager.getCharacter();
        // call collecting items method with the info list given and another info list returned
        // toReturn = "Bob-";
        toReturn = parseForJS(infoList);

        return ok(Json.toJson(toReturn));
    }

    public static Result getCharInteraction()
    {
        String toReturn = "";

        List<String> infoList = GameManager.getCharacterMessage();

        // call collecting items method with the info list given and another info list returned
        // toReturn = "I am Alice, the dangerous pirate. Don't bother me or you will regret it!-";
        toReturn = parseForJS(infoList);

        return ok(Json.toJson(toReturn));
    }

    // for Use Case 'Creating Items'
    public static Result getCombination(String itemsSelected)
    {
        List<String> infoList = parseFromJS(itemsSelected);
        String toReturn = "";

        // call collecting items method with the info list given and another info list returned
        toReturn = parseForJS(GameManager.combineItems(infoList));

        System.out.println("GetCombination completed. Returns values to Javascript.");

        return ok(Json.toJson(toReturn));
    }

    // for Use Case 'Use Item on Object'
    public static Result getAction(String itemsSelected)
    {
        List<String> infoList = parseFromJS(itemsSelected);
        String toReturn = "";

        // call collecting items method with the info list given and another info list returned
        // alternative0: if items = 0 (list = empty = no items selected in slot) and interaction with beehive -> receive honeycomb
        // alternative1: "notSuccessful-messageInfo-You cannot use more than one item on an object.-";
        // alternative2: "notSuccessful-messageInfo-This action would not have any effect.-";
        toReturn = parseForJS(GameManager.interactWithObjects(infoList));

        return ok(Json.toJson(toReturn));
    }


    public static Result interactWithNPC(String npc)
    {
        List<String> infoList = parseFromJS(npc);
        String toReturn = "";

        toReturn = "Ah, welcome back! I see you bring me the bottle with water that i asked you for. Please take this hook in turn. Thank you again!-";

        return ok(Json.toJson(toReturn));
    }


    /*
    user / game management
     */
    public static Result createGame(String character)
    {
        String toReturn = "successful-";
        List<String> infoList = parseFromJS(character);
        GameManager.newGame(infoList.get(0));

        // call collecting items method with the info list given and another info list returned

        return ok(Json.toJson(toReturn));
    }










    /*
    Helping methods
     */


    // takes the String from JS and returns an array of strings with 2 parameters (arr[0]=actualLocation, arr[1]=location to switch to)
    public static List<String> parseFromJS(String infoGiven)
    {
        List<String> stringList;
        stringList=new ArrayList<String>();
        for(int i=0; i<infoGiven.length(); i++)
        {
            if(infoGiven.substring(i, i+1).equals("-"))
            {
                stringList.add(infoGiven.substring(0,i));
                infoGiven=infoGiven.substring(i+1);
                i=0;
            }
        }
        return(stringList);
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
