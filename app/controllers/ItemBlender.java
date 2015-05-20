package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maik Wandrei on 20.05.2015.
 */
public class ItemBlender
{
    final static String[] torchArray =
            {
                    "flintstones",
                    "stick"
            };
    final static ArrayList<String> torch = new ArrayList<String>(Arrays.asList(torchArray));

    final static ArrayList[] itemCombinationArray =
            {
                    torch
            };
    final static ArrayList<ArrayList> itemCombinations = new ArrayList<ArrayList>(Arrays.asList(itemCombinationArray));

    final static String[] combinationNameArray =
            {
                    "torch"
            };
    final static ArrayList<String> combinationNames = new ArrayList<String>(Arrays.asList(combinationNameArray));


    final static String[] messagesArray =
            {
                    "Congratulations! You have created a torch!"
            };
    final static ArrayList<String> messages = new ArrayList<String>(Arrays.asList(messagesArray));

    public static String combineItems(List<String> items)
    {
        ArrayList<ArrayList> combinationList = new ArrayList<ArrayList>();
        for (ArrayList newList: itemCombinations)
        {
            ArrayList list = new ArrayList();
            list.addAll(newList);
            combinationList.add(list);
        }

        // the checklist contains if a combination is yet applicable,
        // if a value inside the checklist becomes less then 0 it's
        // combination is invalid else the value and it's index is
        // the index of it's combination it's related to
        ArrayList<Integer> checkList = new ArrayList<Integer>();
        while(checkList.size() < combinationList.size())
        {
            checkList.add(checkList.size());
        }

        // check all items
        for (String item: items)
        {
            // now see if a combination contains this item
            for (Integer check: checkList)
            {
                if (check < 0)
                {
                    continue;
                }

                ArrayList<String> combination = combinationList.get(check);

                // if not this combination can't be applicable
                if (!combination.contains(item))
                {
                    combinationList.remove(combination);
                    checkList.set(check, -1);
                    continue;
                }

                combination.remove(item);
            }
        }

        // now that we removed all combinations, that miss
        // an item that is used, check if the remaining combinations
        // require additional items or if they are empty after
        // removing all selected items
        for (Integer check: checkList)
        {
            if (check < 0)
            {
                continue;
            }

            ArrayList<String> combination = combinationList.get(check);

            if (combination.size() == 0)
            {
                return combinationNames.get(check);
            }
        }

        return "These items cannot be combined with each other!";
    }

    public static String messageOfCombination(String combination)
    {
        if (combinationNames.contains(combination))
        {
            return messages.get(combinationNames.indexOf(combination));
        }

        return "Gr8 b8 m8 I r8 8/8!";
    }
}
