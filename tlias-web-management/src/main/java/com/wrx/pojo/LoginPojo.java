package com.wrx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginPojo {

    private Integer id;
    private String username;
    private String name;
    private String token;

}
