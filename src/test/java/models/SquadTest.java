package models;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SquadTest {

    //HELPER METHODS
    private final Squad squad = new Squad("Titans",5,"Revenge");

    @Test
    public void gettersObtainValues(){
       assertEquals("Titans",squad.getName());
       assertEquals(5,squad.getMaxSize());
       assertEquals("Revenge",squad.getCause());
    }

    @Test
    public void settersSetValues(){
        squad.setName("Jokers");
        squad.setCause("Freedom");
        squad.setMaxSize(10);
        squad.setId(2);
        assertEquals("Jokers",squad.getName());
        assertEquals("Freedom",squad.getCause());
        assertEquals(10,squad.getMaxSize());
        assertEquals(2,squad.getId());
        assertEquals(10,squad.getSquadMembers().length);
    }

}