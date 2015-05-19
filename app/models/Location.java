package models;

import com.avaje.ebean.ExpressionList;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;

/**
 * Created by Maik Wandrei on 19.05.2015.
 */
@Entity
public class Location
{
    @Id
    public long id;

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
}
