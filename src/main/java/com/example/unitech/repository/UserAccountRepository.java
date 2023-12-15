package com.example.unitech.repository;

import com.example.unitech.entity.UserAccount;
import com.example.unitech.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@Transactional
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    boolean existsByAccountNumber(Long accountNumber);

    List<UserAccount> findAllByUser_IdAndAccountStatus(String currentUserId, AccountStatus accountStatus);

    Optional<UserAccount> findByUser_IdAndAccountNumberAndAccountStatus(String currentUserId, Long accountNumber, AccountStatus accountStatus);

    Optional<UserAccount> findByUser_IdAndAccountNumber(String currentUserId, Long toAccountNumber);
}
