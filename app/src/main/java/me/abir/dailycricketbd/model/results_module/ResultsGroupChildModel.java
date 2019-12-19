package me.abir.dailycricketbd.model.results_module;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Abir on 30-May-18.
 */
public class ResultsGroupChildModel extends ExpandableGroup {

    private String headerValue;
    private List<ResultsModelWrapper> results;

    public ResultsGroupChildModel(String title, List<ResultsModelWrapper> results) {
        super(title, results);
        this.headerValue = title;
        this.results = results;
    }

    public String getHeaderValue() {
        return headerValue;
    }

    public List<ResultsModelWrapper> getResults() {
        return results;
    }


}
