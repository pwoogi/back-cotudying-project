package com.week06.cotudying_project.repository;


import com.week06.cotudying_project.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Member findByUsername(String username);
    public Member findByName(String name);
}