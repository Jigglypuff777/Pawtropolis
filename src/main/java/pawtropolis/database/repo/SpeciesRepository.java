package pawtropolis.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.database.entity.SpeciesEntity;
@Repository
public interface SpeciesRepository extends JpaRepository<SpeciesEntity, Long> {
}