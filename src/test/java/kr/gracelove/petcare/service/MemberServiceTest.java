package kr.gracelove.petcare.service;

import kr.gracelove.petcare.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}