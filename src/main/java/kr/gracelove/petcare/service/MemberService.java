package kr.gracelove.petcare.service;

import kr.gracelove.petcare.domain.member.Member;
import kr.gracelove.petcare.domain.member.MemberRepository;
import kr.gracelove.petcare.domain.member.MemberRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService implements UserDetailsService {
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
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException("존재하지않는 사용자입니다."));
        findMember.updateMember(member);
        return findMember.getId();
    }

    @Transactional(readOnly = true)
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException("존재하지않는 사용자입니다."));
    }

    @Transactional(readOnly = true)
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(username)
                .orElseThrow(() -> new MemberNotFoundException(username));
        return new User(member.getLoginId(), member.getPassword(), authorities(member.getRoles()));
    }

    private Collection<? extends GrantedAuthority> authorities(List<MemberRoles> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toList());
    }
}
