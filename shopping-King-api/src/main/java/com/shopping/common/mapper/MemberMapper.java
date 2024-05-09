package com.shopping.common.mapper;

import com.shopping.member.dto.MemberRequestDto;
import com.shopping.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class MemberMapper {

  private final PasswordEncoder passwordEncoder;

  public static Member requestMemberDtoToMember(MemberRequestDto requestMemberDto) {
    return requestMemberDto == null ? null
        : Member
            .builder()
            .name(requestMemberDto.getName())
            .password(requestMemberDto.getPassword())
            .address(requestMemberDto.getAddress())
            .memberRole(requestMemberDto.getMemberRole())
            .build();
  }
}
