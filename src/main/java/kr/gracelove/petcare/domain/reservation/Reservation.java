package kr.gracelove.petcare.domain.reservation;

import kr.gracelove.petcare.domain.clinic.Clinic;
import kr.gracelove.petcare.domain.common.BaseEntity;
import kr.gracelove.petcare.domain.pet.Pet;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Reservation extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    private LocalDateTime reservationDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Builder
    public Reservation(Pet pet, Clinic clinic, LocalDateTime reservationDate) {
        this.pet = pet;
        this.clinic = clinic;
        this.reservationDate = reservationDate;
        this.reservationStatus = ReservationStatus.READY;
    }

    public void cancel() {
        if(reservationStatus != ReservationStatus.READY) {
            throw new IllegalStateException("예약완료인 상황에서만 취소를 할 수 있습니다.");
        }
        this.reservationStatus = ReservationStatus.CANCEL;
    }

    public void changeDate(LocalDateTime date) {
        if(reservationStatus != ReservationStatus.READY) {
            throw new IllegalStateException("예약완료인 상황에서만 예약날짜를 변경할 수 있습니다.");
        }
        this.reservationDate = date;
    }
}
