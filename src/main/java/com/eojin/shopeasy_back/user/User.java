package com.eojin.shopeasy_back.user;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
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

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateProfileUrl(String profile_url) {
        this.profile_url = profile_url;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
