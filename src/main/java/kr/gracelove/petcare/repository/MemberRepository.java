package kr.gracelove.petcare.repository;

import kr.gracelove.petcare.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
