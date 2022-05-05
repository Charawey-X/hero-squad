package herosquad;

import java.util.ArrayList;

public class Hero {
    private String name;

    private int age;

    private String superpower;

    private String weakness;

    private static ArrayList<Hero> instances = new ArrayList<>();

    public static void clearHeroes(){
        instances.clear();
    }


    public Hero(String name, int age, String superpower, String weakness ){
        this.name = name;
        this.age =age;
        this.superpower =superpower;
        this.weakness = weakness;
        instances.add(this);
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

    public static ArrayList<Hero> getInstances() {
        return instances;
    }
}
