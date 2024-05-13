package com.shopping.common.mapper;

import com.shopping.member.dto.MemberRequestDto;
import com.shopping.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class MemberMapper {

  public static Member requestMemberDtoToMemberForSave(MemberRequestDto requestMemberDto) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return requestMemberDto == null ? null
        : Member
            .builder()
            .name(requestMemberDto.getName())
            .password(passwordEncoder.encode(requestMemberDto.getPassword()))
            .address(requestMemberDto.getAddress())
            .memberRole(requestMemberDto.getMemberRole())
            .build();
  }
}
