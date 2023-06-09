package com.jobtang.sourcecompany.api.user.controller;

import com.jobtang.sourcecompany.api.exception.customerror.ErrorCode;
import com.jobtang.sourcecompany.api.user.dto.EmailAndCode;
import com.jobtang.sourcecompany.api.user.dto.LoginRequestDto;
import com.jobtang.sourcecompany.api.user.dto.SignupRequestDto;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import com.jobtang.sourcecompany.api.user.service.EmailService;
import com.jobtang.sourcecompany.api.user.service.UserService;
import com.jobtang.sourcecompany.config.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.jobtang.sourcecompany.api.exception.customerror.CustomException;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Api("유저 API")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final EmailService emailService;

    // 로그인. 현재는 jwt 반환하는 형태
    @ApiOperation(
            value = "유저 로그인",
            notes = "email, ",
            response = HashMap.class
    )
    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto request) {
        HashMap<String,Object> result = new HashMap<>();

        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()) {
            User currentUser = user.get();
            if (!currentUser.isActive()) {
                throw new CustomException("User Not Active", ErrorCode.USER_NOT_ACTIVE);
            }
            if (request.getPassword().equals(currentUser.getPassword())) {
                Map<String,Object> data = new HashMap<>();
                data.put("token",jwtTokenProvider.createToken(currentUser, currentUser.getRole()));
                data.put("nickname",currentUser.getNickname());
                result.put("data", data);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                throw new CustomException("Not Current Password", ErrorCode.PASSWORD_NOT_CURRENT);
            }
        } else {
            throw new CustomException("Not Sign Email", ErrorCode.USER_NOT_FOUND);
        }
    }


    // 회원가입
    @ApiOperation(
            value = "유저 회원 가입",
            notes = "입력된 값을 가지고 회원가입",
            response = HashMap.class
    )
    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody @Valid SignupRequestDto request, Errors errors) {
        HashMap<String,Object> result = new HashMap<>();

        // 회원 가입 중복 재검증
        boolean isEmailDuplicate = userService.validateDuplicateEmail(request.getEmail());
        if (isEmailDuplicate) {
            errors.rejectValue("email","204","중복된 이메일입니다");
        }
        if (userService.validateDuplicateNickname(request.getNickname())) {
            errors.rejectValue("nickname","204","중복된 닉네임입니다");
        }
        // 기본 유효성 검사
        if (errors.hasErrors()) {
            Map<String, String> validatorResult = userService.validateHandling(errors);
            result.put("data", validatorResult);
            result.put("message", "회원 가입 유효성 에러");
            result.put("status", "400");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        User signUser = userService.signupUser(request);
        if (signUser == null) {
            throw new CustomException("signup failed", ErrorCode.SAVE_FAILED);
        }
        result.put("data", signUser);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // username 중복검증
    @ApiOperation(
            value = "email 중복검증",
            notes = "email 중복인지 확인",
            response = HashMap.class
    )
    @GetMapping("/validusername/{email}")
    public ResponseEntity<?> validateDuplicateEmail(@PathVariable String email) {
        HashMap<String,Object> result = new HashMap<>();
        boolean isEmailDuplicate = userService.validateDuplicateEmail(email);
        if (isEmailDuplicate) {
            throw new CustomException("Duplicated Email", ErrorCode.USER_EXISTS);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "nickname 중복검증",
            notes = "nickname이 중복인지 확인",
            response = HashMap.class
    )
    @GetMapping("/validnickname/{nickname}")
    public ResponseEntity<?> validateDuplicateNickname(@PathVariable String nickname) {
        HashMap<String,Object> result = new HashMap<>();
        boolean isNicknameDuplicate = userService.validateDuplicateNickname(nickname);
        if (isNicknameDuplicate) {
            throw new CustomException("Duplicated Nickname", ErrorCode.USER_EXISTS);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "email 인증",
            notes = "email 중복 확인 및 인증 코드 발송",
            response = HashMap.class
    )
    @PostMapping("/sendemail")
    public ResponseEntity<?> emailCert(@RequestBody String email) {
        HashMap<String,Object> result = new HashMap<>();
        boolean isEmailDuplicate = userService.validateDuplicateEmail(email);
        if (isEmailDuplicate) {
            throw new CustomException("Duplicated Email", ErrorCode.USER_EXISTS);
        }
        emailService.sendEmailCert(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "email 인증 코드 확인",
            notes = "email 인증 코드 검증",
            response = HashMap.class
    )
    @PostMapping("/checkemailcert")
    public ResponseEntity<?> checkEmailCert(@RequestBody EmailAndCode emailAndCode ) {
        boolean isCurrent = emailService.checkEmailCert(emailAndCode);
        if (isCurrent) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new CustomException("Not Current email code", ErrorCode.EMAIL_NOT_CURRENT);
        }
    }

    @ApiOperation(
            value = "토큰 유효기간 만료 확인",
            notes = "토큰 유효기간 만료 확인",
            response = HashMap.class
    )
    @GetMapping("/tokenvaild")
    public ResponseEntity<?> tokenVaild(@RequestHeader("Authorization") String authHeader) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
