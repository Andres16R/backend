package com.famisanar.arquitectura.department.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.famisanar.arquitectura.department.adapter.out.persistence.interfaces.IDepartmentRepository;
import com.famisanar.arquitectura.department.application.port.out.IDepartmentPersistenceAdapter;
import com.famisanar.arquitectura.department.application.services.DepartmentService;
import com.famisanar.arquitectura.department.domain.model.Department;

@SpringBootTest
class DepartmentServiceTests {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private IDepartmentPersistenceAdapter departmentPersistenceAdapter;

    @Mock
    private IDepartmentRepository departmentRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void testdepartmentList() {
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

        when(departmentPersistenceAdapter.department()).thenReturn(List.of(departmentA));
        when(modelMapper.map(departmentA, Department.class))
                .thenReturn(departmentC);

        List<Department> lstDepartments = this.departmentService.department();
        assertNotNull(lstDepartments);
        verify(this.departmentPersistenceAdapter).department();
    }

    @Test
    void testdepartmentStoreProcedureList() {
        Integer departmentId = 1;

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

        List<DepartmentEntity> departmentEntityList = new ArrayList<>();
        departmentEntityList.add(departmentA);

        List<Department> expectedDepartments = new ArrayList<>();
        expectedDepartments.add(departmentC);

        when(departmentPersistenceAdapter.departmentStoreProcedure(departmentId)).thenReturn(departmentEntityList);
        when(modelMapper.map(departmentEntityList.get(0), Department.class)).thenReturn(departmentC);

        List<Department> actualDepartments = departmentService.departmentStoreProcedure(departmentId);

        verify(departmentPersistenceAdapter).departmentStoreProcedure(departmentId);
        verify(modelMapper).map(departmentEntityList.get(0), Department.class);
        assertEquals(expectedDepartments, actualDepartments);
    }

    @Test
    void testddeleteDepartmentById() {
        DepartmentEntity departmentA = new DepartmentEntity();
        departmentA.setDepartmentId(1);
        departmentA.setDepartmentName("Kennedy");
        departmentA.setDepartmentCode("kn");
        departmentA.setDepartmentAddress("calle");
        List<DepartmentEntity> departmentList = new ArrayList<>();
        departmentList.add(departmentA);
        when(departmentPersistenceAdapter.department()).thenReturn(List.of(departmentA));

        Department department = new Department();
        department.setDepartmentId(1);

        this.departmentService.deleteDepartmentById(department);
        assertTrue(true);
    }
}
