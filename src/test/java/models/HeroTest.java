package models;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    //HELPER METHODS
    private final Hero hero = new Hero("Spiderman",25,"Webs","Small body",2);

    @Test
    public void gettersObtainValues(){
        assertEquals("Spiderman",hero.getName());
        assertEquals(25,hero.getAge());
        assertEquals("Webs",hero.getSuperpower());
        assertEquals("Small body",hero.getWeakness());
        assertEquals(2,hero.getSquad_id());
        assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("E yyyy MM dd 'at' hh:mm a")),hero.getJoinedAt());
    }

    @Test
    public void settersSetValues(){
        hero.setName("Superman");
        hero.setAge(45);
        hero.setSuperpower("Strength");
        hero.setWeakness("Kryptonite");
        hero.setId(2);
        hero.setSquad_id(3);
        assertEquals("Superman",hero.getName());
        assertEquals(45,hero.getAge());
        assertEquals("Strength",hero.getSuperpower());
        assertEquals("Kryptonite",hero.getWeakness());
        assertEquals(2,hero.getId());
        assertEquals(3,hero.getSquad_id());
    }
}