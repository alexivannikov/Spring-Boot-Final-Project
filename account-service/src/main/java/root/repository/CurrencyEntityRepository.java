package root.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import root.model.entity.CurrencyEntity;

public interface CurrencyEntityRepository extends JpaRepository<CurrencyEntity, Integer> {
    CurrencyEntity findByName(String name);
}
