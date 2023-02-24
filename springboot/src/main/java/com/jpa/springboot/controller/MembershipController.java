package com.jpa.springboot.controller;

import com.google.gson.Gson;
import com.jpa.springboot.dto.MembershipConstants;
import com.jpa.springboot.dto.MembershipDetailResponse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.springboot.dto.MembershipRequest;
import com.jpa.springboot.dto.MembershipAddResponse;
import com.jpa.springboot.enums.MembershipErrorResult;
import com.jpa.springboot.exception.MembershipException;
import com.jpa.springboot.service.MembershipService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MembershipController {
    
    private final MembershipService membershipService;

    @PostMapping("/api/v1/memberships")
    public ResponseEntity<MembershipAddResponse> addMembership(
            @RequestHeader(MembershipConstants.USER_ID_HEADER) final String userId,
            @RequestBody @Valid final MembershipRequest membershipRequest) {
           
        //Gson gson = new Gson();
        //System.out.println("json = " + gson.toJson(membershipRequest));
        //if (true) throw new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);       
        final MembershipAddResponse membershipResponse = membershipService.addMembership(userId, membershipRequest.getMembershipType(), membershipRequest.getPoint());
        return ResponseEntity.status(HttpStatus.CREATED).body(membershipResponse);
    }
    
    @GetMapping("/api/v1/memberships")
    public ResponseEntity<List<MembershipDetailResponse>> getMembershipList(
            @RequestHeader(MembershipConstants.USER_ID_HEADER) final String userId) {
        
        return ResponseEntity.ok(membershipService.getMembershipList(userId));
    }
    
    @GetMapping("/api/v1/memberships/{id}")
    public ResponseEntity<MembershipDetailResponse> getMembership(
            @RequestHeader(MembershipConstants.USER_ID_HEADER) final String userId,
            @PathVariable final Long id) {

        return ResponseEntity.ok(membershipService.getMembership(id, userId));
    }
    
    @DeleteMapping("/api/v1/memberships/{id}")
    public ResponseEntity<Void> removeMembership(
        @RequestHeader(MembershipConstants.USER_ID_HEADER) final String userId,
        @PathVariable final Long id) {

        membershipService.removeMembership(id, userId);
        return ResponseEntity.noContent().build();
    }

}
