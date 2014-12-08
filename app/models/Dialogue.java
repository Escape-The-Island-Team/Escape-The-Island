package models;

import com.avaje.ebean.ExpressionList;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Maik Wandrei on 06.12.2014.
 */
@Entity
public class Dialogue extends Model
{
    @Id
    long id;

    @Constraints.Required
    long npc_id;
    
    @Constraints.Required
    String name;
    
    @Constraints.Required
    boolean active;     //tbd if necessary

    @Constraints.Required
    String content;     //tbd if necessary
    
    // -- Queries
    public static Model.Finder<String, Dialogue> find = new Model.Finder<>(String.class, Dialogue.class);

    public static Dialogue findById(long id)
    {
        ExpressionList<Dialogue> dialogues = find.where().eq("id", id);

        if(dialogues != null)
        {
            return dialogues.findUnique();
        }
        else
        {
            return null;
        }
    }

    public static ExpressionList<Dialogue> findByNpc(long npc_id)
    {
        return find.where().eq("npc_id", npc_id);
    }
}
