package com.eojin.shopeasy_back.common.security;

import com.eojin.shopeasy_back.user.entity.User;
import com.eojin.shopeasy_back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private final User user;

    public UserDetailsImpl(User user) { //user 초기화
        this.user = user;
    }

    @Override
    public String getPassword() { return user.getPassword(); }
    @Override
    public String getUsername() { return user.getUsername(); }

    //권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}
