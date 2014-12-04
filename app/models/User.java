package models;

import com.avaje.ebean.ExpressionList;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;


/**
 * Created by Maik Wandrei on 27.11.2014.
 */
@Entity
public class User extends Model
{
    @Id
    public Long id;

    @Constraints.Required
    @Formats.NonEmpty
    @Column(unique = true)
    public String e_mail;

    @Constraints.Required
    @Formats.NonEmpty
    @Column(unique = true)
    public String nickname;

    @Constraints.Required
    @Formats.NonEmpty
    public String password_salt;

    @Constraints.Required
    @Formats.NonEmpty
    public String password_hash;

    @Constraints.Required
    @Formats.NonEmpty
    public Date time_creation;

    @Constraints.Required
    @Formats.NonEmpty
    public Date time_password;

    // -- Queries (long id, user.class)
    public static Model.Finder<Long, User> find = new Model.Finder<Long, User>(Long.class, User.class);

    /**
     * Retrieve a user from an email.
     *
     * @param email email to search
     * @return a user
     */
    public static User findByEmail(String e_mail) {
        return find.where().eq("e_mail", e_mail).findUnique();
    }

    /**
     * Retrieve a user from an email.
     *
     * @param id id to search
     * @return a user
     */
    public static User findById(Long id) {
        return find.where().eq("id", id).findUnique();
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

    public static long nextId()
    {
        return find.findRowCount() + 1;
    }

    /**
     * Authenticate a User, from a email and clear password.
     *
     * @param email         email
     * @param clearPassword clear password
     * @return User if authenticated, null otherwise
     * @throws AppException App Exception
     */
   /* public static User authenticate(String email, String clearPassword) throws AppException {

        // get the user with email only to keep the salt password
        User user = find.where().eq("email", email).findUnique();
        if (user != null) {
            // get the hash password from the salt + clear password
            if (Hash.checkPassword(clearPassword, user.passwordHash)) {
                return user;
            }
        }
        return null;
    }

    public void changePassword(String password) throws AppException {
        this.passwordHash = Hash.createPassword(password);
        this.save();
    }
     */

}
