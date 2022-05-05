package herosquad;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        Hero.clearHeroes();
    }
    @Test
    public Hero newHero(){
        return new Hero("Thor", 100, "Hammer", "Emotional");
    }

    @Test
    public void heroObjectsCorrectlyInstantiated(){
        Hero hero = newHero();
        assertEquals(true, hero instanceof Hero);
    }

    @Test
    public void heroesAreCorrectlyReturned(){
        Hero hero = newHero();
        Hero aSecondHero = new Hero("Black Panther", 40, "Healing", "Human" );
        assertEquals(2, Hero.getInstances().size());
    }
    @Test
    public void getInstancesReturnsHeroes(){
        Hero hero = newHero();
        Hero aSecondHero = new Hero("Black Panther", 40, "Healing", "Human" );
        assertTrue(Hero.getInstances().contains(hero));
        assertTrue(Hero.getInstances().contains(aSecondHero));
    }

    @Test
    void getAge() {
        Hero hero = newHero();
        assertEquals(100, hero.getAge());
    }


    @Test
    void getName() {
        Hero hero = newHero();
        assertEquals("Thor", hero.getName());
    }


    @Test
    void getSuperpower() {
        Hero hero = newHero();
        assertEquals("Hammer", hero.getSuperpower());
    }


    @Test
    void getWeakness() {
        Hero hero = newHero();
        assertEquals("Emotional", hero.getWeakness());
    }
}