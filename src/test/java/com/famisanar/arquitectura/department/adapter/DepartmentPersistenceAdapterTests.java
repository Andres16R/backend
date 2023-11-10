package com.famisanar.arquitectura.department.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.famisanar.arquitectura.department.adapter.out.persistence.DepartmentEntity;
import com.famisanar.arquitectura.department.adapter.out.persistence.DepartmentPersistenceAdapter;
import com.famisanar.arquitectura.department.adapter.out.persistence.interfaces.IDepartmentRepository;
import com.famisanar.arquitectura.department.domain.model.Department;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@SpringBootTest
class DepartmentPersistenceAdapterTests {

    @Mock
    private IDepartmentRepository departmentRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DepartmentPersistenceAdapter departmentPersistenceAdapter;

    @Mock
    private EntityManager entityManager;

     @Mock
    private StoredProcedureQuery storedProcedureQuery;

    @Test
    void department() {

        DepartmentEntity departmentA = new DepartmentEntity();
        departmentA.setDepartmentId(1);
        departmentA.setDepartmentName("Kennedy");
        departmentA.setDepartmentCode("kn");
        departmentA.setDepartmentAddress("calle");

        Department departmentC = new Department();
        departmentC.setDepartmentId(2);
        departmentC.setDepartmentName("Timiza");
        departmentC.setDepartmentCode("TM");
        departmentC.setDepartmentAddress("calle 75");

        List<Department> departmentLista = new ArrayList<>();
        departmentLista.add(departmentC);

        when(departmentRepository.findAll()).thenReturn(List.of(departmentA));
        when(modelMapper.map(departmentA, Department.class))
                .thenReturn(departmentC);

        List<DepartmentEntity> lstDepartments = this.departmentPersistenceAdapter.department();
        assertNotNull(lstDepartments);
        verify(this.departmentRepository).findAll();
    }

    @Test
    void departmentStoreProcedure() {

        when(entityManager.createStoredProcedureQuery("DEPARTMENLIST")).thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.registerStoredProcedureParameter("p_departmentid", Long.class, ParameterMode.IN)).thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.setParameter(eq("p_departmentid"), any(Integer.class))).thenReturn(storedProcedureQuery);        
        when(storedProcedureQuery.registerStoredProcedureParameter("result_cursor", void.class, ParameterMode.REF_CURSOR)).thenReturn(storedProcedureQuery);

        // Mock resultados del procedimiento almacenado
        Object[] row1 = new Object[]{1, "Cundinamarca"};
        Object[] row2 = new Object[]{2, "Bogota"};
        List<Object[]> results = new ArrayList<>();
        results.add(row1);
        results.add(row2);
        when(storedProcedureQuery.getResultList()).thenReturn(results);

        List<DepartmentEntity> departments = departmentPersistenceAdapter.departmentStoreProcedure(123);

        assertEquals(2, departments.size());
        assertEquals(1, departments.get(0).getDepartmentId());
        assertEquals("Cundinamarca", departments.get(0).getDepartmentName());
        assertEquals(2, departments.get(1).getDepartmentId());
        assertEquals("Bogota", departments.get(1).getDepartmentName());
    }

    @Test
    void deleteDepartmentById() {
        DepartmentEntity departmentA = new DepartmentEntity();
        departmentA.setDepartmentId(1);
        departmentA.setDepartmentName("Kennedy");
        departmentA.setDepartmentCode("kn");
        departmentA.setDepartmentAddress("calle");
        List<DepartmentEntity> departmentList = new ArrayList<>();
        departmentList.add(departmentA);
        when(departmentRepository.findAll()).thenReturn(List.of(departmentA));

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDepartmentId(1);

        this.departmentPersistenceAdapter.deleteDepartmentById(departmentEntity);
        assertTrue(true);
    }

}
