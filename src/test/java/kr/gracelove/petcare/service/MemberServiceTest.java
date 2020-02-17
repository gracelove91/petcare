package kr.gracelove.petcare.service;

import kr.gracelove.petcare.domain.member.Member;
import kr.gracelove.petcare.domain.member.MemberRoles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void exception_duplication_login_id() {
        Member member1 = Member.builder()
                .loginId("govlmo91")
                .password("1234")
                .build();
        memberService.join(member1);

        Member member2 = Member.builder()
                .loginId("govlmo91")
                .password("1234")
                .build();

        assertThrows(DuplicateLoginId.class, () -> memberService.join(member2));
    }

    @Test
    void member_not_found() {
        assertThrows(MemberNotFoundException.class, () -> memberService.getMember(999L));
    }

    @Test
    void findByUsername() {

        //Given
        String logindId = "grace";
        Member member = Member.builder()
                .loginId(logindId)
                .password("1234")
                .roles(List.of(MemberRoles.MEMBER))
                .build();
        Long joinId = memberService.join(member);

        UserDetailsService userDetailsService = (UserDetailsService) memberService;
        UserDetails userDetails = userDetailsService.loadUserByUsername(logindId);

        //When
        Member findMember = memberService.getMember(joinId);

        //Then
        assertEquals(findMember.getPassword(), userDetails.getPassword());
        System.out.println("findMember = " + findMember);
    }


}