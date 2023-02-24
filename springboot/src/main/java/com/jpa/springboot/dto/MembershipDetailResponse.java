package com.jpa.springboot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

import com.jpa.springboot.enums.MembershipType;

@Getter
@Builder
@RequiredArgsConstructor
public class MembershipDetailResponse {
    private final Long id;
    private final MembershipType membershipType;
    private final LocalDateTime createdAt;
    private final Integer point;
}
