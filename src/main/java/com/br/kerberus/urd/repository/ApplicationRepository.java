package com.br.kerberus.urd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.kerberus.urd.domain.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long>{

}
