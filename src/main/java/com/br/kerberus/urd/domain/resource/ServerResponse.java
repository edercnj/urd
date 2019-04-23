package com.br.kerberus.urd.domain.resource;

import java.util.Optional;

import com.br.kerberus.urd.domain.entity.Server;

public class ServerResponse extends Server {

	public ServerResponse(Optional<Server> server) {

		if (server.isPresent()) {
			this.setId(server.get().getId());
			this.setHostname(server.get().getHostname());
		}
	}

	@Override
	public String getHostname() { return super.getHostname(); }

	@Override
	public void setHostname(String hostname) { super.setHostname(hostname); }

	@Override
	public Long getId() { return super.getId(); }

	@Override
	public void setId(Long id) { super.setId(id); }

	@Override
	public String toString() {
		return "ServerResponse [getHostname()=" + getHostname() + ", getId()=" + getId() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
