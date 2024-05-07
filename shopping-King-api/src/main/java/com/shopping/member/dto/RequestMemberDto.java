package com.shopping.member.dto;

import com.shopping.common.MemberRole;
import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestMemberDto {

  @Nullable
  private Long id;

  private String name;

  private String password;

  private String address;

  private MemberRole memberRole;

}
