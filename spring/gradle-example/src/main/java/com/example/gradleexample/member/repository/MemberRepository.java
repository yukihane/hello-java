package com.example.gradleexample.member.repository;

import com.example.gradleexample.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
