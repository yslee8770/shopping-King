package com.shop.common.mapper;

import com.shop.member.dto.RequestMemberDto;
import com.shop.member.entity.Member;

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
