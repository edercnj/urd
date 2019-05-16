package com.br.kerberus.urd.resource;

import com.br.kerberus.urd.entity.Application;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApplicationResponse {

    private Integer id;

    @NotEmpty
    @NotBlank
    @NotNull
    private String name;

    @NotEmpty
    @NotBlank
    @NotNull
    private String logPath;

    @NotNull
    private Integer serverId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-YYYY HH:mm:ss")
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

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public ApplicationResponse(Application application) {

        this.id = application.getId();
        this.serverId = application.getServer().getId();
        this.creationDate = application.getCreationDate();
        this.logPath = application.getLogPath();
        this.name = application.getName();
    }
    public ApplicationResponse() {}
}
