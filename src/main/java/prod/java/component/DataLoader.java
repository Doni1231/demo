package prod.java.component;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

    }
//    final
//    UserRepository userRepository;
//    final
//    RoleRepository roleRepository;
//    final
//    PasswordEncoder passwordEncoder;
//    final PermissionRepository permissionRepository;
//    @Autowired
//    PermissionRoleRepository permissionRoleRepository;
//
//    @Value("${spring.datasource.initialization-mode}")
//    private String initMode;
//
//    public DataLoader(UserRepository userRepository, RoleRepository roleRepository,
//                      @Lazy PasswordEncoder passwordEncoder, PermissionRepository permissionRepository) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.permissionRepository = permissionRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        if (initMode.equals("always")) {
//
//            List<Role> roles = roleRepository.findAll();
//
//            List<PermissionRole> permissionRoles = new ArrayList<>();
//
//            for (PermissionName permissionName : PermissionName.values()) {
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
//
//            userRepository.save(
//                    new User(
//                            "SuperAdmin",
//                            "SuperAdminov",
//                            "+998991234567",
//                            "superadmin@gmail.com",
//                            passwordEncoder.encode("root123"),
//                            "admin123",
//                            new HashSet<>(roleRepository.findAllByRoleName(RoleName.ROLE_SUPER_ADMIN)),
//                            null,
//                            true,
//                            new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_SUPER_ADMIN.name()))
//                    )
//            );
//
////        } else if (initMode.equals("never")) {
////            List<Permission> permissionList = permissionRepository.findAll();
////            List<PermissionName> permissionNames = Arrays.asList(PermissionName.values());
////            List<PermissionName> notSavedPermission = permissionNames.stream().filter(permissionName -> !isDbSaved(permissionName, permissionList)).collect(Collectors.toList());
////            List<Role> roles = roleRepository.findAll();
////
////            List<PermissionRole> permissionRoles = new ArrayList<>();
////
////            for (PermissionName permissionName : notSavedPermission) {
////
////                Permission savedPermission = permissionRepository.save(
////                        new Permission(permissionName));
////
////                for (RoleName roleName : permissionName.roleNames) {
////                    permissionRoles.add(new PermissionRole(
////                            getRoleByRoleName(roles, roleName), savedPermission));
////                }
////            }
////            permissionRoleRepository.saveAll(permissionRoles);
////        }
//    }
//    }
//
//    private Role getRoleByRoleName(List<Role> roles, RoleName roleName) {
//        for (Role role : roles) {
//            if (role.getRoleName().equals(roleName))
//                return role;
//        }
//        return null;
//    }
//
//    private boolean isDbSaved(PermissionName permissionName, List<Permission> permissions) {
//        return permissions.stream().anyMatch(permission -> permission.getPermissionName().equals(permissionName));
//    }
//
}
