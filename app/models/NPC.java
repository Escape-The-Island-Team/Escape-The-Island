package models;

import com.avaje.ebean.ExpressionList;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;

/**
 * Created by Maik Wandrei on 06.12.2014.
 */
@Entity
public class NPC extends Model
{
    @Id
    long id;

    @Constraints.Required
    long game_id;

    @Constraints.Required
    String name;

    @Constraints.Required
    boolean old;

    @Constraints.Required
    String position;

    // -- Queries
    public static Model.Finder<String, NPC> find = new Model.Finder<>(String.class, NPC.class);

    public static NPC findById(long id)
    {
        ExpressionList<NPC> npcs = find.where().eq("id", id);

        if(npcs != null)
        {
            return npcs.findUnique();
        }
        else
        {
            return null;
        }
    }

    public static ExpressionList<NPC> findByGameId(long game_id)
    {
        return find.where().eq("game_id", game_id);
    }
}
