package com.br.kerberus.urd.controller;

import com.br.kerberus.urd.resource.ErrorResponse;
import com.br.kerberus.urd.resource.ServerResource;
import com.br.kerberus.urd.exception.UrdException;
import com.br.kerberus.urd.service.ServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "server")
@RestController
@RequestMapping(path = {"/api/server"})
public class ServerController {

    private final ServerService service;

    public ServerController(ServerService service) {
        this.service = service;
    }

    @ApiOperation(httpMethod = "POST", value = "This service provide resource to add one server.", response = ServerResource.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "Bad Request", response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = "Could not make request. Try later.", response = ErrorResponse.class)
            })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = {"/add"})
    public ResponseEntity<ServerResource> addServer(@RequestBody @Validated ServerResource server) throws UrdException {

        ServerResource response = new ServerResource(service.addServer(server));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(httpMethod = "PUT", value = "This service provide resource to update a server by id.", response = ServerResource.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Server not found with id informed", response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = "Bad Request", response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = "Could not make request. Try later.", response = ErrorResponse.class)
            })
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(path = {"/{id}"})
    public ResponseEntity<ServerResource> updateServer(@RequestBody @Validated ServerResource server,
                                                       @PathVariable(name = "id", required = true) Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ApiOperation(httpMethod = "DELETE", value = "This service provide resource to delete one server by id.", response = ServerResource.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Server not found with id informed", response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = "Bad Request", response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = "Could not make request. Try later.", response = ErrorResponse.class)
            })
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<ServerResource> deleteServer(@PathVariable(name = "id", required = true) Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ApiOperation(httpMethod = "GET", value = "Return all servers.", response = ServerResource.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Server not found with id informed", response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = "Bad Request", response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = "Could not make request. Try later.", response = ErrorResponse.class)
            })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = {"/all"})
    public ResponseEntity<List<ServerResource>> getAllServers() {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ApiOperation(httpMethod = "GET", value = "Return one server by id.", response = ServerResource.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Server not found with id informed", response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = "Bad Request", response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = "Could not make request. Try later.", response = ErrorResponse.class)
            })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = {"/{id}"})
    @Transactional
    public ResponseEntity<ServerResource> getServer(@PathVariable(name = "id", required = true) Long id) throws UrdException {

        ServerResource server = new ServerResource(service.getServerById(id).get());

        return ResponseEntity.status(HttpStatus.OK).body(server);
    }
}
