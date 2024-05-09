package com.shopping.member.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.shopping.common.MemberRole;
import com.shopping.member.dto.RequestMemberDto;
import com.shopping.member.entity.Member;
import com.shopping.member.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

  @InjectMocks
  private MemberService memberService;

  private RequestMemberDto existingMemberDto;
  private RequestMemberDto newMemberDto;

  @BeforeEach
  public void setUp() {
    existingMemberDto = RequestMemberDto
        .builder()
        .name("existingName")
        .password("existingPassword")
        .address("existingAddress")
        .memberRole(MemberRole.USER)
        .build();

    newMemberDto = RequestMemberDto
        .builder()
        .name("newName")
        .password("newPassword")
        .address("newAddress")
        .memberRole(MemberRole.ADMIN)
        .build();
  }

  @Test
  @DisplayName("닉네임 중복으로 인한 회원가입실패")
  public void testRegisterMemberWithExistingName() {
    // Given
    when(memberService.existsByName("test")).thenReturn(true);

    // When
    Member result = memberService.addMember(existingMemberDto);

    // Then
    assertNotNull(result);
  }

  @Test
  @DisplayName("회원가입성공")
  public void testRegisterMemberWithNewName() {
    // Given
    when(memberService.existsByName("test")).thenReturn(false);

    // When
    Member result = memberService.addMember(newMemberDto);

    // Then
    assertNotNull(result);
  }


}
