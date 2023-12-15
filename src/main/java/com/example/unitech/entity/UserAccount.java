package com.example.unitech.entity;


import com.example.unitech.custom_exception.InvalidAccountNumberException;
import com.example.unitech.enums.AccountStatus;
import com.example.unitech.security.entity.User;
import com.example.unitech.utility.core_entity.CoreEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@Entity
@Table(name = "user_account")
public class UserAccount extends CoreEntity {
    @Column(name = "account_name", nullable = false)
    String accountName;

    @Column(name = "account_number", nullable = false)
    Long accountNumber;

    @Column(name = "account_balance")
    Double accountBalance;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_status")
    AccountStatus accountStatus;

    @JoinColumn(name = "fk_user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    User user;

    @PrePersist
    public void prePersist() {
        this.accountStatus = AccountStatus.ACTIVE;
        this.accountBalance = 0.0;
        if (String.valueOf(this.accountNumber).trim().length() != 16) {
            throw new InvalidAccountNumberException();
        }
    }
}
