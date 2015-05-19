package models;

import com.avaje.ebean.ExpressionList;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;

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
}
