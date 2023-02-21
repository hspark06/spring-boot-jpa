package com.jpa.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.springboot.entity.Membership;
import com.jpa.springboot.enums.MembershipType;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Membership findByUserIdAndMembershipType(final String userId, final MembershipType membershipType);
}
