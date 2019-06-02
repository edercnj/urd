package com.br.kerberus.urd.service;

import com.br.kerberus.urd.entity.Server;
import com.br.kerberus.urd.exception.ManagedException;
import com.br.kerberus.urd.exception.NoManagedException;
import com.br.kerberus.urd.log.*;
import com.br.kerberus.urd.repository.ServerRepository;
import com.br.kerberus.urd.resource.ServerRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServerService {

    private final ServerRepository repository;

    public ServerService(ServerRepository repository) {
        this.repository = repository;
    }

    @LogException(LogType = LogType.EXCEPTION)
    @LogMethodCall(LogType = LogType.METHOD)
    @LogMetlhodReturn(LogType = LogType.METHOD)
    @LogExecutionTime(LogType = LogType.EXECUTION_TIME)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public Server getServerById(Integer id) throws ManagedException {
        Optional<Server> server = repository.findById(id);

        if (server.isEmpty())
            throw new ManagedException(LogError.SERVER_NOT_FOUND);

        return server.get();
    }

    @LogException(LogType = LogType.EXCEPTION)
    @LogMethodCall(LogType = LogType.METHOD)
    @LogMetlhodReturn(LogType = LogType.METHOD)
    @LogExecutionTime(LogType = LogType.EXECUTION_TIME)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public Server getServerByHostname(String name) throws ManagedException {

        Optional<Server> server = repository.findByHostnameIsLike(name);

        if (server.isEmpty())
            throw new ManagedException(LogError.SERVER_NOT_FOUND);

        return server.get();
    }

    @LogException(LogType = LogType.EXCEPTION)
    @LogMethodCall(LogType = LogType.METHOD)
    @LogMetlhodReturn(LogType = LogType.METHOD)
    @LogExecutionTime(LogType = LogType.EXECUTION_TIME)
    public List<Server> findAllServers() throws ManagedException {
        List<Server> servers = repository.findAll();

        if (servers.isEmpty())
            throw new ManagedException(LogError.SERVER_NOT_FOUND);

        return servers;
    }

    @LogException(LogType = LogType.EXCEPTION)
    @LogMethodCall(LogType = LogType.METHOD)
    @LogMetlhodReturn(LogType = LogType.METHOD)
    @LogExecutionTime(LogType = LogType.EXECUTION_TIME)
    public Server addServer(ServerRequest server) throws NoManagedException {
        try {
            Server srv = new Server();
            srv.setHostname(server.getHostname());
            srv.setCreationDate(new Date());

            return repository.save(srv);
        } catch (Exception e) {
            throw new NoManagedException(e);
        }
    }

    @LogException(LogType = LogType.EXCEPTION)
    @LogMethodCall(LogType = LogType.METHOD)
    @LogMetlhodReturn(LogType = LogType.METHOD)
    @LogExecutionTime(LogType = LogType.EXECUTION_TIME)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public Server removeServerById(Integer id) throws ManagedException {
        Optional<Server> server = repository.findById(id);

        if (server.isEmpty())
            throw new ManagedException(LogError.SERVER_NOT_FOUND);
        else
            repository.delete(server.get());

        return server.get();
    }
}
