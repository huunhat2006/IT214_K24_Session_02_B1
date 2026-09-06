package com.librax.library.member;

import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMemberById(Integer id) {
        return memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
    }
    
    public void updateMember(Member member) {
        // Since we are using in-memory map and mutating the object, it's implicitly updated.
    }
}
