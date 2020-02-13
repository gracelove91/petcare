package kr.gracelove.petcare.entity.common;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String gu;
    private String dong;
    private String detailAddress;

    protected Address() {
    }

    public Address(String city, String gu, String dong, String detailAddress) {
        this.city = city;
        this.gu = gu;
        this.dong = dong;
        this.detailAddress = detailAddress;
    }
}
