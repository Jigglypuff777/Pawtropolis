package pawtropolis.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.database.entity.RoomEntity;
@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
}