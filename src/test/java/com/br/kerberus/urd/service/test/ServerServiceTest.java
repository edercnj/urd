package com.br.kerberus.urd.service.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.kerberus.urd.domain.entity.Server;
import com.br.kerberus.urd.exception.UrdException;
import com.br.kerberus.urd.service.ServerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
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
