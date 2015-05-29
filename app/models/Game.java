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
    public long completed;

    @Constraints.Required
    public long active;

    // -- Queries
    public static Model.Finder<Long, Game> find = new Model.Finder<>(Long.class, Game.class);

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

    public static Game findActive(long user_id)
    {
        ExpressionList<Game> games = find.where().eq("user_id", user_id);
        ExpressionList<Game> activeGames = games.where().eq("active", 1);
        Game desiredGame = activeGames.findUnique();

        return desiredGame;
    }

    public static void setGameActive(long game_id)
    {
        long userId = find.byId(game_id).user_id;
        List<Game> games = find.where().eq("user_id", userId).findList();

        for(Game game: games)
        {
            game.active = 0;
            game.update();
        }

        Game selectedGame = find.byId(game_id);

        selectedGame.active = 1;
        selectedGame.update();

        Game loadedGame = find.byId(game_id);
    }

    public static void setGamesInactive(long userId)
    {
        List<Game> games = find.where().eq("user_id", userId).findList();

        for(Game game: games)
        {
            game.active = 0;
            game.update();
        }
    }

    public static boolean findIncompleteCharacter(String character, long user_id)
    {
        ExpressionList<Game> games = find.where().eq("user_id", user_id);
        ExpressionList<Game> incompleteGames = games.where().eq("completed", 0);

        for(Game game: incompleteGames.findList())
        {
            Character gameCharacter = Character.findByGameId(game.id);
            if (gameCharacter.equals(character))
            {
                return false;
            }
        }

        return true;
    }

    public static void setGameComplete(long gameId, long complete)
    {
        Game completed = Game.findById(gameId);

        completed.completed = complete;

        completed.update();
    }
}
