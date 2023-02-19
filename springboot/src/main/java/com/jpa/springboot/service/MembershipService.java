package com.jpa.springboot.service;

import org.springframework.stereotype.Service;

import com.jpa.enums.MembershipErrorResult;
import com.jpa.enums.MembershipType;
import com.jpa.exception.MembershipException;
import com.jpa.springboot.entity.Membership;
import com.jpa.springboot.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;
    
    public Membership addMembership(final String userId, final MembershipType membershipType, final Integer point) {
        final Membership result = membershipRepository.findByUserIdAndMembershipType(userId, membershipType);
        if (result != null) {
            throw new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
        }
        return null;
    }
}
