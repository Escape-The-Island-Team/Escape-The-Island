package models;

import com.avaje.ebean.ExpressionList;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


/**
 * Created by Maik Wandrei on 27.11.2014.
 */
@Entity
public class User extends Model
{
    @Id
    public Long id;
    public String e_mail;
    public String nickname;
    public String password_salt;
    public String password_hash;
    public Date time_creation;
    public Date time_password;

    // -- Queries
    public static Model.Finder<String, User> find = new Model.Finder<>(String.class, User.class);

    /**
     * Retrieve a user from an email.
     *
     * @param e_mail email to search
     * @return a user
     */
    public static User findByEmail(String e_mail)
    {
        ExpressionList<User> users = find.where().eq("e_mail", e_mail);

        if(users == null)
        {
            return null;
        }
        else
        {
            return users.findUnique();
        }
    }

    /**
     * Retrieve a user from an email.
     *
     * @param id id to search
     * @return a user
     */
    public static User findById(Long id)
    {

        ExpressionList<User> users = find.where().eq("id", id);

        if(users == null)
        {
            return null;
        }
        else
        {
            return users.findUnique();
        }
    }

    /**
     * Retrieve a user from a fullname.
     *
     * @param nickname Full name
     * @return a user
     */
    public static User findByUsername(String nickname) {

        ExpressionList<User> users = find.where().eq("nickname", nickname);

        if(users == null)
        {
            return null;
        }
        else
        {
            return users.findUnique();
        }

    }

    /**
     * Takes the hash and salt and writes it into the database
     *
     * @param hash the hashpart
     * @param salt the saltpart
     */
    public void changePassword(String hash, String salt)
    {
        this.password_hash = hash;
        this.password_salt = salt;
        this.time_password = new Date(System.currentTimeMillis());
        this.save();
    }

    /**
     * Recieves an email String and writes it into the database.
     *
     * @param email the new email
     */
    public void changeEmail(String email)
    {
        this.e_mail = email;
        this.save();
    }
}
