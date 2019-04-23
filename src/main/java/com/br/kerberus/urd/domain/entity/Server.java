package com.br.kerberus.urd.domain.entity;

import javax.persistence.*;


@Table(name = "Server", schema = "kerberus", indexes = {@Index(name ="SERVER_INDEX_ID", columnList = "Id")})
public class Server {

	@Id
	private Long id;

	@Column(name = "hostname")
	private String hostname;

	public String getHostname() { return hostname; }

	public void setHostname(String hostname) { this.hostname = hostname; }

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

}
