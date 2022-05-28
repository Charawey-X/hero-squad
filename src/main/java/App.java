import dao.Sql2oHeroDao;
import dao.Sql2oSquadDao;
import models.Hero;
import models.Squad;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost/hero_squad";
        Sql2o sql2o = new Sql2o(connectionString,"x","230620");
        Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);
        Sql2oSquadDao squadDao = new Sql2oSquadDao(sql2o);

        //show all heroes in all squads
        get("/",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads",squads);
            List<Hero> heroes = heroDao.getAll();
            model.put("heroes",heroes);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //create a new squad
        get("/squads/new", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll(); //refresh navbar list
            model.put("squads",squads);
            return new ModelAndView(model,"squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process form to add a squad
        post("/squads",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int size = Integer.parseInt(request.queryParams("size"));
            String cause = request.queryParams("cause");
            Squad squad = new Squad(name,size,cause);
            squadDao.add(squad);
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        //delete all squads and heroes
        get("/squads/delete",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            squadDao.deleteAll();
            heroDao.deleteAll();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //delete all heroes
        get("/heroes/delete",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            heroDao.deleteAll();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //show specific squad
        get("/squads/:id",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            Squad foundSquad = squadDao.findById(id);
            model.put("squad",foundSquad);
            List<Hero> allHeroesInSquad = squadDao.allHeroesBySquad(id);
            model.put("heroes",allHeroesInSquad);
            model.put("squads",squadDao.getAll()); //refresh navbar list
            return new ModelAndView(model,"squad-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //update a squad
        get("/squads/:id/edit",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            model.put("editSquad",true);
            int id = Integer.parseInt(request.params("id"));
            Squad foundSquad = squadDao.findById(id);
            model.put("squad",foundSquad);
            model.put("squads",squadDao.getAll()); //refresh navbar list
            return new ModelAndView(model,"squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process squad update
        post("/squads/:id",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            String name = request.queryParams("newSquadName");
            String cause = request.queryParams("newSquadCause");
            int size = Integer.parseInt(request.queryParams("newSquadSize"));
            Squad squad = new Squad(name,size,cause);
            squadDao.update(id,squad);
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        //delete a hero
        get("/heroes/:id/delete",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            heroDao.deleteById(id);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //create a new hero
        get("/heroes/new",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            model.put("squads",squadDao.getAll());
            return new ModelAndView(model,"hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process new hero
        post("/heroes",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            model.put("squads",squadDao.getAll());
            String name =request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String power = request.queryParams("power");
            String weak = request.queryParams("weak");
            int squad_id = Integer.parseInt(request.queryParams("squad"));
            Hero hero = new Hero(name,age,power,weak,squad_id);
            heroDao.add(hero);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //update a hero
        get("heroes/:id/update", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            model.put("editHero",true);
            model.put("squads",squadDao.getAll());
            Hero foundHero = heroDao.findById(Integer.parseInt(request.params("id")));
            model.put("hero",foundHero);
            return new ModelAndView(model,"hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("heroes/:id", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            String name =request.queryParams("newHeroName");
            int age = Integer.parseInt(request.queryParams("newHeroAge"));
            String power = request.queryParams("newHeroPower");
            String weak = request.queryParams("newHeroWeak");
            int squad_id = Integer.parseInt(request.queryParams("newSquad"));
            Hero hero = new Hero(name,age,power,weak,squad_id);
            heroDao.update(id,hero);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

    }
}
