package kr.gracelove.petcare.controller.dto;

import kr.gracelove.petcare.entity.Member;
import kr.gracelove.petcare.entity.common.Address;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private String loginId;
    private String email;
    private Address address;
    private String phoneNumber;

    public MemberResponseDto(Member member) {
        this.loginId = member.getLoginId();
        this.email = member.getEmail();
        this.address = member.getAddress();
        this.phoneNumber = member.getPhoneNumber();
    }
}
