package com.br.kerberus.urd.service;

import com.br.kerberus.urd.domain.entity.Server;
import com.br.kerberus.urd.domain.resource.ServerResponse;

public class MapServerResource implements MapEntities<ServerResponse, Server> {

	@Override
	public final ServerResponse map(ServerResponse serverResponse, Server server) {

		serverResponse.setId(server.getId());
		serverResponse.setHostname(server.getHostname());
		serverResponse.setCreationDate(server.getCreationDate());
		serverResponse.setApplications(server.getApplications());

		return serverResponse;
	}

}
