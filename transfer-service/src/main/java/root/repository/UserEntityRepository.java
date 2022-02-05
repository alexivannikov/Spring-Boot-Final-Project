package root.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import root.model.entity.BankBookEntity;
import root.model.entity.CurrencyEntity;
import root.model.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
   @Query("select u from UserEntity u where u.email = ?1")
   public UserEntity findByEmail(String email);
}
