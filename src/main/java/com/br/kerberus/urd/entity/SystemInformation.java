package com.br.kerberus.urd.entity;

public class SystemInformation {
    private int availableProcessors;
    private long freeMemory;
    private long maximumMemory;
    private long totalMemoryAvailable;
    private String osName;
    private String osVersion;
    private String osArchitecture;
    private String javaVersion;

    public int getAvailableProcessors() { return availableProcessors; }

    private void setAvailableProcessors(int availableProcessors) { this.availableProcessors = availableProcessors; }

    public long getFreeMemory() { return freeMemory; }

    private void setFreeMemory(long freeMemory) { this.freeMemory = freeMemory; }

    public long getMaximumMemory() { return maximumMemory; }

    private void setMaximumMemory(long maximumMemory) { this.maximumMemory = maximumMemory; }

    public long getTotalMemoryAvailable() { return totalMemoryAvailable; }

    private void setTotalMemoryAvailable(long totalMemoryAvailable) { this.totalMemoryAvailable = totalMemoryAvailable; }

    public String getOsName() { return osName; }

    public void setOsName(String osName) { this.osName = osName; }

    public String getOsVersion() { return osVersion; }

    private void setOsVersion(String osVersion) { this.osVersion = osVersion; }

    public String getOsArchitecture() { return osArchitecture; }

    private void setOsArchitecture(String osArchitecture) { this.osArchitecture = osArchitecture; }

    public String getJavaVersion() { return javaVersion; }

    private void setJavaVersion(String javaVersion) { this.javaVersion = javaVersion; }

    public SystemInformation() {
        this.setAvailableProcessors(Runtime.getRuntime().availableProcessors());
        this.setFreeMemory(Runtime.getRuntime().freeMemory());
        this.setMaximumMemory(Runtime.getRuntime().maxMemory());
        this.setTotalMemoryAvailable(Runtime.getRuntime().totalMemory());
        this.setOsVersion(System.getProperty("os.name"));
        this.setOsVersion(System.getProperty("os.version"));
        this.setOsArchitecture(System.getProperty("os.arch"));
        this.setJavaVersion(System.getProperty("java.version"));
    }

    @Override
    public String toString() {
        return "SystemInformation{" +
                "availableProcessors:" + getAvailableProcessors() +
                ", freeMemory:" + getFreeMemory() +
                ", maximumMemory:" + getMaximumMemory() +
                ", totalMemoryAvailable:" + getTotalMemoryAvailable() +
                ", osName:'" + getOsName() + '\'' +
                ", osVersion:'" + getOsVersion() + '\'' +
                ", osArchitecture:'" + getOsArchitecture() + '\'' +
                ", javaVersion:'" + getJavaVersion() + '\'' +
                '}';
    }
}