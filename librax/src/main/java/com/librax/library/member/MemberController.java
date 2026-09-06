package com.librax.library.member;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Integer id) {
        return memberService.getMemberById(id);
    }
}
