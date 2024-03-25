package com.kawshikbuet17.blog.payloads;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class JwtAuthResponse {
    private String jwtToken;
    private String username;
}
