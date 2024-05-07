package com.shopping.member.service;

import org.springframework.stereotype.Service;
import com.shopping.common.mapper.MemberMapper;
import com.shopping.member.dto.RequestMemberDto;
import com.shopping.member.entity.Member;
import com.shopping.member.repository.MemberRepository;
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
