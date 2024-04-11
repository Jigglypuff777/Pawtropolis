package pawtropolis.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "adjacent_room")
public class AdjacentRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "direction_id")
    private DirectionEntity direction;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "adjacent_room_id")
    private RoomEntity adjacentRoom;

}