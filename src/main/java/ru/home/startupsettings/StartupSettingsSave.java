package ru.home.startupsettings;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StartupSettingsSave {
    @JsonProperty("enabled")
    private Boolean enabled;

    @JsonProperty("fileName")
    private String filename;

    @JsonProperty("format")
    private StartupSettings.FileFormat fileFormat;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public StartupSettings.FileFormat getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(StartupSettings.FileFormat fileFormat) {
        this.fileFormat = fileFormat;
    }
}
