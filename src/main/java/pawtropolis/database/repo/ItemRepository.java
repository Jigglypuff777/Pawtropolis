package pawtropolis.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.database.entity.ItemEntity;
@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}