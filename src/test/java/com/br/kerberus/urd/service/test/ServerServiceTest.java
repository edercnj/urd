package com.br.kerberus.urd.service.test;

import com.br.kerberus.urd.entity.Server;
import com.br.kerberus.urd.exception.UrdException;
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
	public void serverServiceFindByIdMustReturnOneElement()throws UrdException
	{
		 Server server = service.getServerById(1);
		
		Assert.assertNotNull(server);
	}

	@Test (expected = UrdException.class)
	public void serverServiceFindByIdMustUrdExceptionNotFound()throws UrdException
	{
			Server server = service.getServerById(4);
	}
}
