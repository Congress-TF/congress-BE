package com.congress.coremodule.member.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickname;
    private String gender;
    private String year;
    private String email;
    private String intend;
    private Boolean isDeleted;

    @Builder
    public Member(String nickname, String gender, String year, String email, String intend, Boolean isDeleted) {
        this.nickname = nickname;
        this.gender = gender;
        this.year = year;
        this.email = email;
        this.intend = intend;
        this.isDeleted = isDeleted;
    }

    public void deleteMember() {
        this.isDeleted = true;
    }

}
