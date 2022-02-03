package root.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import root.model.entity.BankBookEntity;
import root.model.entity.CurrencyEntity;
import root.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface BankBookEntityRepository extends JpaRepository <BankBookEntity, Integer> {
    public List <BankBookEntity> findByUser(UserEntity user);

    public BankBookEntity findByNumber(String number);
}
