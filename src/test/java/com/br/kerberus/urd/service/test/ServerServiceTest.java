package com.br.kerberus.urd.service.test;

import com.br.kerberus.urd.domain.entity.Server;
import com.br.kerberus.urd.exception.UrdException;
import com.br.kerberus.urd.service.ServerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerServiceTest {

	@Autowired
	ServerService service;

	@Test
	public void serverService_findById_Must_Return_1_elements() throws UrdException
	{
		Optional<Server> server = service.getServerById(1L);
		
		Assert.assertTrue(server.isPresent());
	}

}
