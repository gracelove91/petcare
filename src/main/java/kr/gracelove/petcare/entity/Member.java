package kr.gracelove.petcare.entity;

import kr.gracelove.petcare.entity.common.Address;
import kr.gracelove.petcare.entity.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    private String email;

    @Embedded
    private Address address;

    private String phoneNumber;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();

    @Builder
    public Member(Address address, String loginId, String password, String email, String phoneNumber) {
        this.address = address;
        this.password = password;
        this.loginId = loginId;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void updateMember(Member member) {
        this.email = member.getEmail();
        this.address = member.getAddress();
        this.phoneNumber = member.phoneNumber;
    }
}
