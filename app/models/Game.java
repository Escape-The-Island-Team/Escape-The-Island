package models;

import com.avaje.ebean.ExpressionList;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Maik Wandrei on 06.12.2014.
 */
@Entity
public class Game extends Model
{
    @Id
    long id;

    @Constraints.Required
    long user_id;

    @Constraints.Required
    Date start_time;

    @Constraints.Required
    boolean completed;

    // -- Queries
    public static Model.Finder<String, Game> find = new Model.Finder<>(String.class, Game.class);

    public static Game findById(long id)
    {
        ExpressionList<Game> games = find.where().eq("id", id);

        if(games != null)
        {
            return games.findUnique();
        }
        else
        {
            return null;
        }
    }

    public static ExpressionList<Game> findByUserId(long user_id)
    {
        return find.where().eq("user_id", user_id);
    }
}
