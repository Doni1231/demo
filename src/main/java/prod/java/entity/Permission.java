package prod.java.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import prod.java.entity.enums.PermissionName;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private PermissionName permissionName;

    public Permission(PermissionName permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public String getAuthority() {
        return permissionName.name();
    }
}

