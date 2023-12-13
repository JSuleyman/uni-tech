package com.example.unitech.utility.core_entity;


import com.example.unitech.utility.QDate;
import com.example.unitech.utility.SessionManager;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoreEntityListener {
    private final SessionManager sessionManager;

    @PrePersist
    private void preInsert(CoreEntity entity) {
        entity.setStatus("A");
        entity.setInsertDate(QDate.getDateTimeLong());
        entity.setCreatedDate(QDate.getCurrentDateLong());
        entity.setCreatedTime(QDate.getCurrentTimeLong());
        entity.setCreatedBy(sessionManager.getCurrentUserId());
    }

    @PreUpdate
    private void preUpdate(CoreEntity entity) {
        entity.setModificationDate(QDate.getCurrentDateLong());
        entity.setModificationTime(QDate.getCurrentTimeLong());
        entity.setModificationBy(sessionManager.getCurrentUserId());
    }
}
