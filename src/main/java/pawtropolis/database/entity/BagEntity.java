package pawtropolis.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "bag")
public class BagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "available_slots")
    private Integer availableSlots;

    @OneToMany(mappedBy = "bag")
    private Set<ItemEntity> items = new LinkedHashSet<>();

    @OneToMany(mappedBy = "bag")
    private Set<PlayerEntity> players = new LinkedHashSet<>();

}