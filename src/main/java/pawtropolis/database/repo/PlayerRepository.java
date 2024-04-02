package pawtropolis.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.database.entity.PlayerEntity;
@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
}