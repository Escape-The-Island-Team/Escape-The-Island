package models;

import com.avaje.ebean.ExpressionList;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maik Wandrei on 06.12.2014.
 */
@Entity
public class Item extends Model
{
    @Id
    @OrderBy
    public long id;

    @Constraints.Required
    public long character_id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public int old;

    @Constraints.Required
    public int used;

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

    public static List<String> backpackContent(long character_id)
    {
        List<Item> backpack = find.where().eq("character_id", character_id).eq("used", 0).findList();
        List<String> backpackStrings = new ArrayList<>();
        backpackStrings.add("itembar");
        backpackStrings.add(Integer.toString(backpack.size()));

        for(Item item: backpack)
        {
            backpackStrings.add(item.name);
        }

        return backpackStrings;
    }

    public static boolean itemCollected(String itemName)
    {
        ExpressionList<Item> collectedItems = find.where().eq("name", itemName);

        if (collectedItems == null || collectedItems.findRowCount() == 0)
        {
            return false;
        }

        return true;
    }

    public static void collectItem(String itemName, long character_id)
    {
        Item collectedItem = new Item();

        collectedItem.character_id = character_id;
        collectedItem.name = itemName;
        collectedItem.old = 0;
        collectedItem.used = 0;

        collectedItem.save();
    }
}
