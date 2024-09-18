package com.cowork.project_management_system.Controllers;

import com.cowork.project_management_system.dtos.InviteRequest;
import com.cowork.project_management_system.model.Chat;
import com.cowork.project_management_system.model.Invitation;
import com.cowork.project_management_system.model.Project;
import com.cowork.project_management_system.model.User;
import com.cowork.project_management_system.services.impl.InvitationServiceImpl;
import com.cowork.project_management_system.services.impl.ProjectService;
import com.cowork.project_management_system.services.impl.UserServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final UserServicesImpl userServices;

    private final InvitationServiceImpl invitationService;

    @GetMapping
    public ResponseEntity<?> getProjects(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            @RequestHeader("Authorization") String jwt
    ){
        try{
        User user =  userServices.findUserProfileByJwt(jwt);
        List<Project>  projects = projectService.getProjectByTeam(user, category, tag);
        return new ResponseEntity<>(projects, OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

        }
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ){
        try{
        User user =  userServices.findUserProfileByJwt(jwt);
        Project project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

        }
    }

    @PostMapping
    public ResponseEntity<?> createProject(
            @RequestBody Project project,
            @RequestHeader("Authorization") String jwt
    ){
        try{
        User user =  userServices.findUserProfileByJwt(jwt);
        Project createdProject = projectService.createProject(project,user);
        return new ResponseEntity<>(createdProject, OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

        }
    }


    @PatchMapping("/{projectId}")
    public ResponseEntity<?> updateProject(
            @PathVariable Long projectId,
            @RequestBody Project project,
            @RequestHeader("Authorization") String jwt
    ){
        try{
        User user =  userServices.findUserProfileByJwt(jwt);
        Project createdProject = projectService.updateProject(project,projectId);
        return new ResponseEntity<>(createdProject, OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

        }
        }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ){
       try{
        User user =  userServices.findUserProfileByJwt(jwt);
       var response= projectService.deleteProject(projectId,user.getId());
        return new ResponseEntity<>(response, OK);
       }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

       }
       }


    @GetMapping("/search")
    public ResponseEntity<?> searchProject(
        @RequestParam(required = false) String keyWord,
        @RequestHeader("Authorization") String jwt
    ){
       try{
        User user = userServices.findUserProfileByJwt(jwt);
        List<Project> projects=projectService.searchProject(keyWord,user);
        return new ResponseEntity<>(projects, OK);
       }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

       }
    }

    @GetMapping("/{projectId}/chat")
    public ResponseEntity<?> getChatByProjectId(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ){
       try{
        User user = userServices.findUserProfileByJwt(jwt);
        Chat chat = projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, OK);
       }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

       }
       }

    @PostMapping("/invite")
    public ResponseEntity<?> inviteToProject(
           @RequestHeader("Authorization") String jwt,
           @RequestBody InviteRequest inviteRequest
    )  {
       try{
        User user = userServices.findUserProfileByJwt(jwt);
        invitationService.sendInvitation(inviteRequest.getEmail(), inviteRequest.getProjectId());
        return new ResponseEntity<>("Invite sent successfully", OK);
       }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

       }
    }


    @PostMapping("/accept-invite")
    public ResponseEntity<?> acceptInvite(
            @RequestParam String token,
            @RequestHeader("Authorization") String jwt
    )  {
       try{
        User user = userServices.findUserProfileByJwt(jwt);
        Invitation invitation=invitationService.acceptInvitation(token, user.getId());
        projectService.addUserToProject(invitation.getProjectId(), user.getId());
        return new ResponseEntity<>(invitation, ACCEPTED);
       }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
       }
    }

}

