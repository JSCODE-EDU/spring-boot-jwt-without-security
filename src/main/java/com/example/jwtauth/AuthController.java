package com.example.jwtauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("signup")
  public Member signup(@RequestBody SignupRequestDto signupRequestDto) {
    return authService.signup(signupRequestDto);
  }

  @PostMapping("login")
  public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
    return authService.login(loginRequestDto);
  }

  @GetMapping("me")
  public Member getMyProfile(@RequestHeader("Authorization") String token) {
    return authService.getMyProfile(token);
  }
}
