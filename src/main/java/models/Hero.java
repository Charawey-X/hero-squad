package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Hero {
    private String name;
    private int age;
    private String superpower;
    private String weakness;
    private final String joinedAt;
    private int id;

    private int squad_id;

    public Hero(String name, int age, String superpower, String weakness, int squad_id){
        this.name=name;
        this.age=age;
        this.superpower=superpower;
        this.weakness=weakness;
        this.joinedAt= LocalDateTime.now().format(DateTimeFormatter.ofPattern("E yyyy MM dd 'at' hh:mm a"));
        this.squad_id=squad_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public String getJoinedAt() {
        return joinedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSquad_id() {
        return squad_id;
    }

    public void setSquad_id(int squad_id) {
        this.squad_id = squad_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return getAge() == hero.getAge() &&
                getId() == hero.getId() &&
                getSquad_id() == hero.getSquad_id() &&
                Objects.equals(getName(),hero.getName()) &&
                Objects.equals(getSuperpower(),hero.getSuperpower()) &&
                Objects.equals(getWeakness(),hero.getWeakness());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getSuperpower(), getWeakness(), getId(), getSquad_id());
    }
}
