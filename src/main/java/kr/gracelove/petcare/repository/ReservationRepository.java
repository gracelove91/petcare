package kr.gracelove.petcare.repository;

import kr.gracelove.petcare.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
