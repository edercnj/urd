package com.br.kerberus.urd.domain.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "server", indexes = {
		@Index(name = "server_id_seq", columnList = "id"),
		@Index(name = "idx_server_hostname", columnList = "hostname") })
public class Server {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	@SequenceGenerator(name = "server_id_seq", sequenceName = "server_id_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "hostname")
	private String hostname;

	@OneToMany(mappedBy = "server", targetEntity = Application.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Application> applications;

	@Column(name = "creationDate")
	private Date creationDate;

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getHostname() { return hostname; }

	public void setHostname(String hostname) { this.hostname = hostname; }

	public List<Application> getApplications() { return applications; }

	public void setApplications(List<Application> applications) { this.applications = applications; }

	public Date getCreationDate() { return creationDate; }

	public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

}
