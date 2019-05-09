package com.br.kerberus.urd.entity;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import java.util.Date;

@Entity
@Table(name = "application", indexes = {
		@Index(name = "SERVICE_APPLICATION_INDEX_ID", columnList = "Id"),
		@Index(name = "idx_application_name", columnList = "name") })
@Proxy(lazy = false)
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	@SequenceGenerator(name = "application_id_seq", sequenceName = "application_id_seq", initialValue = 1, allocationSize = 1)

	private Long id;

	@JoinColumn(name = "name")
	private String name;

	@JoinColumn(name = "log_path")
	private String logPath;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "server_id", nullable = false, foreignKey = @ForeignKey(name = "FK_aplication_server_id"))
	private Server server;

	@Column(name = "creation_date")
	private Date creationDate;

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getLogPath() { return logPath; }

	public void setLogPath(String logPath) { this.logPath = logPath; }

	public Server getServer() { return server; }

	public void setServer(Server server) { this.server = server; }

	public Date getCreationDate() { return creationDate; }

	public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

}
