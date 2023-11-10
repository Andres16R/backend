package com.famisanar.arquitectura.department.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.famisanar.arquitectura.department.adapter.out.persistence.DepartmentEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DepartmentEntityTest {

    private DepartmentEntity departmentEntity;

    @BeforeEach
    public void setUp() {
        departmentEntity = new DepartmentEntity();
        assertNotNull(departmentEntity);
    }

    @Test
    void testGetSetDepartmentId() {
        departmentEntity.setDepartmentId(1);
        assertEquals(1, departmentEntity.getDepartmentId());
    }

    @Test
    void testGetSetDepartmentName() {
        departmentEntity.setDepartmentName("Cundinamarca");
        assertEquals("Cundinamarca", departmentEntity.getDepartmentName());
    }

    @Test
    void testGetSetDepartmentAddress() {
        departmentEntity.setDepartmentAddress("Calle");
        assertEquals("Calle", departmentEntity.getDepartmentAddress());
    }

    @Test
    void testGetSetDepartmentCode() {
        departmentEntity.setDepartmentCode("CUN");
        assertEquals("CUN", departmentEntity.getDepartmentCode());
    }

    @Test
    void testConstructor() {
        DepartmentEntity departmentEntity = new DepartmentEntity(1, "Cundinamarca", "Calle", "CUN");
        departmentEntity.setDepartmentCode("CUN");
        assertNotNull(departmentEntity);
    }

}
