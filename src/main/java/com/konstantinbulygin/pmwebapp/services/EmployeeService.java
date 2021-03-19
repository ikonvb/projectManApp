package com.konstantinbulygin.pmwebapp.services;

import com.konstantinbulygin.pmwebapp.dao.EmployeeRepository;
import com.konstantinbulygin.pmwebapp.dto.EmployeeProject;
import com.konstantinbulygin.pmwebapp.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public List<EmployeeProject> getEmployeeProjects() {
        return employeeRepository.employeeProjects();
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findByEmployeeId(id);
    }


    public void delete(long id) {
        employeeRepository.deleteById(id);
    }
}
