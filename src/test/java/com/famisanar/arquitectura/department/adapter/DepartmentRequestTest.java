package com.famisanar.arquitectura.department.adapter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.famisanar.arquitectura.department.adapter.in.dto.DepartmentRequest;

@SpringBootTest
class DepartmentRequestTest {

  @Test
  void testDepartmentRequestConstructorAndGetters() {
    DepartmentRequest departmentRequest = new DepartmentRequest();
    departmentRequest.setDepartmentId(123);
    Integer departmentId = departmentRequest.getDepartmentId();
    assertEquals(123, departmentId);
  }
}
