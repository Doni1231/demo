package prod.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import prod.java.entity.PermissionRole;

import java.util.List;



public interface PermissionRoleRepository extends JpaRepository<PermissionRole, Integer> {

    @RestResource(path = "byRole")
    List<PermissionRole> findAllByRoleId(@Param("roleId") Integer roleId);
}
