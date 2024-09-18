package com.cowork.project_management_system.Controllers;

import com.cowork.project_management_system.dtos.LoginRequest;
import com.cowork.project_management_system.services.impl.UserServicesImpl;
import com.cowork.project_management_system.dtos.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Controller
public class UserController {

    @Autowired
    private UserServicesImpl userServices;
    @PostMapping("auth/sign_up")
    public ResponseEntity<?> signUp(@RequestBody UserRequest userRequest){
        try{
             return new ResponseEntity<>(  userServices.createUser(userRequest), CREATED  );
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(),BAD_REQUEST);
        }
    }


    @PostMapping("auth/sign_in")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest userRequest){
        try{
            return new ResponseEntity<>(  userServices.signIn(userRequest), OK  );
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(),BAD_REQUEST);
        }
    }

    @GetMapping("/api/profile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String jwt){
        return new ResponseEntity<>(userServices.findUserProfileByJwt(jwt), OK);
    }

}
