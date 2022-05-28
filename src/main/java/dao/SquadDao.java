package dao;

import models.Hero;
import models.Squad;

import java.util.List;

public abstract class SquadDao {

    //CREATE
    abstract void add(Squad squad);

    //READ
    abstract List<Squad> getAll();
    abstract Squad findById(int id);
    abstract List<Hero> allHeroesBySquad(int squad_id);

    //UPDATE
    abstract void update(int id,Squad squad);

    //DELETE
    abstract void deleteById(int id);
    abstract void deleteAll();

}
