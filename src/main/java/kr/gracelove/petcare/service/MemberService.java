package kr.gracelove.petcare.service;

import kr.gracelove.petcare.entity.Member;
import kr.gracelove.petcare.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public Long update(Long memberId, Member member) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new IllegalStateException("존재하지않는 사용자입니다."));
        findMember.updateMember(member);
        return findMember.getId();
    }

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new IllegalStateException("존재하지않는 사용자입니다."));
    }
}
