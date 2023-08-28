package com.eduardo.newcadastro.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.newcadastro.domain.model.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long>{

}
