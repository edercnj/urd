package com.br.kerberus.urd.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.br.kerberus.urd.domain.entity.Server;
import com.br.kerberus.urd.exception.UrdException;
import com.br.kerberus.urd.repository.ServerRepository;

@Service
public class ServerService {

	@Autowired
	private ServerRepository repository;

	public Optional<Server> getServerById(Long id) throws UrdException {
		Optional<Server> server = repository.findById(id);

		if (server == null) {
			throw new UrdException(HttpStatus.NOT_FOUND, String.format("Server with id {%s} not found", id),
					String.format("Server with id {%s} not found. Please enter a valid value for search.", id), 501);
		}
		return server;
	}

}
