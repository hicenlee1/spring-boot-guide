package com.example.springbootmultidatasoruce.db2.mapper;

import com.example.springbootmultidatasoruce.entity.Money;
import org.springframework.beans.factory.annotation.Qualifier;
import tk.mybatis.mapper.common.Mapper;

/**
* Created by Mybatis Generator 2021-03-01
*/

@Qualifier("db2SqlSessionTemplate")
public interface MoneyMapper extends Mapper<Money> {
}