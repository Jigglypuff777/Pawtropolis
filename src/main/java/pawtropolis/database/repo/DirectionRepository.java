package pawtropolis.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.database.entity.DirectionEntity;
@Repository
public interface DirectionRepository extends JpaRepository<DirectionEntity, Long> {
}