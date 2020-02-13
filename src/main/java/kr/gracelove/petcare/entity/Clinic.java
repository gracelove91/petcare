package kr.gracelove.petcare.entity;

import kr.gracelove.petcare.entity.common.Address;
import kr.gracelove.petcare.entity.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Clinic extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clinic_id")
    private Long id;

    private String loginId;

    private String password;

    @Embedded
    private Address address;

    private String name;

    private String ceo;

    private String contact;

    private LocalTime openDate;

    private LocalTime closeDate;

    private String description;

    @OneToMany(mappedBy = "clinic")
    private List<Reservation> reservations = new ArrayList<>();

    @Builder
    public Clinic(String loginId, String password, Address address, String name, String ceo, String contact, LocalTime openDate, LocalTime closeDate, String description) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.ceo = ceo;
        this.contact = contact;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.description = description;
    }

}
