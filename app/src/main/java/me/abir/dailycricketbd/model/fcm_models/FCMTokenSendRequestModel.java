package me.abir.dailycricketbd.model.fcm_models;

import com.google.gson.annotations.SerializedName;


public class FCMTokenSendRequestModel {

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("app_version")
    private String appVersion;

    @SerializedName("platform")
    private String platform;

    @SerializedName("token")
    private String token;

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return
                "FCMTokenSendRequestModel{" +
                        "device_id = '" + deviceId + '\'' +
                        ",app_version = '" + appVersion + '\'' +
                        ",platform = '" + platform + '\'' +
                        ",token = '" + token + '\'' +
                        "}";
    }
}