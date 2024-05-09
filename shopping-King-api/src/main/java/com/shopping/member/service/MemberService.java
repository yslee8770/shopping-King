package com.shopping.member.service;

import org.springframework.stereotype.Service;
import com.shopping.common.mapper.MemberMapper;
import com.shopping.member.dto.MemberRequestDto;
import com.shopping.member.entity.Member;
import com.shopping.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  public Member addMember(MemberRequestDto requestMemberDto) {
    return memberRepository.save(MemberMapper.requestMemberDtoToMember(requestMemberDto));
  }

  public boolean existsByName(String existingName) {
    return memberRepository.existsByName(existingName);
  }

}
