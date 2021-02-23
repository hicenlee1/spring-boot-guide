package com.example.helloworld.jpa.repository;

import com.example.helloworld.model.po.Person;
import com.example.helloworld.repository.jpa.PersonRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    private Long id;

    @Before
    public void setUp() {
        Assert.assertNotNull(personRepository);
        Person p = new Person("hicen", 30);
        Person savedPerson = personRepository.saveAndFlush(p);
        id = savedPerson.getId();
        System.out.println(">>>id=" + id);
    }

    @After
    public void clear() {
        personRepository.deleteAll();
    }

    /**
     * 使用jpa 自带的方法查找PERSON
     */
    @Test
    public void should_get_person() {
        Optional<Person> personOptional = personRepository.findById(id);
        Assert.assertTrue(personOptional.isPresent());
        Assert.assertEquals("hicen", personOptional.get().getName());
        Assert.assertEquals(Integer.valueOf(30), personOptional.get().getAge());

        List<Person> personList = personRepository.findByAgeGreaterThan(18);
        Assert.assertEquals(1, personList.size());
    }

    @Test
    public void should_get_person_use_custom_query() {
        //查找所有字段
        Optional<Person> personOptional = personRepository.findByNameCustom("hicen");
        Assert.assertTrue(personOptional.isPresent());
        Assert.assertEquals(Integer.valueOf(30), personOptional.get().getAge());

        //查找部分字段
        String personName = personRepository.findPersonNameById(id);
        Assert.assertEquals("hicen", personName);
        System.out.println(">>id2" + id);

        //更新
        personRepository.updatePersonNameById("updatename", id);
        Optional<Person> updatename = personRepository.findByNameCustom("updatename");
        Assert.assertTrue(updatename.isPresent());

    }

}


