package com.example.employeemanage.core.data.Repository;

import com.example.employeemanage.core.data.Enitity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity, Long > {
    Optional<ImageEntity> findByName(String fileName);

    Optional<ImageEntity> findByEmployeeId(Long employeeId);
    void deleteByEmployeeId(Long employeeId);
}
