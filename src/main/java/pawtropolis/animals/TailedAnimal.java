package pawtropolis.animals;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class TailedAnimal extends Animal {
    private double tailLength;

    public TailedAnimal(long id, String nickname, String favoriteFood, int age, LocalDate dateEntry, double weight, double height, double tailLength) {
        super(id, nickname, favoriteFood, age, dateEntry, weight, height);
        this.tailLength = tailLength;
    }

}
