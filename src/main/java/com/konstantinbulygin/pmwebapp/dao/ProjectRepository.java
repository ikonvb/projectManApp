package com.konstantinbulygin.pmwebapp.dao;

import com.konstantinbulygin.pmwebapp.entities.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {

}
