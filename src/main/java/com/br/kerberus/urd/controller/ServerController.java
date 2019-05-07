package com.br.kerberus.urd.controller;

import com.br.kerberus.urd.domain.entity.Server;
import com.br.kerberus.urd.domain.resource.ErrorResponse;
import com.br.kerberus.urd.domain.resource.ServerRequest;
import com.br.kerberus.urd.domain.resource.ServerResponse;
import com.br.kerberus.urd.exception.UrdException;
import com.br.kerberus.urd.service.ServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(value = "server")
@RestController
@RequestMapping(path = { "/api/server" })
public class ServerController {

	@Autowired
	ServerService service;

	@ApiOperation(httpMethod = "POST", value = "This service provide resource to add one server.", response = ServerResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Could not make request. Try later.", response = ErrorResponse.class) })
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(path = { "/add" })
	public ResponseEntity<ServerResponse> addServer(@RequestBody @Validated ServerRequest server) throws UrdException {

		Server srv = new Server();
		srv.setHostname(server.getHostname());
		srv.setCreationDate(new Date());

		ServerResponse response = new ServerResponse(service.addServer(srv));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@ApiOperation(httpMethod = "PUT", value = "This service provide resource to update a server by id.", response = ServerResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Server not found with id informed", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Could not make request. Try later.", response = ErrorResponse.class)
	})
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(path = { "/{id}" })
	public ResponseEntity<ServerResponse> updateServer(@RequestBody @Validated ServerRequest server,
			@PathVariable(name = "id", required = true) Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}

	@ApiOperation(httpMethod = "DELETE", value = "This service provide resource to delete one server by id.", response = ServerResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Server not found with id informed", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Could not make request. Try later.", response = ErrorResponse.class)
	})
	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<ServerResponse> deleteServer(@PathVariable(name = "id", required = true) Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}

	@ApiOperation(httpMethod = "GET", value = "Return all servers.", response = ServerResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Server not found with id informed", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Could not make request. Try later.", response = ErrorResponse.class)
	})
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(path = { "/all" })
	public ResponseEntity<List<ServerResponse>> getAllServers() {

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}

	@ApiOperation(httpMethod = "GET", value = "Return one server by id.", response = ServerResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Server not found with id informed", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Could not make request. Try later.", response = ErrorResponse.class)
	})
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<ServerResponse> getServer(@PathVariable(name = "id", required = true) Long id) throws UrdException {

		ServerResponse server = new ServerResponse(service.getServerById(id).get());

		return ResponseEntity.status(HttpStatus.OK).body(server);

	}

}
