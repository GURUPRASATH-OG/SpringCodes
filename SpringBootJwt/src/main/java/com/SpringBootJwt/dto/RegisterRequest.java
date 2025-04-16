package com.SpringBootJwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest
{
    private String name;
    private String password;
    private Set<String> roles; //A set of roles to be passed along with the request.
}
