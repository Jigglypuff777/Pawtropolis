package pawtropolis.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "required_slots", nullable = false)
    private Integer requiredSlots;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "bag_id")
    private BagEntity bag;

}