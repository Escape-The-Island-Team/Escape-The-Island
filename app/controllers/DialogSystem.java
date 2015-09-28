package controllers;

import models.Item;
import models.Character;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maik Wandrei on 22.05.2015.
 */
public class DialogSystem
{
    final static String[] npcArray =
         {   
            "versutus",
            "scientist",
            "maya"
         };
    final static ArrayList<String> npcs = new ArrayList<String>(Arrays.asList(npcArray));
    
    final static String[] welcomeArray =
        {
            "Hello, I am Versutus, the famous inventor. I’m sure you have already heard of me. What I do? "
                + "I live in this treehouse and invent new things.",
            "Oh, hello, I haven’t seen you here before. I am the scientist professor Adventuus Vigor. "
                + "What you can see here is the old laboratory which got destroyed by an explosion some years ago. "
                + "My company left the island, but I wanted to stay and make more experiments for my research.",
            "Ugh! You do not belong to my people. Who are you?  I am Abu Simple, the chieftain of this temple."
        };
    final static ArrayList<String> welcomeMessage = new ArrayList<String>(Arrays.asList(welcomeArray));
    
    final static String[] versutusTaskArray = 
        {
            "I’ve been thinking a lot, but sometimes it’s difficult to concentrate. In the jungle, "
                + "there grows a delicious purple fruit which could help me to concentrate again. "
                + "Could you find one for me?|||task",
            "Thank you again for the delicious fruit. Now, I need some volcanic stones for my inventions. "
                + "Maybe you can find some near the volcano on this island.|||task",
            "Hey, there is something else that you could help me with. I need some door hinges for my treehouse. "
                + "How to find them on this island? There are some ships that crashed at the rocks on the coast. "
                + "Maybe you can find some parts there.|||task"
        };
    final static ArrayList<String> versutusTask = new ArrayList<String>(Arrays.asList(versutusTaskArray));

    final static String[] mayaTaskArray =
            {
                "Soon, we want to celebrate, but we need a special flower. It is a pink flower, "
                    + "maybe you can find one of them and bring it to me.|||task",
                "I have another task for you. My people had a nice treasure some years ago, "
                    + "but we lost it anywhere on this island. Can you find it?|||task",
                "Have you seen the waterfall? The water is so clear there! Could you bring me some water from there?|||task",
                "May I bother you again? I lost a piece of my traditional garment when I was running away from a tiger. Could you find it for me?|||task",
                "I have another task for you before I tell you about a construction to escape from this island. "
                    + "I need one of the seldom yellow fishes for the big celebration. Maybe you can use the fishhook I gave you.|||task"
            };
    final static ArrayList<String> mayaTask = new ArrayList<String>(Arrays.asList(mayaTaskArray));

    final static String[] scientistTaskArray =
            {
                "I’ve been thinking a lot, but sometimes it’s difficult to concentrate. "
                        + "In the jungle, there grows a delicious purple fruit which could help me to concentrate again."
                        + "Could you find one for me?|||task",
                "Thank you again for the delicious fruit. Now, I need some volcanic stones to find out more about this island. "
                        + "Maybe you can find some near the volcano.|||task",
                "Hey, there is something else that you could help me with. I want to rebuild a part of the laboratory. "
                        + "Therefore, I need some metallic door hinges. How to find them on this island? "
                        + "Once, an older ship crashed at the rocks on the coast. Maybe you can find some parts there.|||task",
                "I have a plan how you could escape from this island, as you don’t want to stay here. "
                        + "If you find me a valve that I need for the water supply of the new laboratory, "
                        + "I will give you an important part for the construction to escape from here.|||task"
            };
    final static ArrayList<String> scientistTask = new ArrayList<String>(Arrays.asList(scientistTaskArray));
    
    final static ArrayList[] taskArray =
            {
                    versutusTask,
                    scientistTask,
                    mayaTask
            };
    final static ArrayList<ArrayList> taskMessage = new ArrayList<ArrayList>(Arrays.asList(taskArray));

    final static String[] versutusWaitArray =
            {
                    "Try to find the purple fruit that I would like so much.|fruit|rope|wait",
                    "I still need the vulcanic stones. Maybe you can find some near the volcano.|volcanicStones|hook|wait",
                    "There are some ships that crashed at the rocks on the coast. Maybe you can find some door hinges there.|doorHinges|knife|wait"
            };
    final static ArrayList<String> versutusWait = new ArrayList<String>(Arrays.asList(versutusWaitArray));

