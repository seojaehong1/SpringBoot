package com.example.mybatis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Board {
    private int id;
    private String title;
    private String content;
    private Date createdAt;
}
