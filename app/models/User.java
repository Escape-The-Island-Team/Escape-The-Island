package models;

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
    public static User findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
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
     * @param user_name Full name
     * @return a user
     */
    public static User findByUsername(String user_name) {
        return find.where().eq("user_name", user_name).findUnique();
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