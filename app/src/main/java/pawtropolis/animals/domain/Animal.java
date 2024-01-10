package pawtropolis.animals.domain;

import java.time.LocalDate;

public abstract class Animal {

    private final String name;
    private final String favouriteMeal;
    private final int age;
    private final LocalDate arrivalDate;
    private final double weight;
    private final int height;

    protected Animal(String name, String favouriteMeal, int age, LocalDate arrivalDate, double weight, int height) {
        this.name = name;
        this.favouriteMeal = favouriteMeal;
        this.age = age;
        this.arrivalDate = arrivalDate;
        this.weight = weight;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public String getFavouriteMeal() {
        return favouriteMeal;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public double getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }
}
