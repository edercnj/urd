package com.br.kerberus.urd.service.test;

import com.br.kerberus.urd.entity.Server;
import com.br.kerberus.urd.exception.ManagedException;
import com.br.kerberus.urd.service.ServerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ServerServiceTest {

    @Autowired
    ServerService service;

    @Test
    public void serverServiceFindByIdMustReturnOneElement() throws ManagedException {
        Server server = service.getServerById(1);
        Assert.assertNotNull(server);

    }

    @Test(expected = ManagedException.class)
    public void serverServiceFindByIdMustManagedExceptionNotFound() throws ManagedException {
        service.getServerById(4);
    }
}
