package com.capgemini.employeepayrollapp.service;

import com.capgemini.employeepayrollapp.dto.EmployeeDTO;
import com.capgemini.employeepayrollapp.model.Employee;
import com.capgemini.employeepayrollapp.repository.EmployeeRepository;
import com.capgemini.employeepayrollapp.validation.EmployeeNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() { return repository.findAll(); }
    public Employee getEmployeeById(Long id) { return repository.findById(id).orElse(null); }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSalary());
        return repository.save(employee);
    }
    public void deleteEmployee(Long id) {
        if (!repository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
        repository.deleteById(id); }


    public Employee updateEmployee(Long id, EmployeeDTO updatedEmployee) {
        Employee existingEmployee = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID " + id + " not found"));

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setSalary(updatedEmployee.getSalary());

        return repository.save(existingEmployee);
    }

    /*public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = repository.findById(id).orElse(null);
        if (employee != null) {
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            return repository.save(employee);
        }
        return null;
    }*/
}