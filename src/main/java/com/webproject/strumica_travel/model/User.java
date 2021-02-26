package com.webproject.strumica_travel.model;

import com.webproject.strumica_travel.model.enumeration.Role;
import com.webproject.strumica_travel.service.UserService;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Data
@Entity
@Table(name = "tUsers")
public class User implements UserDetails {
    @Id
    private String username;
    private String password;
    private String name;
    private String lastname;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean isAccountNonExpired=true;
    private boolean isAccountNonLocked=true;
    private boolean isCredentialsNonExpired=true;
    private boolean isEnabled=true;
    public User() {
    }


    public User(String username, String password, String name, String lastname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.role = role;
    }
    public User(String username, String password, String name, String lastname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
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
        return isEnabled;
    }
}
