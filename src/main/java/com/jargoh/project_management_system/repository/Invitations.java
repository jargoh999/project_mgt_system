package com.jargoh.project_management_system.repository;

import com.jargoh.project_management_system.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Invitations extends JpaRepository<Invitation, Long> {

    Invitation findInvitationByToken(String token);

    Invitation findInvitationByEmail(String email);

}
