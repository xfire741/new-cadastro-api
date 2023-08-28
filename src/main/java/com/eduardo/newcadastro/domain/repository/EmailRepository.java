package com.eduardo.newcadastro.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.newcadastro.domain.model.Email;

public interface EmailRepository extends JpaRepository<Email, Long>{

}
