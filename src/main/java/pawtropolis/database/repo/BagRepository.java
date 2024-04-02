package pawtropolis.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.database.entity.BagEntity;
@Repository
public interface BagRepository extends JpaRepository<BagEntity, Long> {
}