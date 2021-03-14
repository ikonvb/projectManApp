package com.konstantinbulygin.pmwebapp.controllers;

import com.konstantinbulygin.pmwebapp.dao.EmployeeRepository;
import com.konstantinbulygin.pmwebapp.dao.ProjectRepository;
import com.konstantinbulygin.pmwebapp.entities.Employee;
import com.konstantinbulygin.pmwebapp.entities.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectController(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        List<Employee> allEmployees = employeeRepository.findAll();

        model.addAttribute("project", new Project());
        model.addAttribute("allEmployees", allEmployees);
        return "projects/new-project";
    }

    //saving project to DB
    @PostMapping("/save")
    public String saveProject(Project project, @RequestParam List<Long> employees, Model model) {

        projectRepository.save(project);

        Iterable<Employee> chosenEmployees = employeeRepository.findAllById(employees);

        for(Employee emp: chosenEmployees) {
            emp.setProject(project);
            employeeRepository.save(emp);
        }

        return "redirect:/projects/new";
    }

    @GetMapping
    public String displayProjects(Model model) {

        //querying the database for projects
        List<Project> projects = projectRepository.findAll();
        //sending the data of projects to home view
        model.addAttribute("projectsList", projects);

        return "projects/list-projects";
    }
}
