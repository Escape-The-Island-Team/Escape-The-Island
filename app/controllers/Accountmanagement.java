package controllers;

import play.mvc.Controller;

import java.text.Normalizer;
import models.User;
import play.data.Form;
import play.mvc.Result;

/**
 * Created by Maik Wandrei on 04.12.2014.
 */
public class Accountmanagement extends Controller
{
   public static Result register()
   {
       //nickname, email, password, repeat-password, termsOfUse

       Form form = Form.form(User.class).bindFromRequest();

       return ok();

   }

    public static Result login()
    {
        //nickname, password

        return ok();
    }
}
