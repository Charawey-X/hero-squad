package dao;

import models.Hero;
import models.Squad;
import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oSquadDaoTest {

    private final String connectionString = "jdbc:postgresql://localhost/hero_squad_test";
    Sql2o sql2o = new Sql2o(connectionString,"x","230620");
    private Connection connection;
    private final Sql2oSquadDao squadDao = new Sql2oSquadDao(sql2o);
    private final Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);

    //HELPER METHODS
    private final Squad squad = new Squad("Titans",5,"Revenge");
    private final Squad aSecondSquad = new Squad("Avengers",10,"Avenge");
    private final Squad aThirdSquad = new Squad("Jokers",2,"Mischief");

    @Before
    public void setUp() {
        connection = sql2o.open();
    }

    @After
    public void tearDown() {
        connection.close();
    }

    @AfterEach
    void tearDownEach() {
        squadDao.deleteAll();
        heroDao.deleteAll();
    }

    @Test
    public void addMethodSavesSquad(){
        squadDao.add(squad);
        squadDao.add(aSecondSquad);
        assertEquals(2,squadDao.getAll().size());
    }

    @Test
    public void findByIdGetsCorrectSquad(){
        squadDao.add(squad);
        squadDao.add(aSecondSquad);
        squadDao.add(aThirdSquad);
        int id = aSecondSquad.getId();
        Squad foundSquad = squadDao.findById(id);
        assertEquals(aSecondSquad,foundSquad);
    }

    @Test
    public void addingSquadSetsId(){
        int id = squad.getId();
        squadDao.add(squad);
        assertNotEquals(id,squadDao.findById(squad.getId()).getId());
    }

    @Test
    public void squadIsUpdatedCorrectly(){
        squadDao.add(squad);
        squadDao.update(squad.getId(), aSecondSquad);
        Squad updatedSquad = squadDao.findById(squad.getId());
        assertEquals("Avenge",updatedSquad.getCause());
    }

    @Test
    public void deleteByIdRemovesSpecificSquad(){
        squadDao.add(squad);
        squadDao.add(aSecondSquad);
        squadDao.add(aThirdSquad);
        squadDao.deleteById(aSecondSquad.getId());
        assertEquals(2,squadDao.getAll().size());
        assertEquals(aThirdSquad.getId(),squadDao.findById(aThirdSquad.getId()).getId());
    }

    @Test
    public void getAllReturnsAllHeroes(){
        squadDao.add(squad);
        squadDao.add(aSecondSquad);
        squadDao.add(aThirdSquad);
        assertEquals(3,squadDao.getAll().size());
    }

    @Test
    public void allHeroesBySquadReturnsHeroesCorrectly(){
        squadDao.add(aSecondSquad);
        int squad_id = aSecondSquad.getId();
        Hero hero = new Hero("Spiderman",25,"Webs","Small body",squad_id);
        Hero aSecondHero = new Hero("Superman",45,"Strength","Kryptonite",squad_id);
        Hero aThirdHero = new Hero("Deadpool",40,"Immortal","Stupid",squad_id);
        heroDao.add(hero);
        heroDao.add(aSecondHero);
        assertEquals(2,squadDao.allHeroesBySquad(squad_id).size());
        assertFalse(squadDao.allHeroesBySquad(squad_id).contains(aThirdHero));
    }

    @Test
    public void deleteAllRemovesAllHeroes(){
        squadDao.add(squad);
        squadDao.add(aSecondSquad);
        squadDao.add(aThirdSquad);
        squadDao.deleteAll();
        assertEquals(0,squadDao.getAll().size());
    }
}