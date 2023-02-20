package com.jpa.springboot.controller;

import com.jpa.springboot.dto.MembershipConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.springboot.dto.MembershipRequest;
import com.jpa.springboot.dto.MembershipResponse;
import com.jpa.springboot.service.MembershipService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MembershipController {
    
    private final MembershipService membershipService;

    @PostMapping("/api/v1/memberships")
    public ResponseEntity<MembershipResponse> addMembership(
            @RequestHeader(MembershipConstants.USER_ID_HEADER) final String userId,
            @RequestBody @Valid final MembershipRequest membershipRequest) {

                membershipService.addMembership(userId, membershipRequest.getMembershipType(), membershipRequest.getPoint());         

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
