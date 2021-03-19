package com.konstantinbulygin.pmwebapp.dao;

import com.konstantinbulygin.pmwebapp.dto.ChartData;
import com.konstantinbulygin.pmwebapp.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    List<Project> findAll();

    @Query(nativeQuery = true,
            value = "SELECT stage AS Label, COUNT(*) AS Value " +
                    "FROM project " +
                    "GROUP BY stage")
    List<ChartData> getProjectStatus();



}
