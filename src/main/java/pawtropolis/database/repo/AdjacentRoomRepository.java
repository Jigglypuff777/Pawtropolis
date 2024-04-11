package pawtropolis.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pawtropolis.database.entity.AdjacentRoomEntity;

import java.util.List;

@Repository
public interface AdjacentRoomRepository extends JpaRepository<AdjacentRoomEntity, Long> {

    @Query("SELECT ar FROM AdjacentRoomEntity ar JOIN FETCH ar.room JOIN FETCH ar.adjacentRoom WHERE ar.room.id = :roomId OR ar.adjacentRoom.id = :roomId")
    List<AdjacentRoomEntity> findAdjacentsRooms(long roomId);
}

