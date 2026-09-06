package com.librax.library.member;

import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MemberRepository {
    private final Map<Integer, Member> members = new HashMap<>();

    public MemberRepository() {
        members.put(1, new Member(1, "Alice"));
        members.put(2, new Member(2, "Bob"));
    }

    public Optional<Member> findById(Integer id) {
        return Optional.ofNullable(members.get(id));
    }
}
