package com.eojin.shopeasy_back.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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

    @Column(nullable = false, unique = true)
    private String username;

    @Column(unique = true)
    private String realname;

    @Column(nullable = false, unique = true)
    private String email;

    private String profile; //uuid
    private int profile_size;
    private int profile_delete_confirm;

    @Column(nullable = false)
    private String password;
    private Date user_created_date;

    @Builder
    public User(String username, String realname, String email, String profile, String save_profile, String password) {
        this.username = username;
        this.realname = realname;
        this.email = email;
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
