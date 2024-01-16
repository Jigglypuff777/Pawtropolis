package pawtropolis.animals.domain;

import lombok.Value;
import lombok.experimental.NonFinal;

import java.time.LocalDate;

@Value
@NonFinal
public abstract class Animal {
    String name;
    String favouriteMeal;
    int age;
    LocalDate arrivalDate;
    double weight;
    int height;
}