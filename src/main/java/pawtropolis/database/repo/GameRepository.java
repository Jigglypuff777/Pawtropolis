package pawtropolis.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.database.entity.GameEntity;
import pawtropolis.database.entity.PlayerEntity;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    GameEntity findByPlayer_Id(long playerId);

    Optional<GameEntity> findByPlayer(PlayerEntity savedPlayer);
}