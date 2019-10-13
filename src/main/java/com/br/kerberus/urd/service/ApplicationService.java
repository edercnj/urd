package com.br.kerberus.urd.service;

import com.br.kerberus.urd.entity.*;
import com.br.kerberus.urd.entity.core.*;
import com.br.kerberus.urd.exception.ManagedException;
import com.br.kerberus.urd.repository.ApplicationRepository;
import com.br.kerberus.urd.resource.ApplicationRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @LogMethodCall (LogLevel = {LogLevel.DEBUG,})
    @LogMetlhodReturn
    @LogExecutionTime
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public Application getApplicationById(Integer id) throws ManagedException {

        Optional<Application> application = repository.findById(id);

        if (application.isEmpty())
            throw new ManagedException(LogError.APPLICATION_NOT_FOUND);

        return application.get();
    }

    @LogException
    @LogMethodCall
    @LogMetlhodReturn
    @LogExecutionTime
    public Application addApplication(ApplicationRequest application) throws ManagedException {

        Application app = new Application();
        app.setName(application.getName());
        app.setLogPath(application.getLogPath());
        app.setServer(serverService.getServerById(application.getServerId()));
        app.setCreationDate(new Date());

        return repository.save(app);
    }
}
