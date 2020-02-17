package kr.gracelove.petcare.controller;

import kr.gracelove.petcare.controller.dto.MemberJoinRequestDto;
import kr.gracelove.petcare.controller.dto.MemberResponseDto;
import kr.gracelove.petcare.controller.dto.MemberUpdateRequestDto;
import kr.gracelove.petcare.domain.member.Member;
import kr.gracelove.petcare.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public ResponseEntity<?> join(@RequestBody MemberJoinRequestDto dto) throws URISyntaxException {
        Long joinId = memberService.join(dto.toEntity());
        String url = "/members/"+joinId;
        return ResponseEntity.created(new URI(url)).build();
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

    @GetMapping("/api/v1/members")
    public List<MemberResponseDto> getMembers() {
        List<Member> members = memberService.getMembers();
        return members.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

}
