package com.cowork.project_management_system.services.impl;

import com.cowork.project_management_system.repository.Projects;
import com.cowork.project_management_system.model.Chat;
import com.cowork.project_management_system.model.Project;
import com.cowork.project_management_system.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final Projects projects;
    private final UserServicesImpl userServices;
    private final ChatService chatService;
    public Project createProject(Project project , User user) {
    Project createdProject = new Project();
    createdProject.setOwner(user);
    createdProject.setTags(project.getTags());
    createdProject.setName(project.getName());
    createdProject.setCategory(project.getCategory());
    createdProject.setDescription(project.getDescription());
    createdProject.getTeam().add(user);
    createdProject=projects.save(createdProject);
    Chat chat = new Chat();
    chat.setProject(createdProject);
    chat = chatService.createChat(chat);
    createdProject.setChat(chat);
    return createdProject;
    }

    public Project getProjectById(Long projectId){

        return projects.findById(projectId)
                .orElseThrow(()-> new RuntimeException("project not found"));
    }

    public List<Project> getProjectByTeam(User user , String category , String tag){

        List<Project> projectList = projects.findProjectByTeamContainingOrOwner(user, user);
        if(category!=null){
           projectList = projectList.stream().filter(project->project
                    .getCategory().equals(category)).collect(Collectors.toList());
        }
        if(tag!=null){
            projectList = projectList.stream().filter(project->project
                    .getCategory().equals(tag)).collect(Collectors.toList());
        }
        return projectList;
    }

    public String deleteProject(Long projectId , Long userId){
        getChatByProjectId(projectId);
        projects.deleteById(projectId);
        return "successfully deleted";
    }

    public Project updateProject(Project updatedProject , Long id){

        Project project = getProjectById(id);
        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());
        project.setTags(updatedProject.getTags());
        return projects.save(project);
    }

    public String addUserToProject(Long projectId , Long userId ){
        Project project = getProjectById(projectId);
        User user = userServices.findUserByUserId(userId);
        if(!project.getTeam().contains(user)){
            project.getChat().getUsers().add(user);
            project.getTeam().add(user);
        }
        projects.save(project);
        return "user successfully added to project";
    }

    public String removeUserFromProject(Long projectId , Long userId ){

        Project project = getProjectById(projectId);
        User user = userServices.findUserByUserId(userId);
        if(!project.getTeam().contains(user)){
            project.getChat().getUsers().remove(user);
            project.getTeam().remove(user);
        }
        projects.save(project);
        return "user successfully removed from project";
    }
    public Chat getChatByProjectId(Long projectId){
         Project project = getProjectById(projectId);
         return project.getChat();
    }


    public List<Project> searchProject(String keyword, User user){
        return projects.findProjectByNameContainingAndTeamContains(keyword, user);
    }



}
