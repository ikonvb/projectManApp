package com.konstantinbulygin.pmwebapp.controllers;

import com.konstantinbulygin.pmwebapp.entities.Employee;
import com.konstantinbulygin.pmwebapp.entities.Project;
import com.konstantinbulygin.pmwebapp.services.EmployeeService;
import com.konstantinbulygin.pmwebapp.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;


    public ProjectController(ProjectService projectService, EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        List<Employee> allEmployees = employeeService.getAll();

        model.addAttribute("project", new Project());
        model.addAttribute("allEmployees", allEmployees);
        return "projects/new-project";
    }

    //saving project to DB
    @PostMapping("/save")
    public String saveProject(Project project, Model model) {

        projectService.save(project);

        return "redirect:/projects";
    }

    @GetMapping
    public String displayProjects(Model model) {

        //querying the database for projects
        List<Project> projects = projectService.getAll();
        //sending the data of projects to home view
        model.addAttribute("projectsList", projects);

        return "projects/list-projects";
    }
}
