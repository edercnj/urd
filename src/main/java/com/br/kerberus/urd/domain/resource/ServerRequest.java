package com.br.kerberus.urd.domain.resource;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ServerRequest {

	
	private Long id;

	@NonNull
	private String hostname;

	public String getHostname() { return hostname; }

	public void setHostname(String hostname) { this.hostname = hostname; }

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

}
