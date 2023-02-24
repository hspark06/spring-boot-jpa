package com.jpa.springboot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jpa.springboot.entity.Membership;
import com.jpa.springboot.enums.MembershipErrorResult;
import com.jpa.springboot.exception.MembershipException;
import com.jpa.springboot.repository.MembershipRepository;

import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RatePointServiceTest {

    @InjectMocks
    private MembershipService target;

    @Mock
    private RatePointService ratePointService;

    @Mock
    private MembershipRepository membershipRepository;

    private final Long membershipId = -1L;
    private final String userId = "";

    @Test
    public void 멤버십적립실패_존재하지않음() {
        // given
        doReturn(Optional.empty()).when(membershipRepository).findById(membershipId);

        // when
        final MembershipException result = assertThrows(MembershipException.class, () -> target.accumulateMembershipPoint(membershipId, userId, 10000));

        // then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND);
    }

// @Test
// public void 멤버십적립실패_본인이아님() {
//     // given
//     final Membership membership = membership();
//     doReturn(Optional.of(membership)).when(membershipRepository).findById(membershipId);

//     // when
//     final MembershipException result = assertThrows(MembershipException.class, () -> target.accumulateMembershipPoint(membershipId, "notowner", 10000));

//     // then
//     assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.NOT_MEMBERSHIP_OWNER);
// }

// @Test
// public void 멤버십적립성공() {
//     // given
//     final Membership membership = membership();
//     doReturn(Optional.of(membership)).when(membershipRepository).findById(membershipId);

//     // when
//     target.accumulateMembershipPoint(membershipId, userId, 10000);

//     // then
    
// }


}
