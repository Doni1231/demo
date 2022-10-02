package prod.java.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PermissionRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Permission permission;


    public PermissionRole(Role role, Permission permission) {
        this.role = role;
        this.permission = permission;
    }
}
