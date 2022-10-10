package prod.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import prod.java.entity.Role;
import prod.java.entity.enums.RoleName;

import java.util.Set;



public interface RoleRepository extends JpaRepository<Role, Integer> {
    Set<Role> findAllByRoleName(RoleName roleName);
}
