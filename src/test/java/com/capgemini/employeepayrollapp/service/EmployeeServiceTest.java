package com.capgemini.employeepayrollapp.service;

import com.capgemini.employeepayrollapp.dto.EmployeeDTO;
import com.capgemini.employeepayrollapp.model.Employee;
import com.capgemini.employeepayrollapp.validation.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();  // Fresh instance before each test
    }

    @Test
    void testCreateEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO("John Doe", 50000);
        Employee createdEmployee = employeeService.saveEmployee(employeeDTO);

        assertNotNull(createdEmployee.getId());
        assertEquals("John Doe", createdEmployee.getName());
        assertEquals(50000, createdEmployee.getSalary());
    }

    @Test
    void testGetAllEmployees() {
        employeeService.saveEmployee(new EmployeeDTO("Alice", 60000));
        employeeService.saveEmployee(new EmployeeDTO("Bob", 70000));

        List<Employee> employees = employeeService.getAllEmployees();

        assertEquals(2, employees.size());
    }

    @Test
    void testGetEmployeeById_Success() {
        Employee createdEmployee = employeeService.saveEmployee(new EmployeeDTO("Charlie", 80000));

        Employee foundEmployee = employeeService.getEmployeeById(createdEmployee.getId());

        assertNotNull(foundEmployee);
        assertEquals("Charlie", foundEmployee.getName());
    }

    @Test
    void testGetEmployeeById_NotFound() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(100L));
    }

    @Test
    void testUpdateEmployee() {
        Employee createdEmployee = employeeService.saveEmployee(new EmployeeDTO("David", 90000));

        EmployeeDTO updatedDTO = new EmployeeDTO("David Updated", 95000);
        Employee updatedEmployee = employeeService.updateEmployee(createdEmployee.getId(), updatedDTO);

        assertEquals("David Updated", updatedEmployee.getName());
        assertEquals(95000, updatedEmployee.getSalary());
    }

    @Test
    void testDeleteEmployee_Success() {
        Employee createdEmployee = employeeService.saveEmployee(new EmployeeDTO("Eve", 100000));

        employeeService.deleteEmployee(createdEmployee.getId());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(createdEmployee.getId()));
    }

    @Test
    void testDeleteEmployee_NotFound() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(999L));
    }

}