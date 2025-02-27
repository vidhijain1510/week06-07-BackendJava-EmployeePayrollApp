package com.capgemini.employeepayrollapp.service;

import com.capgemini.employeepayrollapp.dto.EmployeeDTO;
import com.capgemini.employeepayrollapp.model.Employee;
import com.capgemini.employeepayrollapp.repository.EmployeeRepository;
import com.capgemini.employeepayrollapp.validation.EmployeeNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() { return repository.findAll(); }
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setGender(employeeDTO.getGender());
        employee.setStartDate(employeeDTO.getStartDate());
        employee.setNote(employeeDTO.getNote());
        employee.setProfilePic(employeeDTO.getProfilePic());
        employee.setDepartment(employeeDTO.getDepartment());
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

    /*private final List<Employee> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1); // Generates unique IDs

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public Employee getEmployeeById(Long id) {
        return employeeList.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(idCounter.getAndIncrement()); // Assign unique ID
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employeeList.add(employee);
        return employee;
    }

    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee existingEmployee = getEmployeeById(id); // Find existing employee
        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setSalary(employeeDTO.getSalary());
        return existingEmployee;
    }

    public void deleteEmployee(Long id) {
        Employee existingEmployee = getEmployeeById(id);
        employeeList.remove(existingEmployee);
    }

    //As we further down we'll persist this data in database.*/
}