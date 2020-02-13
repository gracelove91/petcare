package kr.gracelove.petcare.controller.dto;

import kr.gracelove.petcare.entity.Member;
import kr.gracelove.petcare.entity.common.Address;
import lombok.Getter;

@Getter
public class MemberJoinRequestDto {

    private String loginId;
    private String password;
    private String password2;
    private String email;
    private Address address;
    private String phoneNumber;

    public Member toEntity() {
        return Member.builder()
                .loginId(loginId)
                .address(address)
                .phoneNumber(phoneNumber)
                .email(email)
                .password(password)
                .build();
    }

    public boolean isValidPassword() {
        return this.password.equals(password2);
    }
}
