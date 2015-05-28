package models;

import com.avaje.ebean.ExpressionList;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Maik Wandrei on 06.12.2014.
 */
@Entity
public class Character extends Model
{
    @Id
    public long id;

    @Constraints.Required
    @Column(unique = true)
    public long game_id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public int old;

    @Constraints.Required
    public int action_points;

    @Constraints.Required
    public String position;

    @Constraints.Required
    public int message;


    // -- Queries
    public static Model.Finder<Long, Character> find = new Model.Finder<>(Long.class, Character.class);

    public static Character findById(long id)
    {
        ExpressionList<Character> characters = find.where().eq("id", id);

        if(characters != null)
        {
            return characters.findUnique();
        }
        else
        {
            return null;
        }
    }

    public static Character findByGameId(long game_id)
    {
        ExpressionList<Character> characters = find.where().eq("game_id", game_id);

        if(characters != null)
        {
            return characters.findUnique();
        }
        else
        {
            return null;
        }
    }

    public static int getNextMessage(long char_id)
    {
        Character character = find.byId(char_id);

        int message = character.message;

        character.message = (character.message + 1) % 6;
        character.update();

        return message;
    }

    public static void changeLocation(long char_id, String location)
    {
        Character character = find.byId(char_id);

        character.position = location;
        character.save();
    }

    public static int getActionpoints(long charId)
    {
        Character character = find.byId(charId);

        return character.action_points;
    }

    public static void reduceActionPoints(long charId, int cost)
    {
        //TODO remove
        System.out.println("Reduce AP by " + cost + " points.");

        Character character = find.byId(charId);

        int newActionPoints = character.action_points - cost;

        if (newActionPoints < 0)
        {
            character.action_points = 0;
            character.update();

            Character actual = find.byId(charId);

            while (actual.action_points != character.action_points)
            {
                actual = find.byId(charId);
            }

            return;
        }

        character.action_points -= cost;
        character.update();

        Character actual = find.byId(charId);

        while (actual.action_points != character.action_points)
        {
            actual = find.byId(charId);
        }

        return;
    }

    public static Character getBeschdeCharacter(long userId)
    {
        List<Game> finishedGames = Game.find.where().eq("user_id", userId).eq("completed", 1).findList();

        Character beschde = new Character();
        beschde.action_points = -1;

        for (Game game: finishedGames)
        {
            Character character = Character.find.where().eq("game_id", game.id).findUnique();

            if (character.action_points > beschde.action_points)
            {
                beschde = character;
            }
        }

        return beschde;
    }
}
