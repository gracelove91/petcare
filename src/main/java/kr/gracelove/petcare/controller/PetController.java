package kr.gracelove.petcare.controller;

import kr.gracelove.petcare.controller.dto.PetFormDto;
import kr.gracelove.petcare.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PetController {

    private final PetService petService;

    @PostMapping("/apt/v1/pets")
    public Long registerPet(@RequestBody PetFormDto dto) {
        return null;
    }
}
