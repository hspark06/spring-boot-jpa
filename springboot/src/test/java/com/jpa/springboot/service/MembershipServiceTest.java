package com.jpa.springboot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jpa.enums.MembershipErrorResult;
import com.jpa.enums.MembershipType;
import com.jpa.exception.MembershipException;
import com.jpa.springboot.entity.Membership;
import com.jpa.springboot.repository.MembershipRepository;

import static org.mockito.Mockito.doReturn;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {
    
    private final String userId = "userId";
    private final MembershipType membershipType = MembershipType.NAVER;
    private final Integer point = 10000;    

    @InjectMocks
    private MembershipService target;
    @Mock
    private MembershipRepository membershipRepository;

    @Test
    public void 멤버십등록실패_이미존재함() {
        //given
        doReturn(Membership.builder().build()).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);

        //when 
        final MembershipException result = assertThrows(MembershipException.class, ()->target.addMembership(userId, membershipType, point));
        
        // then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
    }

}
