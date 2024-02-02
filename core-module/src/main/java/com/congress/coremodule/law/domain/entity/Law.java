package com.congress.coremodule.law.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Law {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "law_id")
    private Long id;

    private String name;

}
