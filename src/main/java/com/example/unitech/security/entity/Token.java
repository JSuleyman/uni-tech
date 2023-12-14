package com.example.unitech.security.entity;

import com.example.unitech.security.enums.TokenType;
import com.example.unitech.utility.core_entity.CoreEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "token")
public class Token extends CoreEntity {
    @Column(name = "token", unique = true)
    String token;

    @Enumerated(EnumType.STRING)
    TokenType tokenType = TokenType.BEARER;

    @Column(name = "revoked")
    boolean revoked;

    @Column(name = "expired")
    boolean expired;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    public User user;
}