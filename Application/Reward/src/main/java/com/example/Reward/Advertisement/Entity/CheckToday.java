package com.example.Reward.Advertisement.Entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Entity
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckToday {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String enterpriseName;

    private Long memberId;




}
