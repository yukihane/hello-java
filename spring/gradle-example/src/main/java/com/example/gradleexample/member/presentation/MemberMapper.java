package com.example.gradleexample.member.presentation;

import com.example.gradleexample.member.domain.Member;
import org.mapstruct.Mapper;

@Mapper
public interface MemberMapper {
    Member mapFrom(MemberRegistrationForm form);
}
