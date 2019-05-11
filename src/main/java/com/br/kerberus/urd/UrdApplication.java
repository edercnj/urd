package com.br.kerberus.urd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableAspectJAutoProxy
public class UrdApplication {

	public static String hostname = null;

	public static void main(String[] args) throws UnknownHostException {

		hostname = InetAddress.getLocalHost().getHostName();

		SpringApplication.run(UrdApplication.class, args);
	}
}
