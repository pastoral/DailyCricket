package me.abir.dailycricketbd.model.video_moule;

import me.abir.dailycricketbd.model.video_module_v2.DataItem;

/**
 * Created by Abir on 06-Nov-17.
 */

public class WrapperVideoModel {
    private DataItem object;
    private String ytVideoId;

    public String getYtVideoId() {
        return ytVideoId;
    }

    public void setYtVideoId(String ytVideoId) {
        this.ytVideoId = ytVideoId;
    }

    public DataItem getObject() {
        return object;
    }

    public void setObject(DataItem object) {
        this.object = object;
    }
}
