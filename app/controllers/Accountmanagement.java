package controllers;

import play.mvc.Controller;
import java.sql.Date;
import models.User;
import play.data.Form;
import play.mvc.Result;
import views.html.editProfile;
import views.html.login;
import views.html.register;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by Maik Wandrei on 04.12.2014.
 */
public class Accountmanagement extends Controller
{

   /**
    * Collecting, validating and if applicable saving data from website input.
    * If the account is created the user will be directed to the loginpage.
    * If something went wrong, the user will be directed back to the register
    * page and recieve a message about what wen wrong.
    *
    * @return the respective html result
    */
   public static Result register()
   {
       //field-names: nickname, email, password, repeat-password, termsOfUse

       Form form = Form.form(User.class).bindFromRequest();

       String nickname = form.field("nickname").value();
       String email = form.field("email").value();
       String password = form.field("password").value();
       String repeat = form.field("repeat-password").value();
       String termsOfUse = form.field("termsOfUse").value();
       String error = "";

       //check for empty
       if(nickname == null || nickname.equals(""))
       {
           error += "The field \"nickname\" was not filled in correctly.\n";
       }
       if(email == null || email.equals("") || !emailValid(email))
       {
           error += "The field \"email\" was not filled in correctly.\n";
       }
       if(password == null || password.equals(""))
       {
           error += "The field \"password\" was not filled in correctly.\n";
       }
       if(termsOfUse == null)
       {
           error += "You have to accept our well designed terms of use!\n";
       }

       //check for password matching
       if(!password.equals(repeat))
       {
           error += "Your passwords didn't match.\n";
       }

       if(!error.equals(""))
       {
           return ok(register.render(error));
       }

       User user = new User();
       user.time_creation = new Date(System.currentTimeMillis());
       user.nickname = nickname;
       user.e_mail = email;

       try
       {
           password = PasswordHash.createHash(password);
       }
       catch(Exception e)
       {
           return ok(register.render("Oops, seems we occured a problem. Maybe our Server drowned.\n"));
       }

       user.password_hash = getHashy(password);
       user.password_salt = getSalty(password);
       user.time_password = new Date(System.currentTimeMillis());

       //check if username already used
       if(null != User.findByUsername(nickname))
       {
            return ok(register.render("Seems your nickname is already used.\n"));
       }
       if(null != User.findByEmail(email))
       {
           return ok(register.render("Seems your e-mail address is already registered.\n"));
       }

       try
       {
           user.save();
       }
       catch(Exception e)
       {
           return ok(register.render("Oops, seems we occured a problem. Maybe our Server drowned.\n"));
       }


       return ok(login.render("Successfully registered! Now try to log-in."));

   }

    /**
     * Collecting and validating login data.
     * If the data was correct user will be directed to profile page.
     * Else he/she will be directed to the login page with an error displayed.
     *
     * @return the respective html result
     */
    public static Result login()
    {
        //collecting input from website

        String nickname = Form.form().bindFromRequest().field("nickname").value();
        String password = Form.form().bindFromRequest().field("password").value();

        if(nickname.equals("") || nickname == null)
        {

        }

        User user = User.findByUsername(nickname);

        if(user == null)
        {
            return ok(login.render("Your password or username was wrong."));
        }

        try
        {

            if(PasswordHash.validatePassword(password, "5000:" + user.password_hash + ":" + user.password_salt))
            {
                session("nickname", nickname);
                return ok(editProfile.render("Welcome, " + nickname + "!"));
            }
            else
            {
                return ok(login.render("Your password or username was wrong."));
            }
        }
        catch(Exception e)
        {
            return ok(login.render("Oops, seems we occured a problem. Maybe our Server drowned.\n"));
        }

    }

    /**
     * Destroying session of logged in user and direct to login page
     * @return
     */
    public static Result logout()
    {
        session().clear();
        return ok(login.render("Logout successful."));
    }

    public static Result editProfile()
    {
        //collecting input from website
        Form form = Form.form();
        String email = form.field("email").value();
        String oldpw = form.field("old-password").value();
        String newpw = form.field("new-password").value();
        String repeatpw = form.field("repeat-new-password").value();

        //validate input


        //find user
        User user = User.findByUsername(session("nickname"));

        if(user == null)
        {
            return ok(editProfile.render("Oops, seems we occured a problem. Maybe our Server drowned.\n"));
        }

        try
        {
            if(!PasswordHash.validatePassword(oldpw, "5000:" + user.password_hash + ":" + user.password_salt))
            {
                return ok(editProfile.render("Your password is incorrect."));
            }
        }
        catch(Exception e)
        {
            return ok(editProfile.render("Oops, seems we occured a problem. Maybe our Server drowned.\n"));
        }

        return ok("tbd c:");
    }

    /**
     * Extracts the hash part of the hashed password.
     *
     * @param hash the hased password
     * @return the hash part
     */
    public static String getHashy(String hash)
    {
        int colonOne = -1;
        int colonTwo = -1;

        for(int i = 0; i<hash.length(); i++)
        {
            if(hash.charAt(i) == ':')
            {
                if(colonOne == -1)
                {
                    colonOne = i;
                }
                else
                {
                    colonTwo = i;
                    return hash.substring(colonOne + 1, colonTwo);
                }
            }
        }

        return  null;
    }

    /**
     * Extracts the salt part of the hashed password.
     * @param hash the hashed password
     * @return the salt of the hashed password
     */
    public static String getSalty(String hash)
    {
        int colonOne = -1;
        int colonTwo = -1;

        for(int i = 0; i<hash.length(); i++)
        {
            if(hash.charAt(i) == ':')
            {
                if(colonOne == -1)
                {
                    colonOne = i;
                }
                else
                {
                    colonTwo = i;
                    return hash.substring(colonTwo + 1);
                }
            }
        }

        return null;
    }

    public static boolean emailValid(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
