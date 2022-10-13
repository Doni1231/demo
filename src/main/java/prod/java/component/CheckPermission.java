package prod.java.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import prod.java.entity.User;
import prod.java.entity.enums.PermissionName;
import prod.java.repository.PermissionRepository;

@Component("CheckPermission")
public class CheckPermission {
    @Autowired
    PermissionRepository permissionRepository;


    public boolean checkPermission(UserDetails principal, String permissionName) {
        User user = (User) principal;
        return user.getPermissions().stream().anyMatch(permission ->
                permission.getPermissionName().equals(PermissionName.valueOf(permissionName)));
    }
}
