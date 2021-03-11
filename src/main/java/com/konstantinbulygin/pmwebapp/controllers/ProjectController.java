package com.konstantinbulygin.pmwebapp.controllers;

import com.konstantinbulygin.pmwebapp.entities.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "new-project";
    }

    //saving project to DB
    @PostMapping("/save")
    public String saveProject(Project project, Model model) {

        return "";
    }
}
