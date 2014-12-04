package controllers;

import play.mvc.Controller;
import java.sql.Date;
import models.User;
import play.data.Form;
import play.mvc.Result;
import views.html.register;

/**
 * Created by Maik Wandrei on 04.12.2014.
 */
public class Accountmanagement extends Controller
{
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
           error += "The field \"nickname\" was not filled in correctly";
       }
       if(email == null || email.equals(""))
       {
           error += "The field \"email\" was not filled in correctly";
       }
       if(password == null || password.equals(""))
       {
           error += "The field \"password\" was not filled in correctly";
       }
       if(termsOfUse == null)
       {
           error += "You have to accept our well designed terms of use.";
       }

       //check for password matching
       if(!password.equals(repeat))
       {
           error += "Your passwords didn't match";
       }

       if(!error.equals(""))
       {
           return ok(register.render(error));
       }

       User user = new User();
       user.time_creation = new Date(System.currentTimeMillis());
       user.nickname = nickname;
       user.e_mail = email;
       user.password_hash = getHashy(password);
       user.password_salt = getSalty(password);
       user.time_password = new Date(System.currentTimeMillis());

       //check if username already used
       if(null != User.findByUsername(nickname))
       {
            return ok(register.render("Seems your nickname is already used."));
       }
       if(null != User.findByEmail(email))
       {
           return ok(register.render("Seems your e-mail address is already registered."));
       }

       try
       {
           user.save();
       }
       catch(Exception e)
       {
           return ok(register.render("Oops, seems we occured a problem. Maybe our Server drowned."));
       }


       return ok(register.render("Successful!"));

   }

    public static Result login()
    {
        //nickname, password

        return ok();
    }

    public static String getHashy(String hash)
    {
        for(int i = 0; i<hash.length(); i++)
        {
            int colonOne = -1;
            int colonTwo = -1;

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

        return null;
    }

    public static String getSalty(String hash)
    {
        for(int i = 0; i<hash.length(); i++)
        {
            int colonOne = -1;
            int colonTwo = -1;

            if(hash.charAt(i) == ':')
            {
                if(colonOne == -1)
                {
                    colonOne = i;
                }
                else
                {
                    colonTwo = i;
                    return hash.substring(colonTwo);
                }
            }
        }

        return null;
    }
}
