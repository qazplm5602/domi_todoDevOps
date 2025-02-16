package com.domi.domitodo.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserTokenVO {
    private String userId;
    private String name;
    private boolean refresh;
}
