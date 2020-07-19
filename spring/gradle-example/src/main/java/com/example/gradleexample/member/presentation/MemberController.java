package com.example.gradleexample.member.presentation;

import com.example.gradleexample.member.domain.Member;
import com.example.gradleexample.member.domain.MemberRegistrationService;
import com.example.gradleexample.member.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRegistrationService memberRegistrationService;
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    /**
     * 全会員一覧表示
     */
    @GetMapping
    public String memberAll(final Model model) {
        final List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "member";
    }

    @PostMapping
    public String memberRegistration(
        @ModelAttribute final MemberRegistrationForm memberRegistrationForm,
        final Model model) {
        final Member member = memberMapper.mapFrom(memberRegistrationForm);
        memberRegistrationService.register(member);
        return "member";
    }
}
