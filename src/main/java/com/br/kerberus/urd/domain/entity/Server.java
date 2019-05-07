package com.br.kerberus.urd.domain.entity;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "server", indexes = {
		@Index(name = "server_id_seq", columnList = "id"),
		@Index(name = "idx_server_hostname", columnList = "hostname") })
@Proxy(lazy = false)
public class Server {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	@SequenceGenerator(name = "server_id_seq", sequenceName = "server_id_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "hostname")
	private String hostname;

	@OneToMany(mappedBy = "server", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Application> applications;

	@Column(name = "creation_date")
	private Date creationDate;

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getHostname() { return hostname; }

	public void setHostname(String hostname) { this.hostname = hostname; }

	public List<Application> getApplications() { return applications; }

	public void setApplications(List<Application> applications) { this.applications = applications; }

	public Date getCreationDate() { return creationDate; }

	public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

	public Server(Long id, String hostname, List<Application> applications, Date creationDate) {
		super();
		this.id = id;
		this.hostname = hostname;
		this.applications = applications;
		this.creationDate = creationDate;
	}
	
	public Server() {}

}
