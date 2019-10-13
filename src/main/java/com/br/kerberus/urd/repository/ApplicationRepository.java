package com.br.kerberus.urd.repository;

import com.br.kerberus.urd.entity.Application;
import com.br.kerberus.urd.entity.core.LogException;
import com.br.kerberus.urd.entity.core.LogExecutionTime;
import com.br.kerberus.urd.entity.core.LogMethodCall;
import com.br.kerberus.urd.entity.core.LogMetlhodReturn;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    @Override
    @LogMethodCall
    @LogMetlhodReturn
    @LogExecutionTime
    @LogException
    <S extends Application> S save(S entity);

    @Override
    @LogMethodCall
    @LogMetlhodReturn
    @LogExecutionTime
    @LogException
    Optional<Application> findById(Integer aLong);

    @Override
    @LogMethodCall
    @LogMetlhodReturn
    @LogExecutionTime
    @LogException
    <S extends Application> List<S> findAll(Example<S> example);
}
