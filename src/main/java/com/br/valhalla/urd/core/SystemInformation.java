package com.br.valhalla.urd.core;

public class SystemInformation {
    private int availableProcessorsJVM;
    private long freeMemoryJVM;
    private long maximumMemoryJVM;
    private long totalMemoryAvailableJVM;
    private String osName;
    private String osVersion;
    private String osArchitecture;
    private String javaVersion;

    public int getAvailableProcessorsJVM() { return availableProcessorsJVM; }

    private void setAvailableProcessorsJVM(int availableProcessorsJVM) { this.availableProcessorsJVM = availableProcessorsJVM; }

    public long getFreeMemoryJVM() { return freeMemoryJVM; }

    private void setFreeMemoryJVM(long freeMemoryJVM) { this.freeMemoryJVM = freeMemoryJVM; }

    public long getMaximumMemoryJVM() { return maximumMemoryJVM; }

    private void setMaximumMemoryJVM(long maximumMemoryJVM) { this.maximumMemoryJVM = maximumMemoryJVM; }

    public long getTotalMemoryAvailableJVM() { return totalMemoryAvailableJVM; }

    private void setTotalMemoryAvailableJVM(long totalMemoryAvailableJVM) { this.totalMemoryAvailableJVM = totalMemoryAvailableJVM; }

    public String getOsName() { return osName; }

    public void setOsName(String osName) { this.osName = osName; }

    public String getOsVersion() { return osVersion; }

    private void setOsVersion(String osVersion) { this.osVersion = osVersion; }

    public String getOsArchitecture() { return osArchitecture; }

    private void setOsArchitecture(String osArchitecture) { this.osArchitecture = osArchitecture; }

    public String getJavaVersion() { return javaVersion; }

    private void setJavaVersion(String javaVersion) { this.javaVersion = javaVersion; }

    public SystemInformation() {
        this.setAvailableProcessorsJVM(Runtime.getRuntime().availableProcessors());
        this.setFreeMemoryJVM(Runtime.getRuntime().freeMemory() / (1024 * 1024));
        this.setMaximumMemoryJVM(Runtime.getRuntime().maxMemory() / (1024 * 1024));
        this.setTotalMemoryAvailableJVM(Runtime.getRuntime().totalMemory() / (1024 * 1024));
        this.setOsName(System.getProperty("os.name"));
        this.setOsVersion(System.getProperty("os.version"));
        this.setOsArchitecture(System.getProperty("os.arch"));
        this.setJavaVersion(System.getProperty("java.version"));
    }

    @Override
    public String toString() {
        return "{" +
                "availableProcessorsJVM:" + getAvailableProcessorsJVM() +
                ", freeMemoryJVM:" + getFreeMemoryJVM() + "MB" +
                ", maximumMemoryJVM:" + getMaximumMemoryJVM() + "MB" +
                ", totalMemoryAvailableJVM:" + getTotalMemoryAvailableJVM() + "MB" +
                ", osName:'" + getOsName() + '\'' +
                ", osVersion:'" + getOsVersion() + '\'' +
                ", osArchitecture:'" + getOsArchitecture() + '\'' +
                ", javaVersion:'" + getJavaVersion() + '\'' +
                '}';
    }
}