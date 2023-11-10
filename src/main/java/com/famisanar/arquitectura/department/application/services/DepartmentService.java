package com.famisanar.arquitectura.department.application.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.famisanar.arquitectura.department.adapter.out.persistence.DepartmentEntity;
import com.famisanar.arquitectura.department.domain.model.Department;
import com.famisanar.arquitectura.shared.annotations.UseCase;
import com.famisanar.arquitectura.department.application.port.in.IDepartmentService;
import com.famisanar.arquitectura.department.application.port.out.IDepartmentPersistenceAdapter;

@UseCase
public class DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentPersistenceAdapter departmentPersistenceAdapter;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Department> department() {
        List<DepartmentEntity> lst = departmentPersistenceAdapter.department();
        return lst.stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, Department.class))
                .toList();
    }

    @Override
    public List<Department> departmentStoreProcedure(Integer departmentId) {
        List<DepartmentEntity> lst = departmentPersistenceAdapter.departmentStoreProcedure(departmentId);
        return lst.stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, Department.class))
                .toList();
    }

    @Override
    public void deleteDepartmentById(Department department) {
        DepartmentEntity departmentEntity = modelMapper.map(department, DepartmentEntity.class);
        departmentPersistenceAdapter.deleteDepartmentById(departmentEntity);
    }

}
