package com.shopping.member.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.shopping.member.dto.MemberRequestDto;
import com.shopping.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@DisplayName("MemberService 테스트")
public class MemberServiceTest {

  @Mock
  private MemberRepository memberRepository;

  @InjectMocks
  private MemberService memberService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("회원 가입 - 실패하는 테스트")
  public void testRegisterMember_Failure() {
    // Given
    MemberRequestDto requestDto = MemberRequestDto.builder()
        .name("John")
        .password("password")
        .address("123 Street")
        .build();

    // When
    when(memberRepository.save(any())).thenReturn(null);

    // Then
    assertThrows(RuntimeException.class, () -> {
      memberService.addMember(requestDto);
    });
  }
}


