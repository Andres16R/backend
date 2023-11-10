package com.famisanar.arquitectura.department.application.port.out;

import java.util.List;
import com.famisanar.arquitectura.department.adapter.out.persistence.DepartmentEntity;

public interface IDepartmentPersistenceAdapter {
    List<DepartmentEntity> department();
    List<DepartmentEntity> departmentStoreProcedure(Integer departmentId);
    void deleteDepartmentById(DepartmentEntity departmentEntity);
}
