package com.famisanar.arquitectura.department.adapter.out.persistence;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.famisanar.arquitectura.department.adapter.out.persistence.interfaces.IDepartmentRepository;
import com.famisanar.arquitectura.department.application.port.out.IDepartmentPersistenceAdapter;
import com.famisanar.arquitectura.shared.annotations.PersistenceAdapter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@PersistenceAdapter
public class DepartmentPersistenceAdapter implements IDepartmentPersistenceAdapter{

    @Autowired
    private IDepartmentRepository departmentRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<DepartmentEntity> department(){
         return  departmentRepository.findAll();      
    }

    @Override
    public List<DepartmentEntity> departmentStoreProcedure(Integer departmentId){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("DEPARTMENLIST");
        query.registerStoredProcedureParameter("p_departmentid", Long.class, ParameterMode.IN);
        query.setParameter("p_departmentid", departmentId);
        query.registerStoredProcedureParameter("result_cursor", void.class, ParameterMode.REF_CURSOR);
        query.execute();

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DepartmentEntity> departments = new ArrayList<>();

        for (Object[] row : result) {
            DepartmentEntity department = new DepartmentEntity();
            department.setDepartmentId(((Number) row[0]).intValue());
            department.setDepartmentName((String) row[1]);
            departments.add(department);
        }

        return departments;

    }

    @Override
    public void deleteDepartmentById(DepartmentEntity departmentEntity) {
        departmentRepository.deleteById(departmentEntity.getDepartmentId());
    }
    
}
