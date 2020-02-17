package kr.gracelove.petcare.controller.dto;

import lombok.Getter;

@Getter
public class PetFormDto {

    private String name;
    private String description;

    public PetFormDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
