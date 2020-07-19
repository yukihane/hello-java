package com.example.gradleexample.member.domain;

import com.example.gradleexample.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberRegistrationServiceImpl implements MemberRegistrationService {

    private final MemberRepository memberRepository;

    @Override
    public Member register(final Member member) {
        return memberRepository.save(member);
    }

}
