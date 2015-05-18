package controllers;

import java.security.InvalidParameterException;

/**
 * Created by Maik Wandrei on 03.05.2015.
 */
public class CharacterParser
{
    public static boolean isCharacter(String potentialCharacter)
    {
        if (potentialCharacter.equals("Alice"))
        {
            return true;
        }

        if (potentialCharacter.equals("Bob"))
        {
            return true;
        }

        return false;
    }

    public static boolean isOld(String character)
    {
        if (character.equals("Alice"))
        {
            return true;
        }

        if (character.equals("Bob"))
        {
            return false;
        }

        throw new InvalidParameterException("UnknownCharacter");
    }
}
