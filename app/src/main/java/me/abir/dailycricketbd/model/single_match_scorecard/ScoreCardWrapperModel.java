package me.abir.dailycricketbd.model.single_match_scorecard;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ScoreCardWrapperModel extends ExpandableGroup {

    private ScoreCardWrapperKeyValue headerValue;
    private List<Object> objects;

    public ScoreCardWrapperModel(ScoreCardWrapperKeyValue headerValue, List<Object> items) {
        super(headerValue.getTitle(), items);
        this.headerValue = headerValue;
        this.objects = items;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    public ScoreCardWrapperKeyValue getHeaderValue() {
        return headerValue;
    }

    public void setHeaderValue(ScoreCardWrapperKeyValue headerValue) {
        this.headerValue = headerValue;
    }
}
