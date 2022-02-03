package root.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import root.model.entity.TransactionEntity;

public interface TransactionEntityRepository extends JpaRepository <TransactionEntity, Integer> {
}
