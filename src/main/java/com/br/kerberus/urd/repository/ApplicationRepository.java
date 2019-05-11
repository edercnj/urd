package com.br.kerberus.urd.repository;

import com.br.kerberus.urd.entity.Application;
import com.br.kerberus.urd.log.LogExecutionTime;
import com.br.kerberus.urd.log.LogMetlhodCall;
import com.br.kerberus.urd.log.LogMetlhodReturn;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface ApplicationRepository extends JpaRepository<Application, Integer>{

    @Override
    @LogMetlhodCall
    @LogMetlhodReturn
    @LogExecutionTime
    <S extends Application> S save(S entity);

    @Override
    @LogMetlhodCall
    @LogMetlhodReturn
    @LogExecutionTime
    Optional<Application> findById(Integer aLong);

    @Override
    @LogMetlhodCall
    @LogMetlhodReturn
    @LogExecutionTime
    <S extends Application> List<S> findAll(Example<S> example);
}
