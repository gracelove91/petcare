package kr.gracelove.petcare.service;

import kr.gracelove.petcare.domain.member.Member;
import kr.gracelove.petcare.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long join(Member member) {
        Optional<Member> savedMember = memberRepository.findByLoginId(member.getLoginId());
        if(savedMember.isPresent()) {
            throw new DuplicateLoginId(member.getLoginId());
        }
        member.passwordEncode(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
        return member.getId();
    }

    public Long update(Long memberId, Member member) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new IllegalStateException("존재하지않는 사용자입니다."));
        findMember.updateMember(member);
        return findMember.getId();
    }

    @Transactional(readOnly = true)
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new IllegalStateException("존재하지않는 사용자입니다."));
    }

    @Transactional(readOnly = true)
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }
}
