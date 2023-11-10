package com.famisanar.arquitectura.department.adapter.out.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;

@Setter
public class DepartmentResponse {
    @Schema(description = "Id del departamento", example = "1")
    public Integer departmentId;
    @Schema(description = "Nombre del departamento", example = "Cundinamarca")
    public String departmentName;
    @Schema(description = "Direccion", example = "Calle 50 # 10-10")
    public String departmentAddress;
    @Schema(description = "Codigo del departamento", example = "CUN")
    public String departmentCode;
}
