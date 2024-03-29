package com.exercise.projectschool.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER_INFO")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoEntity {
    @Id
    @GeneratedValue
    Long id;

    @Column(name = "USER_NAME")
    String userName;

    @Column(nullable = false, name = "EMAIL_ID", unique = true)
    String emailId;

    @Column(nullable = false, name = "PASSWORD")
    String password;

    @Column(name = "MOBILE_NUMBER")
    String mobileNumber;

    @Column(nullable = false, name = "ROLES")
    String roles;  //ROLE_ADMIN, ROLE_MANAGER --> [ROLE_ADMIN, ROLE_MANAGER]

    // Many-to-One relationship with RefreshTokenEntity
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RefreshTokenEntity> refreshTokens;
}
