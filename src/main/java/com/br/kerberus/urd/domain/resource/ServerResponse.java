package com.br.kerberus.urd.domain.resource;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.br.kerberus.urd.domain.entity.Application;
import com.br.kerberus.urd.domain.entity.Server;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ServerResponse {

	private Long id;

	private String hostname;

	private Date creationDate;
	
	private List<Application> applications;
	
	
	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getHostname() { return hostname; }

	public void setHostname(String hostname) { this.hostname = hostname; }

	public Date getCreationDate() { return creationDate; }

	public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

	public List<Application> getApplications() { return applications; }

	public void setApplications(List<Application> applications) { this.applications = applications; }

	public ServerResponse(Server server) {
		
		setId(server.getId());
		setHostname(server.getHostname());
		setCreationDate(new Date());
		setApplications(server.getApplications());
	
	}
	
	public ServerResponse(Optional<Server> server) {
		
		setId(server.get().getId());
		setHostname(server.get().getHostname());
		setCreationDate(new Date());
		setApplications(server.get().getApplications());
	
	}

}
