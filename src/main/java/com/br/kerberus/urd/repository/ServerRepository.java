package com.br.kerberus.urd.repository;

import com.br.kerberus.urd.entity.Server;
import com.br.kerberus.urd.entity.core.LogExecutionTime;
import com.br.kerberus.urd.entity.core.LogMethodCall;
import com.br.kerberus.urd.entity.core.LogMetlhodReturn;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServerRepository extends JpaRepository<Server, Integer>{

    @Override
    @LogMethodCall
    @LogMetlhodReturn
    @LogExecutionTime
    Optional<Server> findById(Integer aLong);

    @LogMethodCall
    @LogMetlhodReturn
    @LogExecutionTime
    Optional<Server> findByHostnameIsLike(String name);

    @Override
    @LogMethodCall
    @LogMetlhodReturn
    @LogExecutionTime
    <S extends Server> S save(S entity);

    @Override
    @LogMethodCall
    @LogMetlhodReturn
    @LogExecutionTime
    <S extends Server> List<S> findAll(Example<S> example);

    @Override
    @LogMethodCall
    @LogMetlhodReturn
    @LogExecutionTime
    void delete(Server entity);
}
