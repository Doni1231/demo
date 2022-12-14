package prod.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prod.java.entity.Permission;
import prod.java.entity.enums.PermissionName;

import java.util.List;
import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
//    Set<Permission> findAllByRoleName(PermissionName permissionName);

    @Query(value = "select * from permission where id in(select permission_id from permission_role where role_id=(select id from role where role_name=:roleName))", nativeQuery = true)
    List<Permission> findAllByRoleName(@Param("roleName") String roleName);
}
