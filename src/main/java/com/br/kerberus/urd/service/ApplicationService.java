package com.br.kerberus.urd.service;

import com.br.kerberus.urd.entity.Application;
import com.br.kerberus.urd.exception.UrdException;
import com.br.kerberus.urd.log.LogException;
import com.br.kerberus.urd.log.LogExecutionTime;
import com.br.kerberus.urd.log.LogMetlhodCall;
import com.br.kerberus.urd.log.LogMetlhodReturn;
import com.br.kerberus.urd.repository.ApplicationRepository;
import com.br.kerberus.urd.resource.ApplicationResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ApplicationService {

    private final ApplicationRepository repository;

    private final ServerService serverService;

    public ApplicationService(ApplicationRepository repository, ServerService serverService) {
        this.repository = repository;
        this.serverService = serverService;
    }

    @LogException
    @LogMetlhodCall
    @LogMetlhodReturn
    @LogExecutionTime
    public Application getApplicationById(Integer id) throws UrdException {

        Optional<Application> application = repository.findById(id);

        if (application.isEmpty()) {
            throw new UrdException(
                    HttpStatus.NOT_FOUND,
                    String.format("Application with id {%s} not found", id),
                    String.format("Application with id {%s} not found. Please enter a valid value for search.", id),
                    501);
        }

        return application.get();
    }

    @LogException
    @LogMetlhodCall
    @LogMetlhodReturn
    @LogExecutionTime
    public Application addApplication(ApplicationResource application) throws UrdException {

        Application app = new Application();
        app.setName(application.getName());
        app.setLogPath(application.getLogPath());
        app.setServer(serverService.getServerById(application.getServerId()));
        app.setCreationDate(new Date());

        return repository.save(app);
    }
}
