package com.br.kerberus.urd.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table( name = "application", indexes = {
        @Index(name = "idx_application_id", columnList = "Id"),
        @Index(name = "idx_application_name", columnList = "name")})
@Proxy(lazy = false)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="application_id_seq")
    @SequenceGenerator(name = "application_id_seq", sequenceName = "application_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "log_path")
    private String logPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id", nullable = false, foreignKey = @ForeignKey(name = "fk_application_server_id"))
    private Server server;

    @Column(name = "creation_date")
    private Date creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;
        Application that = (Application) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                logPath.equals(that.logPath) &&
                server.equals(that.server) &&
                creationDate.equals(that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, logPath, server, creationDate);
    }

    @Override
    public String toString() {
        return "Application{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", logPath:'" + logPath + '\'' +
                ", server:" + server +
                ", creationDate:" + creationDate +
                '}';
    }
}
