package com.cowork.project_management_system.repository;

import com.cowork.project_management_system.model.Project;
import com.cowork.project_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Projects extends JpaRepository<Project,Long> {
  List<Project>  findProjectByOwner(User user);

  List<Project> findProjectByNameContainingAndTeamContains(String name , User user);

  @Query("select p from Project p join p.team t where t=:user")
  List<Project> findProjectByTeam(@Param("user") User user);

  List<Project> findProjectByTeamContainingOrOwner(User user, User owner);

}
