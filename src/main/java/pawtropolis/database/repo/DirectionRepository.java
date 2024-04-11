package pawtropolis.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.database.entity.DirectionEntity;
import pawtropolis.game.gamecontroller.DirectionEnum;

@Repository
public interface DirectionRepository extends JpaRepository<DirectionEntity, Long> {
    DirectionEnum findDirectionEnumById(long directionId);
}