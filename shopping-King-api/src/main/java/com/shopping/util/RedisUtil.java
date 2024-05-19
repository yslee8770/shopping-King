package com.shopping.util;

import com.shopping.entity.Member;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisUtil {

  private final JwtTokenUtil jwtTokenUtil;
  private final RedisTemplate<String, String> redisTemplate;

  public boolean validateToken(String token) {

    return Boolean.TRUE.equals(redisTemplate.opsForValue().getOperations().hasKey(token));
  }

  public void insertToken(String token, String name) {
    redisTemplate.opsForValue()
        .set(token, name, jwtTokenUtil.getValidityInMilliseconds(),
            TimeUnit.MILLISECONDS);
  }


}
