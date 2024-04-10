package pawtropolis.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private long id;
    private String name;
    private String description;
    private int slotRequired;



}
