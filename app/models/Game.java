package models;

import com.avaje.ebean.ExpressionList;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maik Wandrei on 06.12.2014.
 */
@Entity
public class Game extends Model
{
    @Id
    public long id;

    @Constraints.Required
    public long user_id;

    @Constraints.Required
    public Date start_time;

    @Constraints.Required
    public boolean completed;

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

    public static List<Game> findByUserId(long user_id)
    {
        ExpressionList<Game> userGames = find.where().eq("user_id", user_id);

        if (userGames == null || userGames.findRowCount() == 0)
        {
            return new ArrayList<Game>();
        }

        return userGames.findList();
    }

    public static boolean findIncomplete(String character, long user_id)
    {
        ExpressionList<Game> games = find.where().eq("user_id", user_id);
        ExpressionList<Game> activeGames = games.where().eq("completed", true);

        for(Game game: activeGames.findList())
        {
            Character gameCharacter = Character.findByGameId(game.id);
            if (gameCharacter.equals(character))
            {
                return false;
            }
        }

        return true;
    }
}
