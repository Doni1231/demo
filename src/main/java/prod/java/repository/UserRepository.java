package prod.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prod.java.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
