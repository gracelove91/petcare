package kr.gracelove.petcare.domain.pet;

import kr.gracelove.petcare.domain.common.BaseTimeEntity;
import kr.gracelove.petcare.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Pet extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member owner;

    private String name;

    private String description;

    @Builder
    public Pet(Member owner, String name, String description) {
        this.owner = owner;
        this.name = name;
        this.description = description;
    }
}
