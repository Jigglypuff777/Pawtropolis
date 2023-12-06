package game.utils;

import game.domain.Item;

import java.util.*;

public class ItemFactory {

    private final Random random = new Random();
    private final Map<String, Integer> itemsMap = Map.of(
            "Coltello",1,
            "Katana", 2,
            "Pistola", 3,
            "Bazooka", 4,
            "Pokédex", 5,
            "Pokéball", 5,
            "Masterball", 10,
            "Ultraball", 7,
            "Pozione", 4,
            "Antidoto", 4
            );

    // TODO rimuovere??
    public ItemFactory() {

    }

    public Item getRandomItem() {
        List<String> keys = new ArrayList<>(itemsMap.keySet());
        String randomKey = keys.get(random.nextInt(keys.size()));
        int value = itemsMap.get(randomKey);

        return new Item(randomKey, value);
    }

    public Set<Item> getRandomItemsSet(int quantity) {
        Set<Item> itemSet = new HashSet<>();
        for (int i = 0; i < quantity; i++) {
            itemSet.add(getRandomItem());
        }
        return itemSet;
    }
}