    final static String[] mayaWaitArray =
            {
                    "Didn’t you find the flower? It is pink, try again!|flower||wait",
                    "If you find our treasure chest, bring it to me.|treasureChest|sharpStone|wait",
                    "Could you bring me some water from the waterfall?|bottleFull|rumBarrel|wait",
                    "Didn’t you find the piece of cloth yet?|cloth|fishhook|wait",
                    "Do you have difficulties with catching a yellow fish?|fish|paddle|wait"
            };
    final static ArrayList<String> mayaWait = new ArrayList<String>(Arrays.asList(mayaWaitArray));

    final static String[] scientistWaitArray =
            {
                    "Try to find the purple fruit that I would like so much.|fruit|rope|wait",
                    "I still need the vulcanic stones. Maybe you can find some near the volcano.|volcanicStones|hook|wait",
                    "Maybe you can find some door hinges at the coast where the old ship once crashed.|doorHinges|knife|wait",
                    "Didn’t you find a valve? It would be very helpful for me to rebuild the laboratory.|valve|gasCanister|wait"
            };
    final static ArrayList<String> scientistWait = new ArrayList<String>(Arrays.asList(scientistWaitArray));

    final static ArrayList[] waitArray =
            {
                    versutusWait,
                    scientistWait,
                    mayaWait
            };
    final static ArrayList<ArrayList> waitMessage = new ArrayList<ArrayList>(Arrays.asList(waitArray));

    final static String[] versutusCompleteArray =
            {
                    "Ah, this is the very fruit that I meant. Thank you! In return, let me give you this thick rope. Maybe you can use it.|fruit|rope|complete",
                    "These volcanic stones are exactly what I was searching for. Thank you. Let me give you this stable hook.|volcanicStones|hook|complete",
                    "I cannot believe that you really found some door hinges! Thank you a lot! "
                        + "In return, let me give you a knife. You can use it in combination with other items, for example to build an aircraft.|doorHinges|knife|complete"
            };
    final static ArrayList<String> versutusComplete = new ArrayList<String>(Arrays.asList(versutusCompleteArray));

    final static String[] mayaCompleteArray =
            {
                    "This is the flower we need. Thank you! In return, I want to teach you how to create cords out of fiber crops. *Teaching how to create fiber crops*|flower||complete",
                    "Our treasure chest, where was it? At the bottom of the lake? Incredible! Thank you! In return, let me give you a sharp stone. We use stones like these to build tools like hatchets.|treasureChest|sharpStone|complete",
                    "Hm, this tasts very good! Thank you! In return I want to give you some resin from special trees here./Hm, this tastes very good! Thank you! In return I want to give you a rum barrel that some sailors left here some years ago.",
                    "Here it is! Thank you. Was it hard to find? Let me thank you with this fishhook.|cloth|fishhook|complete",
                    "A yellow fish! Thank you! Let me reward you with a paddle. You can use it for the raft with which you can escape from this island.|fish|paddle|complete"
            };
    final static ArrayList<String> mayaComplete = new ArrayList<String>(Arrays.asList(mayaCompleteArray));

    final static String[] scientistCompleteArray =
            {
                    "Ah, this is the very fruit that I meant. Thank you! In return, let me give you this thick rope. Maybe you can use it.|fruit|rope|complete",
                    "These volcanic stones are exactly what I was searching for. Thank you. Let me give you this stable hook.|volcanicStones|hook|complete",
                    "I cannot believe that you really found some door hinges! Thank you a lot! In return, let me give you a knife. You can use it in combination with other items, for example to build an aircraft.|doorHinges|knife|complete",
                    "Ah, this valve is exactly what I was looking for. Thank you. My plan for you to escape from this island is building a hot air balloon. And here I give you a gas canister for this balloon.|valve|gasCanister|complete"
            };
    final static ArrayList<String> scientistComplete = new ArrayList<String>(Arrays.asList(scientistCompleteArray));

    final static ArrayList[] completeArray =
            {
                    versutusComplete,
                    scientistComplete,
                    mayaComplete
            };

    final static ArrayList<ArrayList> completeMessage = new ArrayList<ArrayList>(Arrays.asList(completeArray));

