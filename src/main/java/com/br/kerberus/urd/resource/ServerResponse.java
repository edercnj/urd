package com.br.kerberus.urd.resource;

import com.br.kerberus.urd.entity.Server;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonInclude(value = Include.NON_NULL)
public class ServerResponse {

	private Integer id;

	@NotEmpty
	@NotBlank
	@NotNull
	private String hostname;

	private Date creationDate;

	
	public Integer getId() { return id; }

	public void setId(Integer id) { this.id = id; }

	public String getHostname() { return hostname; }

	public void setHostname(String hostname) { this.hostname = hostname; }

	public Date getCreationDate() { return creationDate; }

	public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

	
	public ServerResponse(Server server) {
		
		setId(server.getId());
		setHostname(server.getHostname());
		setCreationDate(new Date());
	
	}

	public ServerResponse() {}
}
