package pawtropolis.animals;


import java.time.LocalDate;

public class Eagle extends WingedAnimal {

    public Eagle(long id, String nickname, String favoriteFood, int age, LocalDate dateEntry, double weight, double height, double wingspan) {
        super(id, nickname, favoriteFood, age, dateEntry, weight, height, wingspan);
    }
}
