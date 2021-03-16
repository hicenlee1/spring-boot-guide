package com.example.springbootmultidatasoruce.db2.service;

import com.example.springbootmultidatasoruce.db2.mapper.MoneyMapper;
import com.example.springbootmultidatasoruce.entity.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyService {
    @Autowired
    private MoneyMapper moneyMapper;

    public Money selectMoney() {
        return moneyMapper.selectAll().get(0);
    }
}
