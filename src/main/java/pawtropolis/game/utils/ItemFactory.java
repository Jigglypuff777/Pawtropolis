package pawtropolis.game.utils;

import pawtropolis.game.domain.Item;

import java.util.*;

public class ItemFactory {
    private static ItemFactory instance = null;
    private static final Random random = new Random();
    private final Map<String, Integer> itemsMap = Map.of(
            "Knife", 1,
            "Katana", 2,
            "Gun", 3,
            "Bazooka", 4,
            "Long sword", 5,
            "Pokéball", 3,
            "Masterball", 5,
            "Ultraball", 4,
            "Potion", 2,
            "Antidote", 1
    );

    private ItemFactory() {
    }

    public static ItemFactory getInstance() {
        if (instance == null) {
            instance = new ItemFactory();
        }
        return instance;
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
