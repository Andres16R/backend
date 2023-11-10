package com.famisanar.arquitectura.department.adapter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.famisanar.arquitectura.department.adapter.in.controller.DepartmentController;
import com.famisanar.arquitectura.department.adapter.in.dto.DepartmentRequest;
import com.famisanar.arquitectura.department.adapter.out.dto.DepartmentResponse;
import com.famisanar.arquitectura.department.application.services.DepartmentService;
import com.famisanar.arquitectura.department.domain.model.Department;

@SpringBootTest
class DepartmentControllerTests {

    @Mock
    private DepartmentService departmentService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DepartmentController departmentController;

    @Test
    void testdepartment() {

        Department department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("Timiza");
        department.setDepartmentCode("TM");
        department.setDepartmentAddress("calle");

        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setDepartmentId(1);
        departmentResponse.setDepartmentName("Timiza");
        departmentResponse.setDepartmentCode("TM");
        departmentResponse.setDepartmentAddress("calle");

        List<Department> departmentList = new ArrayList<>();
        departmentList.add(department);

        when(departmentService.department()).thenReturn(departmentList);
        when(modelMapper.map(department, DepartmentResponse.class)).thenReturn(departmentResponse);

        ResponseEntity<List<DepartmentResponse>> response = departmentController.department();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<DepartmentResponse> result = response.getBody();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(departmentResponse, result.get(0));
    }

    @Test
    void testDepartmentCatchBlock() {
        when(departmentService.department()).thenThrow(new RuntimeException("Error en el servicio"));
        ResponseEntity<List<DepartmentResponse>> response = departmentController.department();
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        List<DepartmentResponse> body = response.getBody();
        assertNull(body);  
    }

    @Test
    void testdepartmentProcedure() {

        Department department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("Timiza");
        department.setDepartmentCode("TM");
        department.setDepartmentAddress("calle");

        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setDepartmentId(1);
        departmentResponse.setDepartmentName("Timiza");
        departmentResponse.setDepartmentCode("TM");
        departmentResponse.setDepartmentAddress("calle 75");

        List<Department> departmentList = new ArrayList<>();
        departmentList.add(department);

        when(departmentService.departmentStoreProcedure(1)).thenReturn(departmentList);
        when(modelMapper.map(department, DepartmentResponse.class)).thenReturn(departmentResponse);

        ResponseEntity<List<DepartmentResponse>> response = departmentController.departmentProcedure(1);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<DepartmentResponse> result = response.getBody();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(departmentResponse, result.get(0));
    }

    @Test
    void testDepartmentProcedureCatchBlock() {
       
        when(departmentService.departmentStoreProcedure(1)).thenThrow(new RuntimeException("Error en el servicio"));
        ResponseEntity<List<DepartmentResponse>> response = departmentController.departmentProcedure(1);
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        List<DepartmentResponse> body = response.getBody();
        assertNull(body);
    }

    @Test
    void testdeleteDepartment() {
        doNothing().when(departmentService).deleteDepartmentById(Mockito.any(Department.class));

        when(modelMapper.map(Mockito.any(DepartmentRequest.class), Mockito.eq(Department.class)))
                .thenReturn(new Department());

        Integer departmentId = 1;
        ResponseEntity<String> response = departmentController.deleteDepartment(departmentId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("El departamento se eliminó con éxito.", response.getBody());
        Mockito.verify(departmentService, Mockito.times(1)).deleteDepartmentById(Mockito.any(Department.class));

    }

    @Test
    void testDeleteDepartmentCatchBlock() {
        int departmentId = 123;
        Mockito.doThrow(new RuntimeException("Se presento un error")).when(departmentService)
                .deleteDepartmentById(Mockito.any(Department.class));
        ResponseEntity<String> response = departmentController.deleteDepartment(departmentId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error interno del servidor: Se presento un error", response.getBody());
    }
}
