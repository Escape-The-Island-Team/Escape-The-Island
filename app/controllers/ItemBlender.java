package controllers;

import models.Item;

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

    final static String[] grapplingHookArray =
            {
                    "rope",
                    "hook"
            };
    final static ArrayList<String> grapplingHook = new ArrayList<String>(Arrays.asList(grapplingHookArray));

    final static String[] hatchetArray =
            {
                    "stickHatchet",
                    "fiberCords",
                    "sharpStone"
            };
    final static ArrayList<String> hatchet = new ArrayList<String>(Arrays.asList(grapplingHookArray));

    final static String[] fishingPoleArray =
            {
                    "stickFishingPole",
                    "fiberCords",
                    "fishHook"
            };
    final static ArrayList<String> fishingPole = new ArrayList<String>(Arrays.asList(fishingPoleArray));

    final static ArrayList[] itemCombinationArray =
            {
                    torch,
                    grapplingHook,
                    hatchet,
                    fishingPole
            };
    final static ArrayList<ArrayList> itemCombinations = new ArrayList<ArrayList>(Arrays.asList(itemCombinationArray));

    final static String[] combinationNameArray =
            {
                    "torch",
                    "grapplingHook",
                    "hatchet",
                    "fishingPole"
            };
    final static ArrayList<String> combinationNames = new ArrayList<String>(Arrays.asList(combinationNameArray));

    final static String[] messagesArray =
            {
                    "Congratulations! You have created a torch!",
                    "Congratulations! You have created a grapplinghook!",
                    "Congratulations! You have created a hatchet!",
                    "Congratulations! You have created a fishing pole!"
            };
    final static ArrayList<String> messages = new ArrayList<String>(Arrays.asList(messagesArray));

    final static String[] torchRemoveArray =
            {
                    "stick"
            };
    final static ArrayList<String> torchRemoveItems = new ArrayList<String>(Arrays.asList(torchRemoveArray));

    final static String[] grapplingHookRemoveArray =
            {
                    "rope",
                    "hook"
            };
    final static ArrayList<String> grapplingHookRemoveItems = new ArrayList<String>(Arrays.asList(grapplingHookRemoveArray));

    final static String[] hatchetRemoveArray =
            {
                    "stickHatchet",
                    "fiberCords",
                    "sharkStone"
            };
    final static ArrayList<String> hatchetRemoveItems = new ArrayList<String>(Arrays.asList(hatchetRemoveArray));

    final static String[] fishingPoleRemoveArray =
            {
                    "stickFishingPole",
                    "fiberCords",
                    "fishHook"
            };
    final static ArrayList<String> fishingPoleRemoveItems = new ArrayList<String>(Arrays.asList(fishingPoleRemoveArray));


    final static ArrayList[] removeItemsArray =
            {
                    torchRemoveItems,
                    grapplingHookRemoveItems,
                    hatchetRemoveItems,
                    fishingPoleRemoveItems
            };
    final static ArrayList<ArrayList> itemsToRemove = new ArrayList<ArrayList>(Arrays.asList(removeItemsArray));

    public static String combineItems(List<String> items)
    {
        int fiberCordNumber = 0;

        if (items.contains("fiberCords1"))
        {
            fiberCordNumber = 1;
            items.remove("fiberCords1");
            items.add("fiberCords");
        }

        if (items.contains("fiberCords2"))
        {
            fiberCordNumber = 2;
            items.remove("fiberCords2");
            items.add("fiberCords");
        }

        if (items.contains("fiberCords3"))
        {
            fiberCordNumber = 3;
            items.remove("fiberCords3");
            items.add("fiberCords");
        }

        if (items.size() == 1)
        {
            return "You cannot combine an item with itself.";
        }

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

    public static List<String> removeItems(String combination)
    {
        if (combinationNames.contains(combination))
        {
            return itemsToRemove.get(combinationNames.indexOf(combination));
        }

        return new ArrayList<String>();
    }
}
