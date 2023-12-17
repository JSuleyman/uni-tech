package com.example.unitech.utility.exception;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface CustomExceptionRepository extends JpaRepository<CustomException, String> {
    Optional<CustomException> findByErrorCodeAndLang(String errorCode, String lang);
}
