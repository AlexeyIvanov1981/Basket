package ru.home.startupsettings;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StartupSettingsLog {
    @JsonProperty("enabled")
    private Boolean enabled;

    @JsonProperty("fileName")
    private String filename;

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
}
