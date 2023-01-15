package ru.home.startupsettings;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.nio.file.Path;

public final class StartupSettings {
    public enum FileFormat {
        FILE_FORMAT_TXT("text"),
        FILE_FORMAT_JSON("json");

        private final String formatString;

        FileFormat(String formatString) {
            this.formatString = formatString;
        }

        @JsonValue
        public String getFormatString() {
            return formatString;
        }
    }

    @JsonProperty("load")
    private StartupSettingsLoad startupSettingsLoad;

    @JsonProperty("save")
    private StartupSettingsSave startupSettingsSave;

    @JsonProperty("log")
    private StartupSettingsLog startupSettingsLog;

    public StartupSettingsLoad getStartupSettingsLoad() {
        return startupSettingsLoad;
    }

    public void setStartupSettingsLoad(StartupSettingsLoad startupSettingsLoad) {
        this.startupSettingsLoad = startupSettingsLoad;
    }

    public StartupSettingsSave getStartupSettingsSave() {
        return startupSettingsSave;
    }

    public void setStartupSettingsSave(StartupSettingsSave startupSettingsSave) {
        this.startupSettingsSave = startupSettingsSave;
    }

    public StartupSettingsLog getStartupSettingsLog() {
        return startupSettingsLog;
    }

    public void setStartupSettingsLog(StartupSettingsLog startupSettingsLog) {
        this.startupSettingsLog = startupSettingsLog;
    }
}
