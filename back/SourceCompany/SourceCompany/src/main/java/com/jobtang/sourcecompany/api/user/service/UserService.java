package com.jobtang.sourcecompany.api.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
//    private final UserRepo userRepo;
//    private final AuthenticationManager authenticationManager;
//
//    // 회원 가입
//    public User signupUser(User user) {
//        return userRepo.save(user);
//    }
//
//    // 중복 회원 가입 검증
//    public void validateDuplicateMember(User user) {
//        User findUser = userRepo.findByUsername(user.getUsername());
//        if (findUser != null) {
//            throw new IllegalStateException("이미 가입된 회원입니다.");
//        }
//    }
//
//    // 로그인
//    public String loginUser(JwtRequestDto request) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        User principal = (User) authentication.getPrincipal();
//        return principal.getUsername();
//    }
}
