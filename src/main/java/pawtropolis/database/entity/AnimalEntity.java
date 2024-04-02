package pawtropolis.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "animal")
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "age")
    private Long age;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "species_id")
    private SpeciesEntity species;

    @Column(name = "wingspan")
    private Double wingspan;

    @Column(name = "tail_length")
    private Double tailLength;

}