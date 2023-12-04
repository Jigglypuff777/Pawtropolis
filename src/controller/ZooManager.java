package controller;

import model.Animal;
import model.TailedAnimal;
import model.WingedAnimal;

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
