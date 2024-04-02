package pawtropolis.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.database.entity.AdjacentRoomEntity;

@Repository
public interface AdjacentRoomRepository extends JpaRepository<AdjacentRoomEntity, Long> {
}