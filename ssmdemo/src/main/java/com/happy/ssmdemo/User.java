package com.happy.ssmdemo;

import lombok.Data;
import lombok.NoArgsConstructor;

// @Data 由Lombok提供，不需要再定义getter和setter
@Data
@NoArgsConstructor
public class User {

    private Long id;

    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}