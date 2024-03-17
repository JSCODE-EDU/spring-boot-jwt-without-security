package com.example.jwtauth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Member findByEmailAndPassword(String email, String password);
}
