package com.famisanar.arquitectura.department.application.port.in;

import java.util.List;

import com.famisanar.arquitectura.department.domain.model.Department;

public interface IDepartmentService {
      List<Department> department();

      List<Department> departmentStoreProcedure(Integer departmentId);

      void deleteDepartmentById(Department department);

}
