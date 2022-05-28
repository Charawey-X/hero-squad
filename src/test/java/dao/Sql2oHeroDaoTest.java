package dao;

import models.Hero;
import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oHeroDaoTest {
    private final String connectionString = "jdbc:postgresql://localhost/hero_squad_test";
    Sql2o sql2o = new Sql2o(connectionString,"x","230620");
    private Connection connection;
    private final Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);

    //HELPER METHODS
    private final Hero hero = new Hero("Spiderman",25,"Webs","Small body",2);
    private final Hero aSecondHero = new Hero("Superman",45,"Strength","Kryptonite",2);
    private final Hero aThirdHero = new Hero("Deadpool",40,"Immortal","Stupid",2);

    @Before
    public void setUp() {
        connection =sql2o.open();
    }

    @After
    public void tearDown() {
        connection.close();
    }

    @AfterEach
    void tearDownEach() {
        heroDao.deleteAll();
    }

    @Test
    public void addMethodSavesHero(){
        heroDao.add(hero);
        heroDao.add(aSecondHero);
        assertEquals(2,heroDao.getAll().size());
    }

    @Test
    public void findByIdGetsCorrectHero(){
        heroDao.add(hero);
        heroDao.add(aSecondHero);
        heroDao.add(aThirdHero);
        int id = aSecondHero.getId();
        Hero foundHero = heroDao.findById(id);
        assertEquals(aSecondHero,foundHero);
    }

    @Test
    public void addingHeroSetsId(){
        int id = hero.getId();
        heroDao.add(hero);
        assertNotEquals(id,heroDao.findById(hero.getId()).getId());
    }

    @Test
    public void heroIsUpdatedCorrectly(){
        heroDao.add(hero);
        heroDao.update(hero.getId(), aSecondHero);
        Hero updatedHero = heroDao.findById(hero.getId());
        assertEquals("Superman",updatedHero.getName());
    }

    @Test
    public void deleteByIdRemovesSpecificHero(){
        heroDao.add(hero);
        heroDao.add(aSecondHero);
        heroDao.add(aThirdHero);
        heroDao.deleteById(aSecondHero.getId());
        assertEquals(2,heroDao.getAll().size());
        assertEquals(aThirdHero.getId(),heroDao.findById(aThirdHero.getId()).getId());
    }

    @Test
    public void getAllReturnsAllHeroes(){
        heroDao.add(hero);
        heroDao.add(aSecondHero);
        heroDao.add(aThirdHero);
        assertEquals(3,heroDao.getAll().size());
    }

    @Test
    public void deleteAllRemovesAllHeroes(){
        heroDao.add(hero);
        heroDao.add(aSecondHero);
        heroDao.add(aThirdHero);
        heroDao.deleteAll();
        assertEquals(0,heroDao.getAll().size());
    }
}