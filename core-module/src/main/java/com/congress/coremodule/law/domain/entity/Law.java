package com.congress.coremodule.law.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Law {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "law_id")
    private Long id;

    private String name;

}
