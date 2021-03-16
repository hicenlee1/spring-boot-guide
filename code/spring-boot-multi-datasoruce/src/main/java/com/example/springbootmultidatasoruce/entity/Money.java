package com.example.springbootmultidatasoruce.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
* Created by Mybatis Generator 2021-03-01
*/
@Table(name = "money")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Money implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 基本工资
     */
    private Integer basic;

    /**
     * 奖金
     */
    private Integer reward;

    /**
     * 惩罚金
     */
    private Integer punishment;

    private static final long serialVersionUID = 1L;
}