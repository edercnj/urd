package com.br.kerberus.urd.controller;

import java.util.List;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/server")
@Api(value = "Server")
public class ServerController {

	@PostMapping(value = "/add")
	@ApiOperation(value = "Add server", notes = "ServerController.addServer")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Created a new Server.")})
	public ResponseEntity<Server> addServer(@RequestBody @Validated ServerRequest server) {

		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Server> updateServer(@RequestBody @Validated ServerRequest server, @PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Server> deleteServer(@PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<Server>> getAllServers() {

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Server> getServer() {

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}

}
