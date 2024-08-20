package com.eojin.shopeasy_back.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//UserDetails 를 상속받아 인증 객체로 사용
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동으로 1씩 증가
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    // 매핑 어노테이션을 생략하면 필드명을 사용해서 컬럼명으로 매핑한다.
    @Column(unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;
    private String profile_url;

    @Column(nullable = false)
    private String password;

    @Builder
    public User(String username, String nickname, String email, String profile_url, String password) {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.profile_url = profile_url;
        this.password = password;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
    public void updateNickname(String nickname) {
    this.nickname = nickname;
}
    public void updateProfileUrl(String profile_url) {
    this.profile_url = profile_url;
}

    //권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }
    //사용자 id 반환
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
        //return UserDetails.super.isAccountNonExpired();
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
        //return UserDetails.super.isAccountNonLocked();
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        //return UserDetails.super.isCredentialsNonExpired();
    }
    @Override
    public boolean isEnabled() {
        return true;
        //return UserDetails.super.isEnabled();
    }
}
