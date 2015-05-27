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

    // -- Queries
    public static Model.Finder<Long, NPC> find = new Model.Finder<>(Long.class, NPC.class);

    public static NPC findById(long id)
    {
        return find.byId(id);
    }

    public static void createNpc(long gameId, String name, int old)
    {
        NPC npc = new NPC();

        npc.game_id = gameId;
        npc.name = name;
        npc.old = old;
        npc.status = 0;
        npc.save();
    }

    public static List<NPC> findByGameId(long game_id, int old)
    {
        return find.where().eq("game_id", game_id).eq("old", old).findList();
    }

    public static void increaseStatus(long game_id, String name)
    {
        NPC npc = find.where().eq("game_id", game_id).eq("name", name).findUnique();

        System.out.println("Name: " + npc.name);
        System.out.println("Name: " + name);

        if (npc.name.equals("versutus") && npc.status < 7)
        {
            NPC versututs = new NPC();

            versututs.game_id = npc.game_id;
            versututs.name = npc.name;
            versututs.old = npc.old;
            versututs.status = npc.status + 1;
            versututs.save();
            npc.delete();

            ExpressionList<NPC> saved = find.where().eq("game_id", game_id).eq("name", name);

            while (saved == null || saved.findRowCount() == 0)
            {
                saved = find.where().eq("game_id", game_id).eq("name", name);
            }

            return;
        }

        if (npc.name.equals("scientist") && npc.status < 9)
        {
            NPC scientist = new NPC();

            scientist.game_id = npc.game_id;
            scientist.name = npc.name;
            scientist.old = npc.old;
            scientist.status = npc.status + 1;
            scientist.save();
            npc.delete();

            ExpressionList<NPC> saved = find.where().eq("game_id", game_id).eq("name", name);

            while (saved == null || saved.findRowCount() == 0)
            {
                saved = find.where().eq("game_id", game_id).eq("name", name);
            }

            return;
        }

        if (npc.name.equals("maya") && npc.status < 11)
        {
            NPC maya = new NPC();

            maya.game_id = npc.game_id;
            maya.name = npc.name;
            maya.old = npc.old;
            maya.status = npc.status + 1;
            maya.save();
            npc.delete();

            ExpressionList<NPC> saved = find.where().eq("game_id", game_id).eq("name", name);

            while (saved == null || saved.findRowCount() == 0)
            {
                saved = find.where().eq("game_id", game_id).eq("name", name);
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
