package com.famisanar.arquitectura.department.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DEPARTMENT")
public class DepartmentEntity {
     @Id
     @Column(name = "DEPARTMENTID")
     @GeneratedValue(strategy = GenerationType.IDENTITY) 
     public Integer departmentId;
     @Column(name = "DEPARTMENTNAME", length = 200)
     public String departmentName;
     @Column(name = "DEPARTMENTADDRESS", length = 200)
     public String departmentAddress;
     @Column(name = "DEPARTMENTCODE", length = 200)
     public String departmentCode;
}