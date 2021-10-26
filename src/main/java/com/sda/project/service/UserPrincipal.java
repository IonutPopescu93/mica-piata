package com.sda.project.service;

import com.sda.project.model.Role;
import com.sda.project.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserPrincipal implements UserDetails {

    private final User user;
    private final Role role;

    public UserPrincipal(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    // get permissions
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // create authority from user role
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());

        // add authority to authorities
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
