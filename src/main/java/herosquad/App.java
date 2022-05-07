package herosquad;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        get("/",(request, response) ->{
            return new ModelAndView(new HashMap(), "index.hbs");

        },new HandlebarsTemplateEngine());

        get("/form",(request, response) ->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(new HashMap(), "form.hbs");

        },new HandlebarsTemplateEngine());

        post("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String heroName = request.queryParams("name");
            request.session().attribute("name", heroName);
            Integer heroAge = Integer.valueOf(request.queryParams("age"));
            request.session().attribute("age", heroAge);
            String heroPower = request.queryParams("superpower");
            request.session().attribute("superpower", heroPower);
            String heroWeakness = request.queryParams("weakness");
            request.session().attribute("weakness", heroWeakness);
            Hero hero = new Hero(heroName, heroAge, heroPower, heroWeakness);
            return new ModelAndView(model, "loading.hbs");
        }, new HandlebarsTemplateEngine());

        get("/form_two", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> hero = Hero.getInstances();
            model.put("hero", hero);
            ArrayList<Squad> squads = Squad.getInstances();
            model.put("squads", squads);
            return new ModelAndView(model, "form-two.hbs");

        }, new HandlebarsTemplateEngine());

        post("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String squadName = request.queryParams("squadName");
            request.session().attribute("squadName", squadName);
            String squadCause = request.queryParams("squadCause");
            request.session().attribute("squadCause", squadCause);
            Squad squad = new Squad(squadName, squadCause);
            return new ModelAndView(model, "loading.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes",(request, response) ->{
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> hero = Hero.getInstances();
            model.put("hero", hero);
            ArrayList<Squad> squads = Squad.getInstances();
            model.put("squads", squads);
            return new ModelAndView(model, "heroes.hbs");

        },new HandlebarsTemplateEngine());

        get("/squads",(request, response) ->{
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> hero = Hero.getInstances();
            model.put("hero", hero);
            ArrayList<Squad> squads = Squad.getInstances();
            model.put("squads", squads);
            return new ModelAndView(model, "squad.hbs");

        },new HandlebarsTemplateEngine());

    }
}
