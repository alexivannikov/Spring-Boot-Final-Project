package root.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import root.model.entity.StatusEntity;

public interface StatusEntityRepository extends JpaRepository<StatusEntity, Integer> {
   StatusEntity findByName(String name);
}
