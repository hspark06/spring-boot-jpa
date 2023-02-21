package com.jpa.springboot.dto;

import com.jpa.springboot.enums.MembershipType;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class MembershipResponse {
    private final Long id;
    private final MembershipType membershipType;
}
