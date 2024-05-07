package com.shop.member.service;

import org.springframework.stereotype.Service;
import com.shop.common.mapper.MemberMapper;
import com.shop.member.dto.RequestMemberDto;
import com.shop.member.entity.Member;
import com.shop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public Member addMember(RequestMemberDto requestMemberDto) {
    return memberRepository.save(MemberMapper.requestMemberDtoToMember(requestMemberDto));
  }

  public boolean existsByName(String existingName) {
    return memberRepository.existsByName(existingName);
  }

}
