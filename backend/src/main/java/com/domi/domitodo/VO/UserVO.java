package com.domi.domitodo.VO;

import com.domi.domitodo.entity.User;
import lombok.AllArgsConstructor;

public class UserVO {
    private int id;
    private String name;
    private String email;

    public static UserVO toEntity(User user) {
        UserVO result = new UserVO();

        result.id = user.getId();
        result.name = user.getName();
        result.email = user.getEmail();

        return result;
    }
}
