package com.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserFormDto implements Serializable {

    private String login;
    private String password;

}
