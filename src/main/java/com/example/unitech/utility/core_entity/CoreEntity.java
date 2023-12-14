package com.example.unitech.utility.core_entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
@SuperBuilder
@EntityListeners({CoreEntityListener.class})
@MappedSuperclass
public class CoreEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    String id;
    @Column(name = "created_by")
    String createdBy;
    @Column(name = "created_time", nullable = false)
    Long createdTime;
    @Column(name = "status", nullable = false)
    String status;
    @Column(name = "insert_date", nullable = false)
    Long insertDate;
    @Column(name = "modification_date")
    Long modificationDate;
    @Column(name = "modification_time")
    Long modificationTime;
    @Column(name = "created_date", nullable = false)
    Long createdDate;
    @Column(name = "modification_by")
    String modificationBy;
}
