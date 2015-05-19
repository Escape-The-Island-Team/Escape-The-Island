package models;

import com.avaje.ebean.ExpressionList;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;

/**
 * Created by Maik Wandrei on 19.05.2015.
 */
@Entity
public class Location extends Model
{
    @Id
    public long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public long game_id;

    @Constraints.Required
    public int visited;

    // -- Queries
    public static Model.Finder<Long, Location> find = new Model.Finder<>(Long.class, Location.class);

    public static Location findById(long id)
    {
        return find.byId(id);
    }

    public static boolean newlyDiscovered(long game_id, String locationName)
    {
        Location location = Location.find.where().eq("game_id", game_id).eq("name", locationName).findUnique();

        return (location == null || location.visited == 0);
    }

    public static void visit(long game_id, String locationName)
    {
        Location location = new Location();

        location.game_id = game_id;
        location.name = locationName;
        location.visited = 1;
        location.save();
    }
}
