package pawtropolis.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @OneToMany(mappedBy = "adjacentRoom")
    private Set<AdjacentRoomEntity> adjacentRooms = new LinkedHashSet<>();

    @OneToMany(mappedBy = "room")
    private Set<AnimalEntity> animals = new LinkedHashSet<>();

    @OneToMany(mappedBy = "room")
    private Set<GameEntity> games = new LinkedHashSet<>();

    @OneToMany(mappedBy = "room")
    private Set<ItemEntity> items = new LinkedHashSet<>();

}