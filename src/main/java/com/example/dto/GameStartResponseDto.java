package com.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GameStartResponseDto implements Serializable {

    private String uuid;
    private String number;
    private Map<String,Integer> useNum;
    private boolean isFinish = false;

}
