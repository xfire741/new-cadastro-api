package com.eduardo.newcadastro.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.newcadastro.domain.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
