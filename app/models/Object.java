package models;


import com.avaje.ebean.ExpressionList;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;
import java.util.List;

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
    public int old;

    @Constraints.Required
    public int used;

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

    public static List<Object> findUsedGameObjects(long game_id)
    {
        return find.where().eq("game_id", game_id).eq("used", 1).findList();
    }

    public static boolean objectIsUsed(String objectName, long gameId)
    {
        List<Object> objects = find.where().eq("name", objectName).eq("game_id", gameId).findList();

        if (objects.size() < 1)
        {
            return false;
        }

        Object object = objects.get(0);

        if (object.used > 0)
        {
            return true;
        }

        return false;
    }

    public static void useObject(String objectName, long gameId)
    {
        List<Object> objects = Object.find.where().eq("name", objectName).eq("game_id", gameId).findList();

        if (objects.size() < 1)
        {
            Object object = new Object();

            object.game_id = gameId;
            object.name = objectName;
            object.old = 0;
            object.usages_left = -1;
            object.used = 1;
            object.save();

            return;
        }

        Object object = objects.get(0);

        object.used = 1;
        object.update();

        return;
    }
}
