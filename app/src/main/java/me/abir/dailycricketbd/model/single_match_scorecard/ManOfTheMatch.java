package me.abir.dailycricketbd.model.single_match_scorecard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManOfTheMatch {

    @SerializedName("pid")
    @Expose
    private int pid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("thumb_url")
    @Expose
    private String thumbUrl;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    @Override
    public String toString() {
        return "ManOfTheMatch{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                '}';
    }
}
