package com.capgemini.employeepayrollapp.service;

import com.capgemini.employeepayrollapp.dto.EmployeeDTO;
import com.capgemini.employeepayrollapp.model.Employee;

import java.util.List;

public interface IEmployeePayrollService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(long id);

    List<Employee> getEmployeesByDepartment(String department);

    Employee saveEmployee(EmployeeDTO employeeDTO);

    Employee updateEmployee(long id, EmployeeDTO employeeDTO);

    void deleteEmployee(long id);
}
