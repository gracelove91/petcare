package kr.gracelove.petcare.repository;

import kr.gracelove.petcare.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}