    final static ArrayList[] statusArray =
        {
                taskMessage,
                waitMessage,
                completeMessage
        };
    final static ArrayList<ArrayList<ArrayList>> statusMessage = new ArrayList<ArrayList<ArrayList>>(Arrays.asList(statusArray));


    public static List<String> retieveMessage(String Npc, int status, long charId)
    {
        int npcIndex = npcs.indexOf(Npc);

        if (status == 0)
        {
            List<String> quest = new ArrayList<String>();

            quest.add(welcomeMessage.get(npcIndex));
            quest.add("");
            quest.add("");
            quest.add("welcome");

            return quest;
        }

        if (Npc.equals("maya") && status == 11)
        {
            List<String> quest = new ArrayList<String>();

            quest.add("For building a raft, let me tell you what you need to build it: a hatchet, some lumber, some cords, a piece of sailcloth and a paddle.");
            quest.add("");
            quest.add("");
            quest.add("escape");

            return quest;
        }

        if (Npc.equals("versutus") && status == 7)
        {
            List<String> quest = new ArrayList<String>();

            quest.add("I have reconstructed the plans for an aircraft of Leonardo Da Vinci. Here are all the items you need to build it: a hatchet, a knife, some lumber, a piece of sailcloth, some cords and some resin.");
            quest.add("");
            quest.add("");
            quest.add("escape");

            return quest;
        }

        if (Npc.equals("scientist") && status == 9)
        {
            List<String> quest = new ArrayList<String>();

            quest.add("For the hot air balloon you need the following items: a knife, some cords, a piece of sail cloth, a torch, a gas canisters and a rumbarrel.");
            quest.add("");
            quest.add("");
            quest.add("escape");

            return quest;
        }

        status--;

        // if a npc is in task, wait or complete
        int state = status % 2;
        // which quest is active right now
        int quest = status / 2;

        if (Npc.equals("maya") && state == 1 && quest == 2)
        {
            return mayaQuest(charId, state, npcIndex, quest);
        }

        String message = (String)statusMessage.get(state).get(npcIndex).get(quest);
        List<String> questList = splitMessage(message);
        List<String> items = Item.backpackContent(charId);

        if (!questList.get(1).equals("") && items.contains(questList.get(1)))
        {
            return splitMessage((String)statusMessage.get(2).get(npcIndex).get(quest));
        }

        return questList;
    }

    public static List<String> splitMessage(String quest)
    {
        int firstSplitIndex = quest.indexOf('|');

        if (firstSplitIndex == -1)
        {
            List<String> result = new ArrayList<String>();
            result.add(quest);
            result.add("");
            result.add("");
            result.add("");

            return result;
        }

        int secondSplitIndex = quest.indexOf('|', firstSplitIndex + 1);
        int thirdSplitIndex = quest.indexOf('|', secondSplitIndex + 1);

        String message = quest.substring(0, firstSplitIndex);
        String npcRetrieves = quest.substring(firstSplitIndex + 1, secondSplitIndex);
        String npcRewards = quest.substring(secondSplitIndex + 1, thirdSplitIndex);
        String status = "";

        if (quest.length() - 1 > thirdSplitIndex)
        {
            status = quest.substring(thirdSplitIndex + 1);
        }

        List<String> questList = new ArrayList<>();

        questList.add(message);
        questList.add(npcRetrieves);
        questList.add(npcRewards);
        questList.add(status);

        return questList;
    }

    public static List<String> mayaQuest(long charId, int state, int npcIndex, int quest)
    {
        Character character = Character.findById(charId);
        String message = (String)statusMessage.get(state).get(npcIndex).get(quest);

        List<String> items = Item.backpackContent(charId);
        List<String> questList = splitMessage(message);

        if (!items.contains(questList.get(1)))
        {
            return questList;
        }

        message = (String)statusMessage.get(state + 1).get(npcIndex).get(quest);

        if (character.old == 1)
        {
            int slash = message.indexOf('/');

            message = message.substring(0, slash);

            questList = splitMessage(message);

            questList.remove(3);
            questList.remove(2);
            questList.remove(1);

            questList.add("bottleFull");
            questList.add("resin");
            questList.add("complete");

            return questList;
        }

        int slash = message.indexOf('/');

        message = message.substring(0, slash);

        questList = splitMessage(message);

        questList.remove(3);
        questList.remove(2);
        questList.remove(1);

        questList.add("bottleFull");
        questList.add("rumbarrel");
        questList.add("complete");

        return questList;
    }
}
