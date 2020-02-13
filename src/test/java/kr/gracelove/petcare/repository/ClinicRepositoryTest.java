package kr.gracelove.petcare.repository;

import kr.gracelove.petcare.entity.Clinic;
import kr.gracelove.petcare.entity.common.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ClinicRepositoryTest {

    @Autowired
    ClinicRepository clinicRepository;

    @Test
    void clinic_만든다() throws Exception {
        //given
        Clinic clinic = Clinic.builder()
                .ceo("홍은모")
                .name("친절한 동물병원")
                .description("친절한 동물병원입니다.")
                .address(new Address("서울시", "무슨 구", "무슨 동", "무슨 디테일"))
                .openDate(LocalTime.of(7, 0))
                .closeDate(LocalTime.of(22, 0))
                .contact("02-111-1111").build();

        //when
        clinicRepository.save(clinic);

        //then
        Clinic findClinic = clinicRepository.findById(clinic.getId()).get();
        assertEquals(findClinic, clinic);
    }
}