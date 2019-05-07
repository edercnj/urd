package com.br.kerberus.urd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.kerberus.urd.core.log.LogExecutionTime;
import com.br.kerberus.urd.domain.entity.Server;
import com.br.kerberus.urd.exception.UrdException;
import com.br.kerberus.urd.repository.ServerRepository;

@Service
public class ServerService {

	@Autowired
	private ServerRepository repository;

	@Transactional(timeout = 2, readOnly = true)
	@LogExecutionTime
	public Optional<Server> getServerById(Long id) throws UrdException {
		Optional<Server> server = repository.findById(id);

		if (server.isEmpty()) {
			throw new UrdException(
					HttpStatus.NOT_FOUND,
					String.format("Server with id {%s} not found", id),
					String.format("Server with id {%s} not found. Please enter a valid value for search.", id),
					501);
		}
		return server;
	}

	public List<Server> findAllServers() {

		return repository.findAll();
	}

	public Server addServer(Server server) throws UrdException {
		try {
			return repository.save(server);

		} catch (Exception e) {
			throw new UrdException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					String.format("Could not include server. Error: %s", e.getCause().getMessage()),
					"Could not include server. Please try again later.",
					502);
		}
	}
}
