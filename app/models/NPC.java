package models;

import com.avaje.ebean.ExpressionList;
import org.h2.constraint.Constraint;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;
import java.util.List;

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
    int old;

    @Constraints.Required
    int status;

    @Constraints.Required
    String position;

    // -- Queries
    public static Model.Finder<Long, NPC> find = new Model.Finder<>(Long.class, NPC.class);

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

    public static List<NPC> findByGameId(long game_id, int old)
    {
        return find.where().eq("game_id", game_id).eq("old", old).findList();
    }

    public static void increaseStatus(long game_id, String name)
    {
        NPC npc = find.where().eq("game_id", game_id).eq("name", name).findUnique();

        if (npc.name == "versutus" && npc.status < 7)
        {
            npc.status++;
            npc.update();

            NPC actual = find.byId(npc.id);

            while (npc.status != actual.status)
            {
                actual.refresh();
            }

            return;
        }

        if (npc.name == "scientist" && npc.status < 9)
        {
            npc.status++;
            npc.update();

            NPC actual = find.byId(npc.id);

            while (npc.status != actual.status)
            {
                actual.refresh();
            }

            return;
        }

        if (npc.name == "maya" && npc.status < 11)
        {
            npc.status++;
            npc.update();

            NPC actual = find.byId(npc.id);

            while (npc.status != actual.status)
            {
                actual.refresh();
            }

            return;
        }

    }

    public static int getStatus(long game_id, String name)
    {
        NPC npc = find.where().eq("game_id", game_id).eq("name", name).findUnique();

        return npc.status;
    }
}
