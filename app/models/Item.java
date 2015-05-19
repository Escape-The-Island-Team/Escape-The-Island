package models;

import com.avaje.ebean.ExpressionList;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Maik Wandrei on 06.12.2014.
 */
@Entity
public class Item extends Model
{
    @Id
    public long id;

    @Constraints.Required
    public long character_id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public boolean old;

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

    public static List<Item> findByCharId(long character_id)
    {
        return find.where().eq("character_id", character_id).findList();
    }

    public static boolean characterHoldsItem(String itemName, long characterId)
    {
        List<Item> items = Item.findByCharId(characterId);

        for(Item item: items)
        {
            if (item.name.equals(itemName))
            {
                return true;
            }
        }

        return false;
    }
}
