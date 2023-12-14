package com.example.unitech.test;

import com.example.unitech.utility.core_entity.CoreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@SuperBuilder
@Table(name = "custom_exception")
public class CustomException extends CoreEntity {

    @Column(name = "error_code")
    String errorCode;
    @Column(name = "lang")
    String lang;
    @Column(name = "message")
    String message;

}
