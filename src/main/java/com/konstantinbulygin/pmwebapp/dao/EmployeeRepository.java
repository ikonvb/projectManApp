package com.konstantinbulygin.pmwebapp.dao;

import com.konstantinbulygin.pmwebapp.dto.EmployeeProject;
import com.konstantinbulygin.pmwebapp.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Override
    List<Employee> findAll();

    @Query(nativeQuery = true,
            value = "SELECT e.first_name AS firstName, e.last_name AS lastName, e.email , COUNT(pe.employee_id) AS projectCount " +
                    "FROM employee e LEFT JOIN project_employee pe ON pe.employee_id = e.employee_id " +
                    "GROUP BY e.first_name, e.last_name, e.email " +
                    "ORDER BY 4 DESC")
    List<EmployeeProject> employeeProjects();
}
