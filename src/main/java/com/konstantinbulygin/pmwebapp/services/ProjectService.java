package com.konstantinbulygin.pmwebapp.services;

import com.konstantinbulygin.pmwebapp.dao.ProjectRepository;
import com.konstantinbulygin.pmwebapp.dto.ChartData;
import com.konstantinbulygin.pmwebapp.dto.TimeChartData;
import com.konstantinbulygin.pmwebapp.entities.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public List<ChartData> getProjectStatus() {
        return projectRepository.getProjectStatus();
    }

    public List<TimeChartData> getTimeData() {
        return projectRepository.getTimeData();
    }

}
