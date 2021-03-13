package com.konstantinbulygin.pmwebapp.controllers;

import com.konstantinbulygin.pmwebapp.dao.EmployeeRepository;
import com.konstantinbulygin.pmwebapp.dao.ProjectRepository;
import com.konstantinbulygin.pmwebapp.entities.Employee;
import com.konstantinbulygin.pmwebapp.entities.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public HomeController(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public String displayHomeView(Model model) {

        //querying the database for projects
        List<Project> projects = projectRepository.findAll();

        //querying the database for employees
        List<Employee> employees = employeeRepository.findAll();

        //sending the data of projects to home view
        model.addAttribute("projectsList", projects);

        //sending the data of employees to home view
        model.addAttribute("employeesList", employees);
        return "home";
    }
}
