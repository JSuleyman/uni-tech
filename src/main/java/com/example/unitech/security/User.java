package com.example.unitech.security;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "email")
    String email;

    @Column(name = "user_type")
    String userType;

    @Column(name = "password")
    String password;

    @Column(name = "firstname")
    String firstname;

    @Column(name = "lastname")
    String lastname;

    @Column(name = "user_image")
    String userImage;

    @Column(name = "verified")
    boolean verified;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Getter(AccessLevel.NONE) //TODO equals and hashcode
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;
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
        return email;
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
