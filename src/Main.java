import controller.ZooManager;
import model.Eagle;
import model.Lion;
import model.Tiger;

import java.time.LocalDate;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ZooManager zoo = new ZooManager();
        zoo.addAnimal(new Eagle("Eagle1", "abc", 3, LocalDate.now(), 25, 100, 100));
        zoo.addAnimal(new Eagle("Eagle2", "cvb", 5, LocalDate.now(), 30, 110, 150));
        zoo.addAnimal(new Lion("Lion1", "abc", 3, LocalDate.now(), 150, 124, 70));
        zoo.addAnimal(new Lion("Lion2", "cvb", 5, LocalDate.now(), 160, 136, 59));
        zoo.addAnimal(new Tiger("Tiger1", "abc", 3, LocalDate.now(), 120, 111, 32));
        zoo.addAnimal(new Tiger("Tiger2", "cvb", 5, LocalDate.now(), 140, 124, 43));
        //System.out.println(zoo.getHeaviestSpecimen(Lion.class));
        }
    }
