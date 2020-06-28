package com.br.kerberus.urd.core;

import org.springframework.beans.factory.annotation.Value;

public class Application {

    private long pid;
    @Value("${spring.application.name}")
    private String name;
    private String user;
    private String commandLine;

    public long getPid() { return pid; }

    public void setPid(long pid) { this.pid = pid; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getUser() { return user; }

    public void setUser(String user) { this.user = user; }

    public String getCommandLine() { return commandLine; }

    public void setCommandLine(String commandLine) { this.commandLine = commandLine; }

    public Application() {
        setPid(ProcessHandle.current().pid());
        if (ProcessHandle.current().info().user().isPresent()) {
            setUser(ProcessHandle.current().info().user().get());
        }
        if (ProcessHandle.current().info().commandLine().isPresent()) {
            setCommandLine(ProcessHandle.current().info().commandLine().get());
        }
    }

    @Override
    public String toString() {
        return "Application{" +
                "pid=" + getPid() +
                ", name='" + getName() + '\'' +
                ", user='" + getUser() + '\'' +
                ", commandLine='" + getCommandLine() + '\'' +
                '}';
    }
}

