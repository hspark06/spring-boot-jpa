package com.jpa.springboot.service;

import org.springframework.stereotype.Service;

import com.jpa.springboot.dto.MembershipResponse;
import com.jpa.springboot.entity.Membership;
import com.jpa.springboot.enums.MembershipErrorResult;
import com.jpa.springboot.enums.MembershipType;
import com.jpa.springboot.exception.MembershipException;
import com.jpa.springboot.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;
    
    public MembershipResponse addMembership(final String userId, final MembershipType membershipType, final Integer point) {
        final Membership result = membershipRepository.findByUserIdAndMembershipType(userId, membershipType);
        if (result != null) {
            throw new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
        }
        
        final Membership membership = Membership.builder()
                .userId(userId)
                .point(point)
                .membershipType(membershipType)
                .build();
        
        final Membership savedMembership = membershipRepository.save(membership);

        return MembershipResponse.builder()
                .id(savedMembership.getId())
                .membershipType(savedMembership.getMembershipType())
                .build();
    }
}
