package controllers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Maik Wandrei on 18.05.2015.
 */
public class CharacterMessages
{
    final static String[] bobArray =
            {
                "Hey, I'm Bob. Bob what? Just Bob.",
                "Oh my god, I'm gonna miss my exams!",
                "Don't worry about a thing. Cause every little thing is gonna be alright!",
                "Today, I would have maths lessons. Damn!",
                "No woman, no cry.",
                "I would just swim back over the ocean, but I'm frightened because of these stupid shark movies!"
            };
    final static ArrayList<String> bob = new ArrayList<String>(Arrays.asList(bobArray));

    final static String[] aliceArray =
            {
                "I am Alice, the dangerous pirate. Don't bother me or you will regret it!",
                "With my crew I will sail on the seven seas.",
                "Many treasures are waiting to be discovered!",
                "Many trading ships are waiting to be boarded!",
                "If I find a jewel on this island I won't tell you.",
                "I'm not Alice in the wonderland, but I bring you there if you click on me!"
            };
    final static ArrayList<String> alice = new ArrayList<String>(Arrays.asList(aliceArray));

    final static String[] novaArray =
            {
                "Special agent Nova, ready for beauty... excuse me, duty!",
                "This mission is top secret.",
                "*stretching* I want to be prepared to face everything.",
                "Do you really have the time for clicking on me so often?",
                "Mission accepted. Over and out.",
                "Yippee ki yay, ...!"
            };
    final static ArrayList<String> nova = new ArrayList<String>(Arrays.asList(novaArray));

    final static String[] homTanksArray =
            {
                "Hello, my name is Hom Tanks from Ted Ex. Now let's get this task started.",
                "Come on, I have no time. I got to go off this island. Tick tack tick tack.",
                "If I find Ted Ex packets, I will throw all other items away and only carry them with me. Just to inform you.",
                "Aaaaanyybooodyy?!",
                "WILSON!",
                "I'd like to play some volleyball now, but I don't know why..."
            };
    final static ArrayList<String> homTanks = new ArrayList<String>(Arrays.asList(homTanksArray));

    final static String[] berryStrawArray =
            {
                "Hey! I'm Berry! Do you want to play with me?",
                "Mommy said I should not talk to strangers.",
                "I want to go home! Mommy!!!",
                "When I'm getting angry my head can become as red as a berry!",
                "Do you think there is an iceman on this island? I would like three balls, strawberry, strawberry and strawberry!",
                "I wanna be a raspberry! Not a strawberry aargh!"
            };
    final static ArrayList<String> berryStraw = new ArrayList<String>(Arrays.asList(berryStrawArray));

    final static String[] CaptainSpeckJarrowArray =
            {
                "I'm Captain Speck Jarrow, the famous captain of the british royal navy! *Hicks*",
                "Yo, ho, hello sailor. Is the rum gone?",
                "I'm not chubby, I just wear a thick jacket!",
                "What shall we do with a drunken sailor, early in the morning?",
                "Once I was drunk again, I have seen a bearded giant. Afterwards my crew told me I was just looking into the mirror.",
                "Now let's find a way back home, I got a meeting... In the tavern, haha!"
            };
    final static ArrayList<String> derDicke = new ArrayList<String>(Arrays.asList(CaptainSpeckJarrowArray));

    public static String getMessage(String character, int messageNumber)
    {
        switch(character)
        {
        case "Bob":                 return bob.get(messageNumber);
        case "Alice":               return alice.get(messageNumber);
        case "Nova":                return nova.get(messageNumber);
        case "HomTanks":            return homTanks.get(messageNumber);
        case "BerryStraw":          return berryStraw.get(messageNumber);
        case "CaptainSpeckJarrow":  return derDicke.get(messageNumber);
        }

        return "Spacko!";
    }
}
