package pawtropolis.animals;


import java.time.LocalDate;

public class Tiger extends TailedAnimal {

    public Tiger(long id, String nickname, String favoriteFood, int age, LocalDate dateEntry, double weight, double height, double tailLength) {
        super(id, nickname, favoriteFood, age, dateEntry, weight, height, tailLength);
    }
}
