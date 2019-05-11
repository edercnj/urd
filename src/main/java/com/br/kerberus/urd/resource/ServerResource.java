package com.br.kerberus.urd.resource;

import com.br.kerberus.urd.entity.Application;
import com.br.kerberus.urd.entity.Server;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@JsonInclude(value = Include.NON_NULL)
public class ServerResource {

	private Long id;

	@NotEmpty
	@NotBlank
	@NotNull
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

	public ServerResource(Server server) {
		
		setId(server.getId());
		setHostname(server.getHostname());
		setCreationDate(new Date());
		setApplications(server.getApplications());
	
	}
	public ServerResource() {}
}
