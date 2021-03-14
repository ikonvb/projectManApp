package com.konstantinbulygin.pmwebapp.dao;

import com.konstantinbulygin.pmwebapp.dto.ChartData;
import com.konstantinbulygin.pmwebapp.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    List<Project> findAll();

    @Query(nativeQuery = true,
            value = "SELECT stage AS Label, COUNT(*) AS Value " +
                    "FROM project " +
                    "GROUP BY stage")
    List<ChartData> getProjectStatus();

}
