package herosquad;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        port(4200);
        get("/",(request, response) ->{
            return new ModelAndView(new HashMap(), "index.hbs");

        },new HandlebarsTemplateEngine());

        get("/form",(request, response) ->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "form.hbs");

        },new HandlebarsTemplateEngine());

        post("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String heroName = request.queryParams("name");
            Integer heroAge = Integer.valueOf(request.queryParams("age"));
            String heroPower = request.queryParams("superpower");
            String heroWeakness = request.queryParams("weakness");
            Hero hero = new Hero(heroName, heroAge, heroPower, heroWeakness);
            return new ModelAndView(model, "heroes.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes",(request, response) ->{
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> hero = Hero.getInstances();
            model.put("hero", hero);
            return new ModelAndView(model, "heroes.hbs");

        },new HandlebarsTemplateEngine());

        get("/squads",(request, response) ->{
            Map<String, Object> model = new HashMap<>();
            ArrayList<Squad> squads = Squad.getInstances();
            model.put("squads", squads);
            return new ModelAndView(new HashMap(), "squads.hbs");

        },new HandlebarsTemplateEngine());

        get("/form_two", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> hero = Hero.getInstances();
            model.put("hero", hero);
            ArrayList<Squad> squads = Squad.getInstances();
            model.put("squads", squads);
            return new ModelAndView(model, "form two.hbs");

        }, new HandlebarsTemplateEngine());

        post("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String squadName = request.queryParams("squadName");
            String squadCause = request.queryParams("squadCause");
            Squad squad = new Squad(squadName, squadCause);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
