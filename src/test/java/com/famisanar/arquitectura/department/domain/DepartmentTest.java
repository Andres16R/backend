package com.famisanar.arquitectura.department.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.famisanar.arquitectura.department.domain.model.Department;

@SpringBootTest
class DepartmentTest {

    private Department department;

    @BeforeEach
    public void setUp() {
        department = new Department();
    }

    @Test
    void testGetSetDepartmentId() {
        department.setDepartmentId(1);
        assertEquals(1, department.getDepartmentId());
    }

    @Test
    void testGetSetDepartmentName() {
        department.setDepartmentName("Cundinamarca");
        assertEquals("Cundinamarca", department.getDepartmentName());
    }

    @Test
    void testGetSetDepartmentAddress() {
        department.setDepartmentAddress("Calle");
        assertEquals("Calle", department.getDepartmentAddress());
    }

    @Test
    void testGetSetDepartmentCode() {
        department.setDepartmentCode("CUN");
        assertEquals("CUN", department.gepartmentCode());
    }
}
