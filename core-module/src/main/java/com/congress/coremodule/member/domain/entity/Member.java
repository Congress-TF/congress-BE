package com.congress.coremodule.member.domain.entity;

import com.congress.coremodule.member.application.dto.MemberSignIn;
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
    private String userId;
    private Boolean isDeleted;

    @Builder
    public Member(String nickname, String gender, String year, String userId, Boolean isDeleted) {
        this.nickname = nickname;
        this.gender = gender;
        this.year = year;
        this.userId = userId;
        this.isDeleted = isDeleted;
    }

    public void deleteMember() {
        this.isDeleted = true;
    }

    public void setMember(MemberSignIn memberSignIn) {
        this.nickname = memberSignIn.getNickname();
        this.gender = memberSignIn.getGender();
        this.year = memberSignIn.getYear();
    }

}
