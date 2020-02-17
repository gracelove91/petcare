package kr.gracelove.petcare.service;

import kr.gracelove.petcare.domain.member.Member;
import kr.gracelove.petcare.domain.pet.Pet;
import kr.gracelove.petcare.domain.pet.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PetService {

    private final PetRepository petRepository;
    private final MemberService memberService;

    public Long register(Pet pet, Long memberId) {
        Member findMember = memberService.getMember(memberId);
        findMember.addPet(pet);
        petRepository.save(pet);
        return pet.getId();
    }

    public void remove(Long petId, Long memberId) {
        Member findMember = memberService.getMember(memberId);
        Pet findPet = petRepository.findById(petId).orElseThrow(() -> new IllegalStateException("존재하지 않는 반려동물입니다."));
        findMember.removePet(findPet);
        petRepository.delete(findPet);
    }
}
