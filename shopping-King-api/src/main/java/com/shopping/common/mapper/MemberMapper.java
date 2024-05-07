package com.shopping.common.mapper;

import com.shopping.member.dto.RequestMemberDto;
import com.shopping.member.entity.Member;

public class MemberMapper {

  public static Member requestMemberDtoToMember(RequestMemberDto requestMemberDto) {
    return Member
        .builder()
        .name(requestMemberDto.getName())
        .password(requestMemberDto.getPassword())
        .address(requestMemberDto.getAddress())
        .memberRole(requestMemberDto.getMemberRole())
        .build();
  }

}
