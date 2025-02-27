package com.capgemini.employeepayrollapp.repository;

import com.capgemini.employeepayrollapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employee_payroll ep " +
            "JOIN employee_department ed ON ep.employee_id = ed.id " +
            "WHERE ed.department = :department", nativeQuery = true)
    List<Employee> findEmployeesByDepartment(String department);
}
