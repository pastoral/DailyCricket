package me.abir.dailycricketbd.interfaces;

import java.util.List;

import me.abir.dailycricketbd.model.results_module.ResultsGroupChildModel;
import me.abir.dailycricketbd.model.results_module.ResultsModelWrapper;

/**
 * Created by Abir on 24-Nov-17.
 */

public interface OnResultResponseListener {
    void onCompletedMatchResponse(List<ResultsGroupChildModel> resultModels);
}
