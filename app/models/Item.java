package models;

import com.avaje.ebean.ExpressionList;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;

/**
 * Created by Maik Wandrei on 06.12.2014.
 */
@Entity
public class Item extends Model
{
    @Id
    long id;

    @Constraints.Required
    long character_id;

    @Constraints.Required
    String name;

    @Constraints.Required
    boolean old;

    // -- Queries
    public static Model.Finder<String, Item> find = new Model.Finder<>(String.class, Item.class);

    public static Item findById(long id)
    {
        ExpressionList<Item> items = find.where().eq("id", id);

        if(items != null)
        {
            return items.findUnique();
        }
        else
        {
            return null;
        }
    }

    public static ExpressionList<Item> findByCharId(long character_id)
    {
        return find.where().eq("character_id", character_id);
    }
}
