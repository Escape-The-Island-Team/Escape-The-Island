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
                    "cords",
                    "sharpStone"
            };
    final static ArrayList<String> hatchet = new ArrayList<String>(Arrays.asList(hatchetArray));

    final static String[] fishingPoleArray =
            {
                    "stickFishingPole",
                    "cords",
                    "fishHook"
            };
    final static ArrayList<String> fishingPole = new ArrayList<String>(Arrays.asList(fishingPoleArray));

    final static String[] balloonArray =
            {
                    "knife",
                    "sailCloth",
                    "gasCanister",
                    "cords",
                    "torch",
                    "rumbarrel"
            };
    final static ArrayList<String> balloon = new ArrayList<String>(Arrays.asList(balloonArray));

    final static String[] daVinciArray =
            {
                    "hatchet",
                    "lumber",
                    "cords",
                    "knife",
                    "sailCloth",
                    "resin"
            };
    final static ArrayList<String> daVinci = new ArrayList<String>(Arrays.asList(daVinciArray));

    final static String[] raftArray =
            {
                    "hatchet",
                    "cords",
                    "paddle",
                    "lumber",
                    "sailCloth"
            };
    final static ArrayList<String> raft = new ArrayList<String>(Arrays.asList(raftArray));

    final static ArrayList[] itemCombinationArray =
            {
                    torch,
                    grapplingHook,
                    hatchet,
                    fishingPole,
                    balloon,
                    daVinci,
                    raft
            };
    final static ArrayList<ArrayList> itemCombinations = new ArrayList<ArrayList>(Arrays.asList(itemCombinationArray));

    final static String[] combinationNameArray =
            {
                    "torch",
                    "grapplingHook",
                    "hatchet",
                    "fishingPole",
                    "getScreen|balloon",
                    "getScreen|daVinci",
                    "getScreen|raft"
            };
    final static ArrayList<String> combinationNames = new ArrayList<String>(Arrays.asList(combinationNameArray));

    final static String[] messagesArray =
            {
                    "Congratulations! You have created a torch!",
                    "Congratulations! You have created a grapplinghook!",
                    "Congratulations! You have created a hatchet!",
                    "Congratulations! You have created a fishing pole!",
                    "getScreen|balloon",
                    "getScreen|daVinci",
                    "getScreen|raft"
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
                    "cords",
                    "sharkStone"
            };
    final static ArrayList<String> hatchetRemoveItems = new ArrayList<String>(Arrays.asList(hatchetRemoveArray));

    final static String[] fishingPoleRemoveArray =
            {
                    "stickFishingPole",
                    "cords",
                    "fishHook"
            };
    final static ArrayList<String> fishingPoleRemoveItems = new ArrayList<String>(Arrays.asList(fishingPoleRemoveArray));

    final static String[] balloonRemoveArray =
            {
                    "stickFishingPole",
                    "cords",
                    "fishHook"
            };
    final static ArrayList<String> balloonRemoveItems = new ArrayList<String>(Arrays.asList(balloonArray));

    final static String[] daVinciRemoveArray =
            {
                    "stickFishingPole",
                    "cords",
                    "fishHook"
            };
    final static ArrayList<String> daVinciRemoveItems = new ArrayList<String>(Arrays.asList(daVinciArray));

    final static String[] raftRemoveArray =
            {
                    "cords",
                    "lumber",
                    "sailCloth"
            };
    final static ArrayList<String> raftRemoveItems = new ArrayList<String>(Arrays.asList(raftRemoveArray));

    final static ArrayList[] removeItemsArray =
            {
                    torchRemoveItems,
                    grapplingHookRemoveItems,
                    hatchetRemoveItems,
                    fishingPoleRemoveItems,
                    balloonRemoveItems,
                    daVinciRemoveItems,
                    raftRemoveItems
            };
    final static ArrayList<ArrayList> itemsToRemove = new ArrayList<ArrayList>(Arrays.asList(removeItemsArray));

    public static String combineItems(List<String> items)
    {
        if (items.contains("cords1"))
        {
            items.remove("cords1");
            items.add("cords");
        }

        if (items.contains("cords2"))
        {
            items.remove("cords2");
            items.add("cords");
        }

        if (items.contains("cords3"))
        {
            items.remove("cords3");
            items.add("cords");
        }

        if (items.size() == 0)
        {
            return "This is your hammer. You can combine items with it!";
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

        System.out.println(checkList + "\n" + combinationList);

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
                    System.out.println("Remove combination: " + itemCombinations.get(check));
                    checkList.set(check, -1);
                    continue;
                }

                combination.remove(item);
                System.out.println("Removed item: " + item);
                System.out.println("Combination after remove: " + combination);
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

            System.out.println("Check if correct: " + combination);

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
