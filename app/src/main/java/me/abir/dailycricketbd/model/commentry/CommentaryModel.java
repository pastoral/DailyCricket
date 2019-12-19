package me.abir.dailycricketbd.model.commentry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentaryModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private CommentaryResponse response;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("api_version")
    @Expose
    private String apiVersion;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CommentaryResponse getResponse() {
        return response;
    }

    public void setResponse(CommentaryResponse response) {
        this.response = response;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }
}