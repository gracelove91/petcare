package kr.gracelove.petcare.controller.dto;

import kr.gracelove.petcare.entity.Member;
import kr.gracelove.petcare.entity.common.Address;
import lombok.Getter;

@Getter
public class MemberUpdateRequestDto {

    private String email;
    private Address address;
    private String phoneNumber;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();
    }
}
