package com.br.kerberus.urd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.br.kerberus.urd.domain.entity.Server;

@Transactional(timeout = 3)
public interface ServerRepository extends JpaRepository<Server, Long>{

}
