package com.br.kerberus.urd.controller;

import com.br.kerberus.urd.entity.Application;
import com.br.kerberus.urd.exception.ManagedException;
import com.br.kerberus.urd.entity.core.LogHttpMessages;
import com.br.kerberus.urd.resource.ApplicationRequest;
import com.br.kerberus.urd.resource.ErrorResponse;
import com.br.kerberus.urd.resource.ServerResponse;
import com.br.kerberus.urd.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "application")
@RestController
@RequestMapping(path = {"/api/application"})
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @ApiOperation(httpMethod = "POST", value = "This service provide resource to add one application.", response = ServerResponse.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "Bad Request", response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = "Could not make request. Try later.", response = ErrorResponse.class)
            })
    @LogHttpMessages
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = {"/add"})
    public ResponseEntity<ApplicationRequest> addServer(@RequestBody @Validated ApplicationRequest application) throws ManagedException {

        ApplicationRequest response = new ApplicationRequest(service.addApplication(application));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(httpMethod = "PUT", value = "This service provide resource to update one application by id.", response = ServerResponse.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Application not found with id informed", response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = "Bad Request", response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = "Could not make request. Try later.", response = ErrorResponse.class)
            })
    @LogHttpMessages
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(path = {"/{id}"})
    public ResponseEntity<ApplicationRequest> updateServer(@RequestBody @Validated ApplicationRequest server,
                                                           @PathVariable(name = "id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ApiOperation(httpMethod = "DELETE", value = "This service provide resource to delete one Application by id.", response = ServerResponse.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Application not found with id informed", response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = "Bad Request", response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = "Could not make request. Try later.", response = ErrorResponse.class)
            })
    @LogHttpMessages
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Application> deleteServer(@PathVariable(name = "id") Integer id) throws ManagedException {

        Application application = service.getApplicationById(id);

        return ResponseEntity.status(HttpStatus.OK).body(application);
    }

    @ApiOperation(httpMethod = "GET", value = "Return all Applications.", response = ServerResponse.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Application not found with id informed", response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = "Bad Request", response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = "Could not make request. Try later.", response = ErrorResponse.class)
            })
    @LogHttpMessages
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = {"/all"})
    public ResponseEntity<List<ApplicationRequest>> getAllServers() {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ApiOperation(httpMethod = "GET", value = "Return one Application by id.", response = ServerResponse.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Application not found with id informed", response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = "Bad Request", response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = "Could not make request. Try later.", response = ErrorResponse.class)
            })
    @LogHttpMessages
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Application> getServer(@PathVariable(name = "id") Integer id) throws ManagedException {

        Application application = service.getApplicationById(id);

        return ResponseEntity.status(HttpStatus.OK).body(application);
    }
}
