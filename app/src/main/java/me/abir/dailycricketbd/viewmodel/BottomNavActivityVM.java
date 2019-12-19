package me.abir.dailycricketbd.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import me.abir.dailycricketbd.BuildConfig;
import me.abir.dailycricketbd.model.fcm_models.FCMTokenResponseModel;
import me.abir.dailycricketbd.model.fcm_models.FCMTokenSendRequestModel;
import me.abir.dailycricketbd.rest.AppApiInterface;
import me.abir.dailycricketbd.rest.CricRetroClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomNavActivityVM extends ViewModel {

    private static final String TAG = "BottomNavActivityVM";
    private MutableLiveData<Boolean> tokenSentToServerData;

    public MutableLiveData<Boolean> getTokenSentToServerData(String fcmToken, String deviceId) {
        if (tokenSentToServerData == null) {
            tokenSentToServerData = new MutableLiveData<>();
            sendTokenToServer(fcmToken, deviceId);
        }
        return tokenSentToServerData;
    }

    private void sendTokenToServer(String fcmToken, String deviceId) {
        FCMTokenSendRequestModel model = new FCMTokenSendRequestModel();
        model.setAppVersion(BuildConfig.VERSION_NAME);
        model.setPlatform("android");
        model.setToken(fcmToken);
        model.setDeviceId(deviceId);
        //Log.d(TAG, "sendTokenToServer() body: " + model.toString());

        AppApiInterface appApiInterface = CricRetroClient.getInstance().getAppClientFCM().create(
                AppApiInterface.class
        );
        appApiInterface.sendFCMTokenToServer(
                "application/json", "XMLHttpRequest", model
        ).enqueue(new Callback<FCMTokenResponseModel>() {
            @Override
            public void onResponse(
                    Call<FCMTokenResponseModel> call, Response<FCMTokenResponseModel> response
            ) {
                if (response.isSuccessful()) {
                    FCMTokenResponseModel responseModel = response.body();
                    if (responseModel != null && responseModel.isSuccess()) {
                        tokenSentToServerData.setValue(true);
                    } else {
                        tokenSentToServerData.setValue(false);
                    }
                } else {
                    tokenSentToServerData.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<FCMTokenResponseModel> call, Throwable t) {
                tokenSentToServerData.setValue(false);
            }
        });
    }
}
