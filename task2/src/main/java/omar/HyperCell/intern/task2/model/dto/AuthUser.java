package omar.HyperCell.intern.task2.model.dto;


import lombok.Getter;
import omar.HyperCell.intern.task2.model.db.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class AuthUser implements UserDetails {
    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities;

//    public AuthUser(Optional<User> u) {
//        this.username = u.getUsername();
//        this.password = u.getPassword();
//        this.authorities = u.getAuthorities().stream()
//                .map(a -> new SimpleGrantedAuthority(a.getAuthority()))
//                .collect(Collectors.toList());
//    }

    public AuthUser(User user) {
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.authorities = (Collection<GrantedAuthority>) user.getAuthorities();
//        this.authorities = user.getAuthorities().stream()
//                .map(a -> new SimpleGrantedAuthority(a.getName()))
//                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
