package com.konstantinbulygin.pmwebapp.dao;

;
import com.konstantinbulygin.pmwebapp.entities.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProjectRepoIntegrationTest {

    @Autowired
    ProjectRepository projectRepository;

    @Test
    public void ifNewProjectSavedThenSuccess() {
        Project project = new Project("New Test Project", "COMPLETE", "Test description");
        projectRepository.save(project);
        assertEquals(1, projectRepository.findAll().size());
    }
}
