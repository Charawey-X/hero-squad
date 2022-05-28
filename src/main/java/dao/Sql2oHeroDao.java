package dao;

import models.Hero;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oHeroDao extends HeroDao{

    private final Sql2o sql2o;

    public Sql2oHeroDao(Sql2o sql2o){
        this.sql2o=sql2o;
    }

    public void add(Hero hero){
        String sql = "INSERT INTO heroes (name, age, superpower, weakness, squad_id) VALUES (:name, :age, :superpower, :weakness, :squad_id)";
        try(Connection connection = sql2o.open()){
            int id =(int) connection.createQuery(sql,true)
                    .bind(hero)
                    .executeUpdate()
                    .getKey();
            hero.setId(id);
        } catch (Sql2oException e){
            System.out.println(e.getMessage());
        }
    }

    public Hero findById(int id){
        String sql = "SELECT * FROM heroes WHERE id=:id";
        try(Connection connection = sql2o.open()){
            return connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Hero.class);
        }
    }

    public List<Hero> getAll(){
        String sql = "SELECT * FROM heroes";
        try(Connection connection = sql2o.open()){
            return connection.createQuery(sql)
                    .executeAndFetch(Hero.class);
        }
    }

    public void update(int id,Hero hero){
        String sql ="UPDATE heroes SET (name, age, superpower, weakness, squad_id) = (:name, :age, :superpower, :weakness, :squad_id) WHERE id=:id";
        try(Connection connection = sql2o.open()){
            hero.setId(id);
            connection.createQuery(sql)
                    .bind(hero)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(int id){
        String sql = "DELETE FROM heroes WHERE id=:id";
        try(Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteAll(){
        String sql = "DELETE FROM heroes";
        try(Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException e) {
            System.out.println(e.getMessage());
        }
    }
}
