package kr.gracelove.petcare.repository;

import kr.gracelove.petcare.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
