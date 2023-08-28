package com.eduardo.newcadastro.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.newcadastro.domain.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
