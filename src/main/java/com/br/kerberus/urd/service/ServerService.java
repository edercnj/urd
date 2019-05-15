package com.br.kerberus.urd.service;

import com.br.kerberus.urd.entity.Server;
import com.br.kerberus.urd.exception.NoManagedException;
import com.br.kerberus.urd.exception.UrdException;
import com.br.kerberus.urd.log.*;
import com.br.kerberus.urd.repository.ServerRepository;
import com.br.kerberus.urd.resource.ServerResource;
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

    @LogException
    @LogMetlhodCall
    @LogMetlhodReturn
    @LogExecutionTime
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public Server getServerById(Integer id) throws UrdException {
        Optional<Server> server = repository.findById(id);

        if (server.isEmpty())
            throw new UrdException(LogError.SERVER_NOT_FOUND);


        return server.get();
    }

    @LogException
    @LogMetlhodCall
    @LogMetlhodReturn
    @LogExecutionTime
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public Server getServerByHostname(String name) throws UrdException {

        Optional<Server> server = repository.findByHostnameIsLike(name);

        if (server.isEmpty())
            throw new UrdException(LogError.SERVER_NOT_FOUND);

        return server.get();
    }

    @LogException
    @LogMetlhodCall
    @LogMetlhodReturn
    @LogExecutionTime
    public List<Server> findAllServers() throws UrdException {
        List<Server> servers = repository.findAll();

        if (servers.isEmpty())
            throw new UrdException(LogError.SERVER_NOT_FOUND);

        return servers;
    }

    @LogException
    @LogMetlhodCall
    @LogMetlhodReturn
    @LogExecutionTime
    public Server addServer(ServerResource server) throws NoManagedException {
        try {
            Server srv = new Server();
            srv.setHostname(server.getHostname());
            srv.setCreationDate(new Date());

            return repository.save(srv);
        } catch (Exception e) {
            throw new NoManagedException(e);
        }
    }
}
