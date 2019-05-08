package com.br.kerberus.urd.service;

import com.br.kerberus.urd.core.log.LogExecutionTime;
import com.br.kerberus.urd.domain.entity.Server;
import com.br.kerberus.urd.domain.resource.ServerResource;
import com.br.kerberus.urd.exception.UrdException;
import com.br.kerberus.urd.repository.ServerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServerService {

    private final ServerRepository repository;

    public ServerService(ServerRepository repository) {
        this.repository = repository;
    }

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

    public Server addServer(ServerResource server) throws UrdException {
        try {
            Server srv = new Server();
            srv.setHostname(server.getHostname());
            srv.setCreationDate(new Date());

            return repository.save(srv);

        } catch (Exception e) {
            throw new UrdException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Could not include server. Error: %s", e.getCause().getMessage()),
                    "Could not include server. Please try again later.",
                    502);
        }
    }
}
