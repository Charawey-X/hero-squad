package dao;

import models.Hero;

import java.util.List;

public abstract class HeroDao {

    //CREATE
    abstract void add(Hero hero);

    //READ
    abstract Hero findById(int id);
    abstract List<Hero> getAll();

    //UPDATE
    abstract void update(int id, Hero hero);

    //DELETE
    abstract void deleteById(int id);
    abstract void deleteAll();

}
