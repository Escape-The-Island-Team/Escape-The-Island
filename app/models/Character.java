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
    long id;

    @Constraints.Required
    @Column(unique = true)
    long game_id;

    @Constraints.Required
    String name;

    @Constraints.Required
    boolean old;

    @Constraints.Required
    int action_points;

    @Constraints.Required
    String position;

    // -- Queries
    public static Model.Finder<String, Character> find = new Model.Finder<>(String.class, Character.class);

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
}
