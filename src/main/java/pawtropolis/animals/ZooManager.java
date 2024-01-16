package pawtropolis.animals;

import pawtropolis.animals.domain.*;

import java.time.LocalDate;
import java.util.*;

public class ZooManager {
    private final Map<Class<? extends Animal>, List<Animal>> animalsMap;

    public ZooManager() {
        animalsMap = new HashMap<>();
    }

    public void addAnimal(Animal animal) {
        if (animal == null) {
            return;
        }
        animalsMap.computeIfAbsent(animal.getClass(), k -> new ArrayList<>()).add(animal);
    }

    public void initializeZoo() {
        addAnimal(new Eagle("Eagle1", "abc", 3, LocalDate.now(), 25, 100, 100));
        addAnimal(new Eagle("Eagle2", "cvb", 5, LocalDate.now(), 30, 110, 150));
        addAnimal(new Lion("Lion1", "abc", 3, LocalDate.now(), 150, 124, 70));
        addAnimal(new Lion("Lion2", "cvb", 5, LocalDate.now(), 160, 136, 59));
        addAnimal(new Tiger("Tiger1", "abc", 3, LocalDate.now(), 120, 111, 32));
        addAnimal(new Tiger("Tiger2", "cvb", 5, LocalDate.now(), 140, 124, 43));
        addAnimal(new Eagle("Eagle3", "abc", 3, LocalDate.now(), 25, 100, 100));
        addAnimal(new Eagle("Eagle4", "cvb", 5, LocalDate.now(), 30, 110, 150));
        addAnimal(new Lion("Lion3", "abc", 3, LocalDate.now(), 150, 124, 70));
        addAnimal(new Lion("Lion4", "cvb", 5, LocalDate.now(), 160, 136, 59));
        addAnimal(new Tiger("Tiger3", "abc", 3, LocalDate.now(), 120, 111, 32));
        addAnimal(new Tiger("Tiger4", "cvb", 5, LocalDate.now(), 140, 124, 43));
    }

    private <T extends Animal> List<T> getSpeciesList(Class<T> animalSpecificClass) {
        if (animalsMap.containsKey(animalSpecificClass)) {
            return animalsMap.get(animalSpecificClass)
                    .stream()
                    .map(animalSpecificClass::cast)
                    .toList();
        }
        return animalsMap
                .entrySet()
                .stream()
                .filter(e -> animalSpecificClass.isAssignableFrom(e.getKey()))
                .flatMap(e -> e.getValue().stream())
                .map(animalSpecificClass::cast)
                .toList();
    }

    public <T extends Animal> Optional<T> getHighestSpecimen(Class<T> animalSpecificClass) {
        return getSpeciesList(animalSpecificClass)
                .stream()
                .max(Comparator.comparing(Animal::getHeight));
    }

    public <T extends Animal> Optional<T> getLowestSpecimen(Class<T> animalSpecificClass) {
        return getSpeciesList(animalSpecificClass)
                .stream()
                .min(Comparator.comparing(Animal::getHeight));
    }

    public <T extends Animal> Optional<T> getHeaviestSpecimen(Class<T> animalSpecificClass) {
        return getSpeciesList(animalSpecificClass)
                .stream()
                .max(Comparator.comparing(Animal::getWeight));
    }

    public <T extends Animal> Optional<T> getLightestSpecimen(Class<T> animalSpecificClass) {
        return getSpeciesList(animalSpecificClass)
                .stream()
                .min(Comparator.comparing(Animal::getWeight));
    }

    public Optional<TailedAnimal> getLongestTailedAnimal() {
        return getSpeciesList(TailedAnimal.class)
                .stream()
                .max(Comparator.comparing(TailedAnimal::getTailLength));
    }

    public Optional<WingedAnimal> getLargestWingspanAnimal() {
        return getSpeciesList(WingedAnimal.class)
                .stream()
                .max(Comparator.comparing(WingedAnimal::getWingspan));
    }
}
