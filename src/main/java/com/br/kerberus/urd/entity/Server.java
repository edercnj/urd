package com.br.kerberus.urd.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table( name = "server", indexes = {
		@Index(name = "idx_server_id", columnList = "id"),
		@Index(name = "idx_server_hostname", columnList = "hostname") })
@Proxy(lazy = false)
public class Server {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "server_id_seq")
	@SequenceGenerator( name = "server_id_seq", sequenceName = "server_id_seq", initialValue = 1, allocationSize = 1)
	private Integer id;

	@Column(name = "hostname")
	private String hostname;

	@OneToMany(mappedBy = "server", fetch = FetchType.LAZY)
	@Cascade(value = CascadeType.ALL)
	private List<Application> applications;

	@Column(name = "creation_date")
	private Date creationDate;

	public Integer getId() { return id; }

	public void setId(Integer id) { this.id = id; }

	public String getHostname() { return hostname; }

	public void setHostname(String hostname) { this.hostname = hostname; }

	public List<Application> getApplications() { return applications; }

	public void setApplications(List<Application> applications) { this.applications = applications; }

	public Date getCreationDate() { return creationDate; }

	public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

	@Override
	public String toString() {
		return "Server{" +
				"id=" + id +
				", hostname='" + hostname + '\'' +
				", applications=" + ((applications != null) ? applications : "null") +
				", creationDate=" + ((creationDate != null ? creationDate : "null")) +
				'}';
	}

	public Server(Integer id, String hostname, List<Application> applications, Date creationDate) {
		super();
		this.id = id;
		this.hostname = hostname;
		this.applications = applications;
		this.creationDate = creationDate;
	}

	public Server() {}
}
