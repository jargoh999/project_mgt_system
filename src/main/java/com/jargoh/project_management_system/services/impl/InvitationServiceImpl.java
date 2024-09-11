package com.jargoh.project_management_system.services.impl;

import com.jargoh.project_management_system.model.Invitation;
import com.jargoh.project_management_system.repository.Invitations;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvitationServiceImpl {

private  final Invitations invitations;

private final EmailServicesImpl emailServices;

    public void sendInvitation(String email,Long projectId) throws MessagingException {

        String InvitationToken = UUID.randomUUID().toString();
        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(InvitationToken);
        invitations.save(invitation);
        String invitationLink="http://localhost:5173/accept_invitation?token"+InvitationToken;
        emailServices.sendEmailWithToken(email, invitationLink);
       }

       public Invitation  acceptInvitation(String token, Long userId){
         Invitation invitation = invitations.findInvitationByToken(token);
         if(invitation == null){
             throw new RuntimeException("invalid invitation token");
         }
         return invitation;
       }

      public void deleteToken(String token){
           Invitation invitation = invitations.findInvitationByToken(token);
           invitations.delete(invitation);
       }

       public String getTokenByUserMail(String userEmail){
            Invitation invitation = invitations.findInvitationByEmail(userEmail);
            return invitation.getToken();
       }

}
