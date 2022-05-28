package dao;

import models.Hero;
import models.Squad;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSquadDao extends SquadDao{

    private final Sql2o sql2o;

    public Sql2oSquadDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    public void add(Squad squad){
        String sql = "INSERT INTO squads (name,maxSize,cause) VALUES (:name,:maxSize,:cause)";
        try(Connection connection = sql2o.open()){
            int id = (int) connection.createQuery(sql,true)
                    .bind(squad)
                    .executeUpdate()
                    .getKey();
            squad.setId(id);
        } catch (Sql2oException e) {
            System.out.println(e.getMessage());
        }
    }

    public Squad findById(int id){
        String sql = "SELECT * FROM squads WHERE id=:id";
        try(Connection connection = sql2o.open()) {
        return connection.createQuery(sql)
                .addParameter("id",id)
                .executeAndFetchFirst(Squad.class);
        }
    }

    public List<Squad> getAll(){
        String sql = "SELECT * FROM squads";
        try(Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetch(Squad.class);
        }
    }

    public List<Hero> allHeroesBySquad(int squad_id){
        String sql = "SELECT * FROM heroes WHERE squad_id=:squad_id";
        try(Connection connection = sql2o.open()){
            return connection.createQuery(sql)
                    .addParameter("squad_id",squad_id)
                    .executeAndFetch(Hero.class);
        }
    }

    public void update(int id, Squad squad){
        String sql = "UPDATE squads SET (name,maxSize,cause) = (:name,:maxSize,:cause) WHERE id=:id";
        try(Connection connection = sql2o.open()){
            squad.setId(id);
            connection.createQuery(sql)
                    .bind(squad)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(int id){
        String sql = "DELETE FROM squads WHERE id=:id";
        try(Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAll(){
        String sql = "DELETE FROM squads";
        try(Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException e) {
            System.out.println(e.getMessage());
        }
    }
}
