package controllers;

import java.security.InvalidParameterException;

/**
 * Created by Maik Wandrei on 03.05.2015.
 */

public class CharacterParser
{
    public static int isCharacter(String character)
    {
        if (character.equals("Bob"))
        {
            return 1;
        }

        if (character.equals("Alice"))
        {
            return 1;
        }

        if (character.equals("Nova"))
        {
            return 1;
        }

        if (character.equals("HomTanks"))
        {
            return 1;
        }

        if (character.equals("BerryStraw"))
        {
            return 1;
        }

        if (character.equals("CaptainSpeckJarrow"))
        {
            return 1;
        }

        return 0;
    }

    public static int isOld(String character)
    {
        if (character.equals("Bob"))
        {
            return 0;
        }

        if (character.equals("Alice"))
        {
            return 1;
        }

        if (character.equals("Nova"))
        {
            return 0;
        }

        if (character.equals("HomTanks"))
        {
            return 0;
        }

        if (character.equals("BerryStraw"))
        {
            return 0;
        }

        if (character.equals("CaptainSpeckJarrow"))
        {
            return 1;
        }

        return -1;
    }
}
