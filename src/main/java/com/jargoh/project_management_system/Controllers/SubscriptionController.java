package com.jargoh.project_management_system.Controllers;

import com.jargoh.project_management_system.model.PlanType;
import com.jargoh.project_management_system.model.Subscription;
import com.jargoh.project_management_system.model.User;
import com.jargoh.project_management_system.services.impl.SubscriptionService;
import com.jargoh.project_management_system.services.impl.UserServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final UserServicesImpl userServices;
    @GetMapping("/user")
    public ResponseEntity<?> getUserSub(
            @RequestHeader("Authorization") String jwt
            ){
        try{
        User user = userServices.findUserProfileByJwt(jwt);
        Subscription subscription = subscriptionService.getUsersSubscription(user.getId());
        return new ResponseEntity<>(subscription,OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        }
@PatchMapping("/upgrade")
    public ResponseEntity<?> upgradeUserSub(
        @RequestHeader("Authorization") String jwt,
        @RequestParam PlanType planType
        ){
        try{
        User user = userServices.findUserProfileByJwt(jwt);
        Subscription subscription = subscriptionService.upgradeSubscription(user.getId(),planType);
        return new ResponseEntity<>(subscription,OK);
}catch (Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }

}
