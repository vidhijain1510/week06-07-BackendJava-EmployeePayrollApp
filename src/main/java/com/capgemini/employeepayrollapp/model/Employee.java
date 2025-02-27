package com.capgemini.employeepayrollapp.model;

import com.capgemini.employeepayrollapp.dto.EmployeeDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee_payroll")
public @Data class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "name")
    private String name;
    private double salary;
    private String gender;
    private String note;
    private String profilePic;
    private LocalDate startDate;

    @ElementCollection
    @CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "department")
    private List<String> department;

    public Employee() {}

    public Employee(EmployeeDTO employeeDTO) {
        this.name = employeeDTO.getName();
        this.gender = employeeDTO.getGender();
        this.salary = employeeDTO.getSalary();
        this.note = employeeDTO.getNote();
        this.startDate = employeeDTO.getStartDate();
        this.profilePic = employeeDTO.getProfilePic();
        this.department = employeeDTO.getDepartment();

    }

    /*// Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }*/
}