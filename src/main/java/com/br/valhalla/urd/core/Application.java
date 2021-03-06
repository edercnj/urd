package com.br.valhalla.urd.core;

import org.springframework.beans.factory.annotation.Value;

public class Application {

    private long pid;
    @Value("${spring.application.name}")
    private String name;
    private String user;
    private String commandLine;
    private String version;

    public long getPid() { return pid; }

    private void setPid(long pid) { this.pid = pid; }

    public String getName() { return name; }

    public String getUser() { return user; }

    private void setUser(String user) { this.user = user; }

    public String getCommandLine() { return commandLine; }

    private void setCommandLine(String commandLine) { this.commandLine = commandLine; }

    public String getVersion() { return version; }

    private void setVersion(String version) { this.version = version; }

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
        return "{" +
                "pid:" + getPid() +
                ", name:'" + getName() + '\'' +
                ", user:'" + getUser() + '\'' +
                ", version:'" + getVersion() + '\'' +
                ", commandLine:'" + getCommandLine() + '\'' +
                '}';
    }
}