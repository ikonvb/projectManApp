package com.konstantinbulygin.pmwebapp.controllers;

import com.konstantinbulygin.pmwebapp.entities.Employee;
import com.konstantinbulygin.pmwebapp.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/new-employee";
    }

    //saving project to DB
    @PostMapping("/save")
    public String saveEmployee(Employee employee, Model model) {
        employeeService.save(employee);
        return "redirect:/employees/new";
    }

    @GetMapping
    public String displayEmployees(Model model) {

        //querying the database for employees
        List<Employee> employees = employeeService.getAll();

        //sending the data of employees to home view
        model.addAttribute("employeesList", employees);
        return "employees/list-employees";
    }
}