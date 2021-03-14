package com.konstantinbulygin.pmwebapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konstantinbulygin.pmwebapp.dao.EmployeeRepository;
import com.konstantinbulygin.pmwebapp.dao.ProjectRepository;
import com.konstantinbulygin.pmwebapp.dto.ChartData;
import com.konstantinbulygin.pmwebapp.dto.EmployeeProject;
import com.konstantinbulygin.pmwebapp.entities.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public HomeController(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public String displayHomeView(Model model) throws JsonProcessingException {

        Map<String, Long> map = new HashMap<>();

        //querying the database for projects
        List<Project> projects = projectRepository.findAll();
        //sending the data of projects to home view
        model.addAttribute("projectsList", projects);

        List<ChartData> projectData = projectRepository.getProjectStatus();

        //covert projectData into a json
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(projectData);
        model.addAttribute("projectStatusCnt", json);


        //querying the database for employees
        List<EmployeeProject> employeeProjectsCount = employeeRepository.employeeProjects();
        //sending the data of employees to home view
        model.addAttribute("employeeProjectsCount", employeeProjectsCount);

        return "main/home";
    }
}
