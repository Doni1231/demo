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

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String emailCode;

    @Column(nullable = false)
    private String changingEmail;

    @OneToOne
    private Attachment photo;   // USERNING AVATAR PHOTOSI


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
        if (permissions != null)
            grantedAuthorityList.addAll(permissions);
        grantedAuthorityList.addAll(roles);
        return grantedAuthorityList;
    }

    public User(String firstName, String lastName, String phoneNumber, String email,
                String password, String emailCode, Set<Role> roles, boolean enabled, Set<Permission> permissions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.emailCode = emailCode;
        this.roles = roles;
        this.enabled = enabled;
        this.permissions = permissions;
    }


    public User(String firstName, String lastName, String phoneNumber, String email,
                String password, String emailCode, Set<Role> roles, boolean enabled, Attachment photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.emailCode = emailCode;
        this.roles = roles;
        this.enabled = enabled;
        this.photo = photo;
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
