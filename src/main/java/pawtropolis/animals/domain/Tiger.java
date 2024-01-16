package pawtropolis.animals.domain;

import java.time.LocalDate;

public class Tiger extends TailedAnimal {
    public Tiger(String name, String favouriteMeal, int age, LocalDate arrivalDate, double weight, int height, double tailLength) {
        super(name, favouriteMeal, age, arrivalDate, weight, height, tailLength);
    }
}
