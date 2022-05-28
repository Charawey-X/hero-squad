package models;

import java.util.Objects;

public class Squad {

    private String name;
    private int maxSize;
    private String [] squadMembers;
    private String cause;

    private int id;

    public Squad(String name, int maxSize, String cause){
        this.name=name;
        this.maxSize=maxSize;
        this.cause=cause;
        this.squadMembers=new String[maxSize];
    }

    public String getName() {
        return name;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        this.squadMembers = new String[maxSize];
    }

    public String[] getSquadMembers() {
        return squadMembers;
    }

    public void setSquadMembers(String[] squadMembers) {
        this.squadMembers = squadMembers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Squad squad = (Squad) o;
        return getMaxSize() == squad.getMaxSize() &&
                getId() == squad.getId() &&
                Objects.equals(getCause(), squad.getCause());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaxSize(), getId(), getCause());
    }
}
