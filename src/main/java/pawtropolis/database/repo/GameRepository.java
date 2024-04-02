package pawtropolis.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.database.entity.GameEntity;
@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {
}