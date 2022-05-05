package herosquad;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquadTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        Squad.clearSquads();
    }

    @Test
    public Squad setUpNewSquad(){
        return new Squad("Avengers", "Crime");
    }

    @Test
    void getSquadName(){
        Squad squad = setUpNewSquad();
        assertEquals("Avengers", squad.getSquadName());
    }

    @Test
    void getMembersReturnsAllMembers_true(){
        Squad squad = setUpNewSquad();
        Squad squad2 = new Squad("Titans", "Pollution");
        assertTrue(Squad.getInstances().contains(squad));
        assertTrue(Squad.getInstances().contains(squad2));
    }

    @Test
    void getId(){
        Squad squad = setUpNewSquad();
        assertEquals(1, squad.getId());
    }

    @Test
    void getSquadCause(){
        Squad squad = setUpNewSquad();
        assertEquals("Crime", squad.getSquadCause());
    }
}