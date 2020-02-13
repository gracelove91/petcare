package kr.gracelove.petcare.controller;

import kr.gracelove.petcare.controller.dto.MemberJoinRequestDto;
import kr.gracelove.petcare.controller.dto.MemberResponseDto;
import kr.gracelove.petcare.controller.dto.MemberUpdateRequestDto;
import kr.gracelove.petcare.entity.Member;
import kr.gracelove.petcare.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public Long join(MemberJoinRequestDto dto) {
        return memberService.join(dto.toEntity());
    }

    @PutMapping("/api/v1/members/{memberId}")
    public Long update(@PathVariable("memberId") Long memberId, MemberUpdateRequestDto dto) {
        return memberService.update(memberId, dto.toEntity());
    }

    @GetMapping("/api/v1/members/{memberId}")
    public MemberResponseDto getMember(@PathVariable("memberId") Long memberId) {
        Member findMember = memberService.getMember(memberId);
        return new MemberResponseDto(findMember);
    }

}
