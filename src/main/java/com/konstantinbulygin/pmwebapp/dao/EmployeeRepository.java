package com.konstantinbulygin.pmwebapp.dao;

import com.konstantinbulygin.pmwebapp.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
