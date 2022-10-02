package prod.java.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import prod.java.entity.template.UserDetail;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
public class User extends UserDetail {

    @Column(nullable = false)
    private String firstName;   //ISMI

    @Column(nullable = false)
    private String lastName;   //FAMILYASI

    @Column(nullable = false)
    private String phoneNumber;   //TELEFON RAQAMI. BUNDAN USERNAME SIFATIDA FOYDLANS HAM BO'LADI

    @JsonIgnore
    @Column(nullable = false)
    private String password;   //PAROLI

    @OneToOne
    private Attachment photo;   //USERNING AVATAR PHOTOSI


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;//USERNING ROLELARI

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_permission",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Set<Permission> permissions;//USERNING HUQUQLARI


    private Boolean onlineAgent;

    private String stripeCustomerId;//

    private boolean isAccountNonExpired = true;

    private boolean isAccountNonLocked = true;

    private boolean isCredentialsNonExpired = true;

    private boolean enabled = false;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorityList = new HashSet<>();
//        permissions.forEach(permission -> {
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getPermissionName().name());
//            grantedAuthorityList.add(grantedAuthority);
//        });
//        roles.forEach(role -> {
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName().name());
//            grantedAuthorityList.add(grantedAuthority);
//        });
        if (permissions != null)
            grantedAuthorityList.addAll(permissions);
        grantedAuthorityList.addAll(roles);
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
