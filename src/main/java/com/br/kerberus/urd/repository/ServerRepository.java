package com.br.kerberus.urd.repository;

import com.br.kerberus.urd.entity.Server;
import com.br.kerberus.urd.log.LogExecutionTime;
import com.br.kerberus.urd.log.LogMetlhodCall;
import com.br.kerberus.urd.log.LogMetlhodReturn;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServerRepository extends JpaRepository<Server, Integer>{

    @Override
    @LogMetlhodCall
    @LogMetlhodReturn
    @LogExecutionTime
    Optional<Server> findById(Integer aLong);

    @Override
    @LogMetlhodCall
    @LogMetlhodReturn
    @LogExecutionTime
    <S extends Server> S save(S entity);

    @Override
    @LogMetlhodCall
    @LogMetlhodReturn
    @LogExecutionTime
    <S extends Server> List<S> findAll(Example<S> example);

    @Override
    @LogMetlhodCall
    @LogMetlhodReturn
    @LogExecutionTime
    void delete(Server entity);
}
