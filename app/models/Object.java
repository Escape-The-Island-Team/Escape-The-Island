package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;

/**
 * Created by Maik Wandrei on 06.12.2014.
 */
@Entity
public class Object extends Model
{
    @Id
    long id;

    @Constraints.Required
    @Formats.NonEmpty
    long game_id;

    @Constraints.Required
    @Formats.NonEmpty
    String name;

    @Constraints.Required
    @Formats.NonEmpty
    String description;

    @Constraints.Required
    boolean old;

    @Constraints.Required
    boolean used;

    int usages_left; //null bei unbegrenzten usages

    @Constraints.Required
    boolean item_required;

    // -- Queries
    public static Model.Finder<String, User> find = new Model.Finder<>(String.class, User.class);
}
