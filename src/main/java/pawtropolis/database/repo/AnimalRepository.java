package pawtropolis.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.database.entity.AnimalEntity;
import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {

    List<AnimalEntity> findByRoom_Id(long roomId);
}