package com.eojin.shopeasy_back.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //+1
    @Column(nullable = false)
    private Long id;

    private Long kakaoId;
    private Long naverId;
    private Long googleId;

    @Column(nullable = false, unique = true)
    private String username; //id in rendering

    @Column(unique = true)
    private String realname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    private String profile; //uuid
    private int profile_size;
    private int profile_delete_confirm;

    @Column(nullable = false)
    private String password;

    private Date user_created_date;

    @Builder
    public User(String username, String realname, String email, UserRole role, String profile, String save_profile, String password) {
        this.username = username;
        this.realname = realname;
        this.email = email;
        this.role = role;
        this.profile = profile;
        this.password = password;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
    public void updateRealname(String realname) {
    this.realname = realname;
    }
    public void updateProfile(String profile) {
    this.profile = profile;
    }
}
