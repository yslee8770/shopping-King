package com.shopping.member.entity;

import java.util.ArrayList;
import java.util.List;
import com.shopping.order.entity.Orders;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class Member {

  @Id
  @GeneratedValue
  @Column(name = "member_id")
  private Long id;

  private String name;

  private String address;

  @OneToMany(mappedBy = "member")
  private List<Orders> orders = new ArrayList<Orders>();
}
