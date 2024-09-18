package com.cowork.project_management_system.services.impl;

import com.cowork.project_management_system.config.JwtProvider;
import com.cowork.project_management_system.repository.Users;
import com.cowork.project_management_system.dtos.LogInResponse;
import com.cowork.project_management_system.dtos.LoginRequest;
import com.cowork.project_management_system.dtos.UserRequest;
import com.cowork.project_management_system.dtos.UserResponse;
import com.cowork.project_management_system.model.User;
import com.cowork.project_management_system.services.CustomUserDetailImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServicesImpl {
    private final Users users;
    private final PasswordEncoder  passwordEncoder;
    private final CustomUserDetailImpl customUserDetail;
    private final SubscriptionService subscriptionService;

    public UserResponse createUser (UserRequest userRequest){
        if(users.findUserByEmailEqualsIgnoreCase(userRequest.getEmail())!=null)throw new RuntimeException("Email already exists");
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setFullName(userRequest.getFullName());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user =users.save(user);
        subscriptionService.createSubscription(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtProvider.generateToken(authentication);
        return new UserResponse(user.getId(), user.getFullName(),user.getEmail(),jwt);
    }


    public LogInResponse signIn (LoginRequest loginRequest){
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtProvider.generateToken(authentication);
        return new LogInResponse(loginRequest.getEmail(),jwt);
    }

    private Authentication authenticate(String username, String password){

        UserDetails userDetails = customUserDetail.loadUserByUsername(username);
        if(userDetails == null){
            throw new RuntimeException("invalid credentials");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new RuntimeException("invalid credentials");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
    }


    public User findUserProfileByJwt(String jwt){
       String email = JwtProvider.getEmailFromToken(jwt);
       return findUserByEmail(email);
    }

    public User findUserByUserId(Long userId){
        return users.findById(userId).orElseThrow(()-> new RuntimeException("user does not exist"));
    }

    public User updateUsersProjectSize(User user , int number){
        user.setProjectSize(user.getProjectSize()+number);
        return users.save(user);
    }

    public User findUserByEmail(String  email){
        User user = users.findUserByEmailEqualsIgnoreCase(email);
        if(user==null)throw new RuntimeException("user not found");
        return user;
    }


}
