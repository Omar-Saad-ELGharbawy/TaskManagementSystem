package omar.HyperCell.intern.task2.model.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

import omar.HyperCell.intern.task2.model.db.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User{

    //Copy Constructor
//    public User(User user) {
//        this.userName = user.getUsername();
//        this.firstName = user.getFirstName();
//        this.lastName = user.getLastName();
//        this.password = user.getPassword();
//        this.role = user.getRole();
//        this.email = user.getEmail();
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false, unique = true)
    private Long id;

    @Column(name="user_name", nullable = false, unique = true)
    private String userName;

    @Column(name="first_name", nullable = false)
    private String firstName;
    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="password", nullable = false)
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }



//    @OneToMany(mappedBy = "user", targetEntity = Authorities.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Authorities> authorities;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "users_authorities", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
//    private List<Authorities> authorities;

//    private Role role;

    private transient String email;

//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getUsername() {
//        return userName;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//    @Override
//    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
//    }

}
