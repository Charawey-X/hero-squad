package herosquad;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        port(4200);
        get("/",(request, response) ->{
            return new ModelAndView(new HashMap(), "index.hbs");

        },new HandlebarsTemplateEngine());

        get("/heroes",(request, response) ->{
            return new ModelAndView(new HashMap(), "heroes.hbs");

        },new HandlebarsTemplateEngine());

        get("/squads",(request, response) ->{
            return new ModelAndView(new HashMap(), "squads.hbs");

        },new HandlebarsTemplateEngine());
    }
}
