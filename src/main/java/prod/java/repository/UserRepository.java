package prod.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prod.java.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

  boolean  existsByEmailAndIdNot(String email,UUID id);

  boolean existsByPhoneNumberAndIdNot(String phoneNumber, UUID id);

  Optional<User> findByEmailCodeAndChangingEmail(String emailCode, String email);

  Optional<User> findByEmailCodeAndEmail(String emailCode, String email);
}
