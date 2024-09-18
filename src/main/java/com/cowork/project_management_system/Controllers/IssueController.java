package com.cowork.project_management_system.Controllers;

import com.cowork.project_management_system.dtos.IssueDto;
import com.cowork.project_management_system.services.impl.IssueServiceImpl;
import com.cowork.project_management_system.dtos.IssueRequest;
import com.cowork.project_management_system.model.Issue;
import com.cowork.project_management_system.model.User;
import com.cowork.project_management_system.services.impl.UserServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/api/issue")
@RequiredArgsConstructor
public class IssueController {

    private final IssueServiceImpl issueService;
    private final UserServicesImpl userServices;

    @GetMapping("/{issueId}")
    public ResponseEntity<?> getIssueById(@PathVariable Long issueId) {
    try{
        return new ResponseEntity<>(issueService.getIssueById(issueId), OK);
    }catch(Exception e){
        return new ResponseEntity<>(e.getMessage(), OK);

    }
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> getIssueByProjectId(@PathVariable Long projectId){
    try{
        return new ResponseEntity<>(issueService.getIssueByProjectId(projectId), OK);
    }catch(Exception e){
        return new ResponseEntity<>(e.getMessage(), OK);

    }
    }


    @PostMapping
    public ResponseEntity<?> createIssue(@RequestBody IssueRequest issueRequest , @RequestHeader("Authorization") String token){
       try{
        User tokenUser = userServices.findUserProfileByJwt(token);
         Issue issue = issueService.createIssue(issueRequest,tokenUser.getId());
            IssueDto dto = new IssueDto();
            dto.setDescription( issue.getDescription());
            dto.setDueDate( issue.getDueDate());
            dto.setId( issue.getId());
//            dto.setPriority(issue.getPriority());
            dto.setProject(issue.getProject());
            dto.setProject_ID(issue.getProject_ID());
//            dto.setStatus(issue.getStatus());
            dto.setTitle(issue.getTitle());
            dto.setTags(issue.getTags());
            dto.setAssignee(issue.getAssignee());
            return new ResponseEntity<>(dto,OK);
       }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

       }
       }


//    @PutMapping("/{issueId}")
//    public ResponseEntity<?> deleteIssue(@RequestBody IssueRequest updatedIssue,
//      @RequestHeader("Authorization") String token){
//
//    }

 @DeleteMapping("/{issueId}")
 public ResponseEntity<?> deleteIssue(@PathVariable Long issueId,
                                      @RequestHeader("Authorization") String token){
        try{
        User user = userServices.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId, user.getId());
        return ResponseEntity.ok("successfully deleted issue");
 }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

 }
    }

 @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<?> addUserToIssue(@PathVariable Long issueId, @PathVariable Long userId){
           try{
            Issue issue = issueService.addUserToIssue(issueId, userId);
            return ResponseEntity.ok(issue);
           }catch(Exception e){
               return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

           }
           }


// @GetMapping("/assignee/{assigneeId}")
//    public ResponseEntity<?> getIssueByAssigneeId(@PathVariable Long assigneeId){
//     List<?> issues = issueService.ge

 @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<?> updateIssueStatus(
            @PathVariable String status,
            @PathVariable Long issueId
 ){
        try{
        Issue issue = issueService.updateStatus(issueId, status);
        return new ResponseEntity<>(issue,OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

        }
        }

@GetMapping("/get-all-my-issues/{userId}")
   public ResponseEntity<?> getAllUserIssues(@RequestHeader("Authorization") String token,
                                             @PathVariable Long userId){
   try {
       return new ResponseEntity<>(issueService.getAllUserIssues(userId), OK);
   }catch (Exception e){
       return new ResponseEntity<>(e.getMessage(),BAD_REQUEST);
   }

}

}

