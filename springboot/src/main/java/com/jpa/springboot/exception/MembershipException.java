package com.jpa.springboot.exception;

import com.jpa.springboot.enums.MembershipErrorResult;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MembershipException extends RuntimeException {

    private final MembershipErrorResult errorResult;

}
