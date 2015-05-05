package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;

/**
 * Created by Maik Wandrei on 06.12.2014.
 */
@Entity
public class Quest extends Model
{
    @Id
    long id;

    @Constraints.Required
    long npc_id;

    @Constraints.Required
    boolean completed;

    @Constraints.Required
    boolean active; //to be determined if necessary

    // -- Queries
    public static Model.Finder<String, Quest> find = new Model.Finder<>(String.class, Quest.class);
}
