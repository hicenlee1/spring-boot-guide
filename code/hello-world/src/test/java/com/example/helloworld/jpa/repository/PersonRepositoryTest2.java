package com.example.helloworld.jpa.repository;

import com.example.helloworld.model.dto.UserDTO;
import com.example.helloworld.model.po.Person;
import com.example.helloworld.repository.jpa.CompanyRepository;
import com.example.helloworld.repository.jpa.PersonRepository;
import static org.junit.Assert.*;

import com.example.helloworld.repository.jpa.SchoolRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:/init.sql")
public class PersonRepositoryTest2 {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @After
    public  void clear() {
        //truncate
        personRepository.truncateTable();
        //delete
        companyRepository.deleteAll();
        schoolRepository.deleteAll();
    }

    @Test
    public void find_person_age_older_than_18() {
        List<Person> personList = personRepository.findByAgeGreaterThan(18);
        System.out.println(personList.get(0).getId());
    }

//    @Test
//    public void should_get_user_info() {
//        Optional<UserDTO> userInformation = personRepository.getUserInformation(1L);
//        System.out.println(userInformation.get().toString());
//    }

    @Test
    public void should_get_user_info_list() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.Direction.DESC,"age");
        Page<UserDTO> userInformationList = personRepository.getUserInformationList(pageRequest);
        //结果总数
        System.out.println("totalElements>>" + userInformationList.getTotalElements());
        //总页数
        System.out.println("getTotalPages>>" + userInformationList.getTotalPages());
        //查询到的分页内容
        System.out.println("getContent>>"+userInformationList.getContent());
    }


    @Test
    public void should_filter_user_info() {
        List<String> personList = new ArrayList<>(Arrays.asList("person1", "person2"));
        List<UserDTO> userDTOS = personRepository.filterUserInfo(personList);
        System.out.println("userDTOS:" + userDTOS);
    }

    @Test
    public void should_filter_user_info_by_age() {
        List<UserDTO> userDTOS = personRepository.filterUserInfoByAge(19, 20);
        System.out.println("userDTOS2：" + userDTOS);
    }
}
