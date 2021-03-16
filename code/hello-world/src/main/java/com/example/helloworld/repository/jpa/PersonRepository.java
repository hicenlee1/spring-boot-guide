package com.example.helloworld.repository.jpa;

import com.example.helloworld.model.dto.UserDTO;
import com.example.helloworld.model.po.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


/**
 * @Repository  表示和数据库操作有关
 *
 * 继承了 JpaRepository<T, ID> 就具有了 JPA 为我们提供好的增删改查、分页查询以及根据条件查询等方法
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    // jps 接口、扩展接口
    Optional<Person> findByName(String name);

    List<Person> findByAgeGreaterThan(int age);

    ////自定义SQL操作
    @Query("select p from Person p where p.name=:name")
    Optional<Person> findByNameCustom(@Param("name") String name);

    @Query("select p.name from Person p where p.id=:id")
    String findPersonNameById(@Param("id") Long id);


    @Modifying
    @Transactional
    @Query("UPDATE Person p set p.name=?1 where p.id=?2 ")
    void updatePersonNameById(String name, Long id);

    @Modifying
    @Transactional
    @Query(value="truncate table person", nativeQuery = true)
    void truncateTable();
    //连表查询
    @Query("select new com.example.helloworld.model.dto.UserDTO(p.name,p.age,c.companyName, s.name) " +
            "from Person p left join Company c on p.companyId=c.id " +
            "left join School s on p.schoolId=s.id " +
            "where p.id=:personId")
    Optional<UserDTO> getUserInformation(@Param("personId") Long personId);


    //分页
    @Query(value="select new com.example.helloworld.model.dto.UserDTO(p.name,p.age,c.companyName, s.name) " +
            "from Person p left join Company c on p.companyId=c.id " +
            "left join School s on p.schoolId=s.id ",
            countQuery = "select count(p.id) from Person p left join Company c on  p.companyId=c.id left join School s on p.schoolId=s.id")
    Page<UserDTO> getUserInformationList(Pageable pageable);


    //IN
    @Query(value="select new com.example.helloworld.model.dto.UserDTO(p.name,p.age,c.companyName, s.name) " +
            "from Person p left join Company c on p.companyId=c.id " +
            "left join School s on p.schoolId=s.id " +
            "where p.name in :peopleList")
    List<UserDTO> filterUserInfo(@Param("peopleList") List peopleList);

    @Query(value="select new com.example.helloworld.model.dto.UserDTO(p.name,p.age,c.companyName, s.name) " +
            "from Person p left join Company c on p.companyId=c.id " +
            "left join School s on p.schoolId=s.id " +
            "where p.age between :small and :big")
    List<UserDTO> filterUserInfoByAge(@Param("small") int small, @Param("big") int big);

}
