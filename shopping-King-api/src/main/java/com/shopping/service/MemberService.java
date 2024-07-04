package com.shopping.service;

import com.shopping.dto.MemberLoginRequestDto;
import com.shopping.dto.MemberRequestDto;
import com.shopping.entity.Member;
import com.shopping.mapper.MemberMapper;
import com.shopping.repository.MemberRepository;
import com.shopping.util.JwtTokenUtil;
import com.shopping.util.RedisUtil;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final JwtTokenUtil jwtTokenUtil;
  private final RedisTemplate<String, String> redisTemplate;
  private final RedisUtil redisUtil;

  public Member addMember(MemberRequestDto requestMemberDto) {
    return memberRepository.save(MemberMapper.requestMemberDtoToMemberForSave(requestMemberDto));
  }

  public boolean existsByName(String existingName) {
    return memberRepository.existsByName(existingName);
  }

  public String login(MemberLoginRequestDto memberLoginRequestDto) {
    Member member=validateMemberCredentials(memberLoginRequestDto);
    String token=jwtTokenUtil.createToken(member.getName(),member.getMemberRole().toString());
    redisUtil.insertToken(token,member.getName());
    return token;
  }

  private Member validateMemberCredentials(MemberLoginRequestDto memberLoginRequestDto) {
    Optional<Member> memberOptional = memberRepository.findByName(memberLoginRequestDto.getName());

    if (memberOptional.isPresent()) {
      Member member = memberOptional.get();
      if (member.getPassword().equals(memberLoginRequestDto.getPassword())) {
        return member;
      } else {
        throw new RuntimeException("Invalid username or password");
      }
    } else {
      throw new RuntimeException("Invalid username or password");
    }
  }

}
