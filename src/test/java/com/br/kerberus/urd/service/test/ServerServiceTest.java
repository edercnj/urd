package com.br.kerberus.urd.service.test;

import com.br.kerberus.urd.entity.Server;
import com.br.kerberus.urd.exception.ManagedException;
import com.br.kerberus.urd.exception.NoManagedException;
import com.br.kerberus.urd.resource.ServerRequest;
import com.br.kerberus.urd.service.ServerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        service.getServerById(18);
    }

    @Test
    public void serverServiceFindByHostnameMustReturnOneElement() throws ManagedException {
        Server server = service.getServerByHostname("SERVER 1");
        Assert.assertNotNull(server);
    }

    @Test(expected = ManagedException.class)
    public void serverServiceFindByHostnameMusMustManagedExceptionNotFound() throws ManagedException {
        service.getServerByHostname("SERVER 18");
    }

    @Test
    public void serverServiceFindAllServersMustReturnElements() throws ManagedException {
        List<Server> servers = service.findAllServers();
        Assert.assertTrue(servers.size() > 0);
    }

    @Test
    public void serverServiceFindAllServersMustReturnFalseWhenSizeNotEqualsToTheTotalOfElements() throws ManagedException {
        List<Server> servers = service.findAllServers();
        Assert.assertNotEquals(10 , servers.size());
    }

    @Test
    public void serverServiceAddServerMustReturnSameHostname() throws NoManagedException {
        ServerRequest request = new ServerRequest();
        request.setHostname("Server 4");
        Server  server = service.addServer(request);
        Assert.assertEquals(server.getHostname(), request.getHostname());
    }

    @Test
    public void serverServiceRemoveServerByIdMustReturnServerWithIdThree() throws ManagedException {

        Server server = service.removeServerById(3);
        Assert.assertEquals((Integer)3, server.getId());
    }

    @Test(expected = ManagedException.class)
    public void serverServiceRemoveServerByIdMustReturnManagedExceptionNotFoundWhenIdNotFound() throws ManagedException {
        service.removeServerById(5);
    }
}

