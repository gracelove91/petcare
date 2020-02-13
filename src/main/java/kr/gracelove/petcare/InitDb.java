package kr.gracelove.petcare;

import kr.gracelove.petcare.entity.Clinic;
import kr.gracelove.petcare.entity.Member;
import kr.gracelove.petcare.entity.Pet;
import kr.gracelove.petcare.entity.Reservation;
import kr.gracelove.petcare.entity.common.Address;
import kr.gracelove.petcare.repository.ClinicRepository;
import kr.gracelove.petcare.repository.MemberRepository;
import kr.gracelove.petcare.repository.PetRepository;
import kr.gracelove.petcare.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Profile("local")
@RequiredArgsConstructor
@Component
public class InitDb implements ApplicationRunner {

    private final MemberRepository memberRepository;
    private final ClinicRepository clinicRepository;
    private final ReservationRepository reservationRepository;
    private final PetRepository petRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member member1 = Member.builder()
                .address(new Address("경기도 용인시", "처인구", "유방동", "133-2"))
                .email("member1@gmail.com")
                .phoneNumber("010-1111-2222")
                .loginId("member1")
                .password("password1")
                .build();

        memberRepository.save(member1);

        Pet pet1 = Pet.builder()
                .owner(member1)
                .description("귀여운 강아지")
                .name("김강아지").build();

        Pet pet2 = Pet.builder()
                .owner(member1)
                .description("귀여운 고양이")
                .name("김고양이").build();

        petRepository.save(pet1);
        petRepository.save(pet2);

        Member member2 = Member.builder()
                .address(new Address("서울시", "관악구", "봉천동", "111-111"))
                .email("member2@gmail.com")
                .phoneNumber("010-2222-2222")
                .loginId("member2")
                .password("password2")
                .build();

        memberRepository.save(member2);

        Pet pet3 = Pet.builder()
                .owner(member2)
                .description("잘생긴 강아지")
                .name("봉천 강아지").build();

        Pet pet4 = Pet.builder()
                .owner(member2)
                .description("잘생긴 고양이")
                .name("봉천 고양이").build();

        petRepository.save(pet3);
        petRepository.save(pet4);

        Clinic clinic1 = Clinic.builder()
                .address(new Address("서울시", "어딘가", "어딘가동", "123-123"))
                .ceo("김철수")
                .contact("010-3333-3333")
                .description("친절한 동물병원입니다.")
                .name("친절한 동물병원")
                .loginId("clinic1")
                .password("password")
                .openDate(LocalTime.of(9, 0))
                .closeDate(LocalTime.of(22, 0)).build();

        Clinic clinic2 = Clinic.builder()
                .address(new Address("경기도 용인시", "어딘가구", "좋은동", "123-1111"))
                .ceo("김수")
                .contact("010-4444-3333")
                .description("불친절한 동물병원입니다.")
                .name("불친절한 동물병원")
                .loginId("clinic2")
                .password("password")
                .openDate(LocalTime.of(0, 0))
                .closeDate(LocalTime.of(0, 0)).build();

        clinicRepository.save(clinic1);
        clinicRepository.save(clinic2);

        Reservation reservation1 = Reservation.builder()
                .clinic(clinic1)
                .pet(pet1)
                .reservationDate(LocalDateTime.of(2020, 4, 20, 16, 0))
                .build();

        Reservation reservation2 = Reservation.builder()
                .clinic(clinic1)
                .pet(pet2)
                .reservationDate(LocalDateTime.of(2020, 4, 20, 16, 0))
                .build();

        Reservation reservation3 = Reservation.builder()
                .clinic(clinic2)
                .pet(pet4)
                .reservationDate(LocalDateTime.of(2020, 4, 20, 16, 0))
                .build();

        Reservation reservation4 = Reservation.builder()
                .clinic(clinic2)
                .pet(pet4)
                .reservationDate(LocalDateTime.of(2020, 4, 20, 16, 0))
                .build();

        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);
        reservationRepository.save(reservation3);
        reservationRepository.save(reservation4);
    }
}
