package com.famisanar.arquitectura.department.adapter.out.persistence.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famisanar.arquitectura.department.adapter.out.persistence.DepartmentEntity;

public interface IDepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
    
}
