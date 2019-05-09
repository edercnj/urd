package com.br.kerberus.urd.resource;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.br.kerberus.urd.entity.Application;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ServerRequest {


	@NotEmpty
	private String hostname;
	
	private List<Application> applications;

	public String getHostname() { return hostname; }

	public void setHostname(String hostname) { this.hostname = hostname; }

	public List<Application> getApplications() { return applications; }

	public void setApplications(List<Application> applications) { this.applications = applications; }

	
}
