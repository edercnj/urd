package com.br.kerberus.urd.repository;

import com.br.kerberus.urd.domain.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional (timeout = 2)
public interface ServerRepository extends JpaRepository<Server, Long>{
	

}
