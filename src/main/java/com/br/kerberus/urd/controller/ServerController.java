package com.br.kerberus.urd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.kerberus.urd.domain.entity.Server;
import com.br.kerberus.urd.domain.resource.ServerRequest;
import com.br.kerberus.urd.domain.resource.ServerResponse;
import com.br.kerberus.urd.exception.UrdException;
import com.br.kerberus.urd.service.ServerService;

@RestController
@RequestMapping(path = { "api/server" })
public class ServerController {

	@Autowired
	ServerService service;

	@PostMapping(value = "/add")
	public ResponseEntity<ServerResponse> addServer(@RequestBody @Validated ServerRequest server) {

		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<ServerResponse> updateServer(@RequestBody @Validated ServerRequest server, @PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<ServerResponse> deleteServer(@PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}

	@GetMapping(value = { "/all" })
	public ResponseEntity<List<ServerResponse>> getAllServers() {

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<ServerResponse> getServer(@PathVariable Long id) throws UrdException {

		ServerResponse server = new ServerResponse(service.getServerById(id));

		return ResponseEntity.status(HttpStatus.OK).body(server);

	}

}
