package com.br.kerberus.urd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.kerberus.urd.domain.entity.Server;

public interface ServerRepository extends JpaRepository<Server, Long>{
	

}
