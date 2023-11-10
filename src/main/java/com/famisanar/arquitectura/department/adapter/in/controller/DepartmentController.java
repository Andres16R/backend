package com.famisanar.arquitectura.department.adapter.in.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.famisanar.arquitectura.department.adapter.out.dto.DepartmentResponse;
import com.famisanar.arquitectura.department.application.port.in.IDepartmentService;
import com.famisanar.arquitectura.department.domain.model.Department;
import com.famisanar.arquitectura.shared.annotations.CustomRestController;
import com.famisanar.arquitectura.shared.annotations.WebAdapter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

@WebAdapter
@CustomRestController
@Tag(name = "Department", description = "API enfocado en brindar las capacidades enfocada en el manejo de los departaments manejado en famisanar ")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/redirect")
    public ResponseEntity<String> redirect(String url) {
        Cookie cookie = new Cookie("mi-cookie", "12345");
        cookie.setPath("/");
        cookie.setMaxAge(60 * 10); // 10 minutos
    
        String headerValue = "Set-Cookie: " + cookie.getName() + "=" + cookie.getValue() + "; ";
    
        if (cookie.getMaxAge() != -1) {
            headerValue += "Max-Age=" + cookie.getMaxAge() + "; ";
        }
    
        if (cookie.isHttpOnly()) {
            headerValue += "HttpOnly; ";
        }
    
        headerValue += "Path=" + cookie.getPath();
    
        HttpHeaders headers = new HttpHeaders();
        headers.set("Set-Cookie", headerValue);
    
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header("Location", url)
                .headers(headers)
                .build();

        // return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header("Location",
        // url).build();
    }

    @GetMapping(value = "/Department")
    @Operation(summary = "Obtener la lista de departamentos")
    public ResponseEntity<List<DepartmentResponse>> department() {
        try {

            List<Department> lstDepartment = departmentService.department();

            return ResponseEntity.status(HttpStatus.OK).body(lstDepartment.stream()
                    .map(department -> modelMapper.map(department, DepartmentResponse.class))
                    .toList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/DepartmentProcedure")
    @Operation(summary = "Obtener departamentos mediante procedimiento almacenado")
    public ResponseEntity<List<DepartmentResponse>> departmentProcedure(
            @Parameter(description = "ID del departamento a buscar", example = "1", required = true) @RequestParam("departmentId") @NotNull Integer departmentId) {
        try {
            List<Department> lstDepartment = departmentService.departmentStoreProcedure(departmentId);
            return ResponseEntity.status(HttpStatus.OK).body(lstDepartment.stream()
                    .map(department -> modelMapper.map(department, DepartmentResponse.class))
                    .toList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping(value = "/Department/{departmentId}")
    @Operation(summary = "Eliminar un departamento")
    public ResponseEntity<String> deleteDepartment(
            @Parameter(description = "ID del departamento a eliminar", required = true) @PathVariable("departmentId") Integer departmentId) {
        try {
            Department department = new Department();
            department.setDepartmentId(departmentId);
            departmentService.deleteDepartmentById(department);
            return ResponseEntity.ok("El departamento se eliminó con éxito.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + ex.getMessage());
        }
    }

}
