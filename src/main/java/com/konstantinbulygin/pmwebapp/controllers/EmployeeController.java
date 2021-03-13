package com.konstantinbulygin.pmwebapp.controllers;

import com.konstantinbulygin.pmwebapp.dao.EmployeeRepository;
import com.konstantinbulygin.pmwebapp.entities.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/new-employee";
    }

    //saving project to DB
    @PostMapping("/save")
    public String saveEmployee(Employee employee, Model model) {
        employeeRepository.save(employee);
        return "redirect:/employees/new";
    }
}
