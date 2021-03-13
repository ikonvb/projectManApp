package com.konstantinbulygin.pmwebapp.dao;

import com.konstantinbulygin.pmwebapp.entities.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    List<Project> findAll();

}
