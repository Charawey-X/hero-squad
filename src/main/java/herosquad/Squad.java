package herosquad;

import java.util.ArrayList;

public class Squad {
    private String squadName;

    private String squadCause;

    private static ArrayList<Squad> instances = new ArrayList<>();

    public static void clearSquads(){
        instances.clear();
    }

    private int id;

    public Squad(String squadName, String squadCause){
        this.squadName = squadName;
        this.squadCause = squadCause;
        instances.add(this);
        this.id = instances.size();
    }


    public String getSquadName() {
        return squadName;
    }

    public void setSquadName(String squadName) {
        this.squadName = squadName;
    }

    public int getId() {
        return id;
    }

    public static ArrayList<Squad> getInstances() {
        return instances;
    }

    public String getSquadCause() {
        return squadCause;
    }

    public void setSquadCause(String squadCause) {
        this.squadCause = squadCause;
    }
}
