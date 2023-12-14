package com.example.unitech.security.entity;

import com.example.unitech.security.enums.Role;
import com.example.unitech.utility.core_entity.CoreEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User extends CoreEntity implements UserDetails {
    @Column(name = "pin")
    String pin;

    @Column(name = "password")
    String password;

    @Enumerated(EnumType.STRING)
    Role role;

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "user")
    List<Token> tokens;
//
//    @Getter(AccessLevel.NONE)
//    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
//    private List<StarList> starLists;
//
//    @Getter(AccessLevel.NONE)
//    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
//    private List<LikeBtn> likeList;
//
//
//    @Getter(AccessLevel.NONE)
//    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
//    List<UserComment> userComments;
//
//    @Getter(AccessLevel.NONE)
//    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
//    List<UserCommentReply> userCommentReplies;
//
//    @Getter(value = AccessLevel.NONE)
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    List<UserEmailVerification> userEmailVerifications;
//
//    @Getter(value = AccessLevel.NONE)
//    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
//    List<Expenses> expensesList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return pin;
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
