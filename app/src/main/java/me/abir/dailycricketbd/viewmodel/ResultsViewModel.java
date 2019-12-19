package me.abir.dailycricketbd.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import me.abir.dailycricketbd.model.results_module.ResultsGroupChildModel;
import me.abir.dailycricketbd.model.results_module.ResultsModel;
import me.abir.dailycricketbd.rest.CricBdApiInterface;
import me.abir.dailycricketbd.rest.CricRetroClient;
import me.abir.dailycricketbd.util.CricResponseParser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Abir on 06-Jun-18.
 */
public class ResultsViewModel extends ViewModel {
    private static final String TAG = "ResultsViewModel";
    private MutableLiveData<List<ResultsGroupChildModel>> results;

    public MutableLiveData<List<ResultsGroupChildModel>> getResults(String token) {
        if (results == null || results.getValue() == null) {
            results = new MutableLiveData<>();
            loadResults(token);
        }
        return results;
    }

    private void loadResults(String token) {
        CricBdApiInterface apiInterface = CricRetroClient.getInstance().getCricketClient().create(
                CricBdApiInterface.class
        );

        apiInterface.getCompletedMatchesInfo(token, 1).enqueue(new Callback<ResultsModel>() {
            @Override
            public void onResponse(Call<ResultsModel> call, Response<ResultsModel> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" +
                        response + "]");
                if (response.isSuccessful()) {
                    List<ResultsGroupChildModel> values =
                            CricResponseParser.parseScoreData(response, true);
                    results.postValue(values);
                } else {
                    results.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResultsModel> call, Throwable t) {
                Log.e(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                results.postValue(null);
            }
        });
    }
}
