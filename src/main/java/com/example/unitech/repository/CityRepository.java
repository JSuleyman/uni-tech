package com.example.unitech.repository;

import com.example.unitech.entity.City;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CityRepository extends
        JpaRepository<City, String>,
        JpaSpecificationExecutor<City> {
}
