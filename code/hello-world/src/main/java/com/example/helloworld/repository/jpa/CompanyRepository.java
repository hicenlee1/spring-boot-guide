package com.example.helloworld.repository.jpa;

import com.example.helloworld.model.po.Company;
import com.example.helloworld.model.po.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
