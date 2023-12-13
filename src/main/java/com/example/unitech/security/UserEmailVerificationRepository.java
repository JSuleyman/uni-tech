package com.example.unitech.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserEmailVerificationRepository extends JpaRepository<UserEmailVerification, String> {

    @Query("""
            select e from UserEmailVerification e where e.user.id = :userId AND e.hasExpired = false
            """)
    UserEmailVerification userIsVerified(String userId);

    @Query("""
            select e from UserEmailVerification e where e.user.id = :userId and e.hasExpired = false
            """)
    List<UserEmailVerification> listUserById(String userId);

}
