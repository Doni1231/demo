package prod.java.component;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import prod.java.entity.Permission;
import prod.java.entity.PermissionRole;
import prod.java.entity.Role;
import prod.java.entity.User;
import prod.java.entity.enums.PermissionName;
import prod.java.entity.enums.RoleName;
import prod.java.repository.PermissionRepository;
import prod.java.repository.PermissionRoleRepository;
import prod.java.repository.RoleRepository;
import prod.java.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataLoader implements CommandLineRunner {

    final
    UserRepository userRepository;

    final
    RoleRepository roleRepository;

    final
    PasswordEncoder passwordEncoder;

    final PermissionRepository permissionRepository;

    final
    PermissionRoleRepository permissionRoleRepository;

    @Value("${spring.sql.init.mode}")
    private String initMode;

    public DataLoader(
            UserRepository userRepository,
            RoleRepository roleRepository,
            @Lazy PasswordEncoder passwordEncoder,
                      PermissionRepository permissionRepository,
                      PermissionRoleRepository permissionRoleRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.permissionRepository = permissionRepository;
        this.permissionRoleRepository = permissionRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {

            List<Role> roles = roleRepository.findAll();

            List<PermissionRole> permissionRoles = new ArrayList<>();

            for (PermissionName permissionName : PermissionName.values()) {

                Permission savedPermission = permissionRepository.save(
                        new Permission(permissionName));

                for (RoleName roleName : permissionName.roleNames) {
                    permissionRoles.add(new PermissionRole(
                            getRoleByRoleName(roles, roleName), savedPermission));
                }
            }
            permissionRoleRepository.saveAll(permissionRoles);

            userRepository.save(
                    new User(
                            "SuperAdmin",
                            "SuperAdminov",
                            "+998991234567",
                            "superadmin@gmail.com",
                            passwordEncoder.encode("root123"),
                            "admin123",
                            new HashSet<>(roleRepository.findAllByRoleName(RoleName.ROLE_SUPER_ADMIN)),
                            true,
                            new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_SUPER_ADMIN.name()))
                    )
            );

//        } else if (initMode.equals("never")) {
//            List<Permission> permissionList = permissionRepository.findAll();
//            List<PermissionName> permissionNames = Arrays.asList(PermissionName.values());
//            List<PermissionName> notSavedPermission = permissionNames.stream().filter(permissionName -> !isDbSaved(permissionName, permissionList)).collect(Collectors.toList());
//            List<Role> roles = roleRepository.findAll();
//
//            List<PermissionRole> permissionRoles = new ArrayList<>();
//
//            for (PermissionName permissionName : notSavedPermission) {
//
//                Permission savedPermission = permissionRepository.save(
//                        new Permission(permissionName));
//
//                for (RoleName roleName : permissionName.roleNames) {
//                    permissionRoles.add(new PermissionRole(
//                            getRoleByRoleName(roles, roleName), savedPermission));
//                }
//            }
//            permissionRoleRepository.saveAll(permissionRoles);
        }
    }



    private Role getRoleByRoleName(List<Role> roles, RoleName roleName) {
        for (Role role : roles) {
            if (role.getRoleName().equals(roleName))
                return role;
        }
        return null;
    }

    private boolean isDbSaved(PermissionName permissionName, List<Permission> permissions) {
        return permissions.stream().anyMatch(permission -> permission.getPermissionName().equals(permissionName));
    }

}
