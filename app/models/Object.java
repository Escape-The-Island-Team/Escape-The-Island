package models;


import com.avaje.ebean.ExpressionList;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;

/**
 * Created by Maik Wandrei on 06.12.2014.
 */
@Entity
public class Object extends Model
{
    @Id
    public long id;

    @Constraints.Required
    @Formats.NonEmpty
    public long game_id;

    @Constraints.Required
    @Formats.NonEmpty
    public String name;

    @Constraints.Required
    public boolean old;

    @Constraints.Required
    public boolean used;

    public int usages_left; //negativ bei unbegrenzten usages

    // -- Queries
    public static Model.Finder<String, Object> find = new Model.Finder<>(String.class, Object.class);

    public static Object findGameObjectByName(long game_id, String objectName)
    {
        ExpressionList<Object> gameObjects = find.where().eq("game_id", game_id);

        if (gameObjects == null)
        {
            return null;
        }

        Object object = gameObjects.eq("name", objectName).findUnique();

        return object;
    }
}
