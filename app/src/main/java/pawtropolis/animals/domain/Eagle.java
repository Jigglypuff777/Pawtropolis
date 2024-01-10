package pawtropolis.animals.domain;

import java.time.LocalDate;

public class Eagle extends WingedAnimal {
    public Eagle(String name, String favouriteMeal, int age, LocalDate arrivalDate, double weight, int height, double wingspan) {
        super(name, favouriteMeal, age, arrivalDate, weight, height, wingspan);
    }
}
