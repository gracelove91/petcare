package kr.gracelove.petcare.controller.dto;

import kr.gracelove.petcare.domain.member.Member;
import kr.gracelove.petcare.domain.common.Address;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class MemberJoinRequestDto {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String password2;
    @NotEmpty
    private String email;

    private Address address;
    @NotEmpty
    private String phoneNumber;

    @Builder
    public MemberJoinRequestDto(String loginId, String password, String password2, String email, Address address, String phoneNumber) {
        this.loginId = loginId;
        this.password = password;
        this.password2 = password2;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


//    public static MemberJoinRequestDto createMemberDto(Member member) {
//        return MemberJoinRequestDto
//                .builder()
//                .loginId(member.getLoginId())
//                .password(member.getPassword())
//                .phoneNumber(member.getPhoneNumber())
//                .address(member.getAddress())
//                .email(member.getEmail())
//                .build();
//    }

    public Member toEntity() {
        return Member.builder()
                .loginId(loginId)
                .address(address)
                .phoneNumber(phoneNumber)
                .email(email)
                .password(password)
                .build();
    }
}
