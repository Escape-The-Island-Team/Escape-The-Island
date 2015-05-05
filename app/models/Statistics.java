package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;

/**
 * Created by Maik Wandrei on 06.12.2014.
 */
@Entity
public class Statistics extends Model
{
    @Id
    long id;

    @Constraints.Required
    @Column(unique = true)
    long game_id;

    @Constraints.Required
    int usedAP;         //AP == actionpoints

    @Constraints.Required
    int collected;      //items collected

    @Constraints.Required
    int created;        //items created

    // -- Queries
    public static Model.Finder<String, Statistics> find = new Model.Finder<>(String.class, Statistics.class);
}
