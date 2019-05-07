package com.br.kerberus.urd.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.kerberus.urd.domain.entity.Application;
import com.br.kerberus.urd.repository.ApplicationRepository;

@Service
public class ServiceApplicationService {
	
	@Autowired
	ApplicationRepository repository;
	
	public Optional<Application> getApplicationById(Long id)
	{
		return repository.findById(id);
	}

}
