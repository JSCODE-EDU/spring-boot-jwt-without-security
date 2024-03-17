package com.example.jwtauth;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final MemberRepository memberRepository;
  private final JwtProvider jwtProvider;

  public AuthService(MemberRepository memberRepository, JwtProvider jwtProvider) {
    this.memberRepository = memberRepository;
    this.jwtProvider = jwtProvider;
  }

  public Member signup(SignupRequestDto signupRequestDto) {
    Member member = new Member(signupRequestDto.getEmail(), signupRequestDto.getPassword());
    return memberRepository.save(member);
  }

  public LoginResponseDto login(LoginRequestDto loginRequestDto) {
    Member member = memberRepository.findByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    String jws = jwtProvider.createJwsWithId(member.getId());
    return new LoginResponseDto(jws);
  }

  public Member getMyProfile(String token) {
    Long id = jwtProvider.getMemberIdByDecoding(token);
    return memberRepository.findById(id).get();
  }
}
