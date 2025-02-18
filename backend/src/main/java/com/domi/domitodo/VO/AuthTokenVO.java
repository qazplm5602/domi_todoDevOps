package com.domi.domitodo.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthTokenVO {
    private String access;
    private String refresh;
}
