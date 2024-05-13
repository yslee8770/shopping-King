package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shopping.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Boolean existsByName(String memberName);

}
