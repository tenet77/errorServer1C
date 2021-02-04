package httpServer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigMessage {

    @JsonProperty("configHash")
    private String configHash;
    @JsonProperty("configName")
    private String configName;
    @JsonProperty("configVersion")
    private String configVersion;
    @JsonProperty("appStackHash")
    private String appStackHash;
    @JsonProperty("clientStackHash")
    private String clientStackHash;
    @JsonProperty("platformType")
    private String platformType;
    @JsonProperty("appName")
    private String appName;
    @JsonProperty("appVersion")
    private String appVersion;
    @JsonProperty("platformInterfaceLanguageCode")
    private String platformInterfaceLanguageCode;
    @JsonProperty("configurationInterfaceLanguageCode")
    private String configurationInterfaceLanguageCode;

    @Override
    public String toString() {

        String sb = this.configHash +
                this.configName;
        return sb;

    }
}
