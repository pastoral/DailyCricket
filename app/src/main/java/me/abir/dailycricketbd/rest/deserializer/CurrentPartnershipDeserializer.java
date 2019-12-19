package me.abir.dailycricketbd.rest.deserializer;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import me.abir.dailycricketbd.model.single_live_match.CurrentPartnership;
import me.abir.dailycricketbd.model.single_live_match.LiveMatchBatsman_;

/**
 * This custom deserialization is done because the 'current_partnership' field
 * sometimes come as an object and sometime it comes as empty jsonArray
 * so to get the actual value when provided and to avoid crash, this deserialization is implemented
 */
public class CurrentPartnershipDeserializer implements JsonDeserializer<CurrentPartnership> {


    private static final String TAG = CurrentPartnershipDeserializer.class.getSimpleName();

    @Override
    public CurrentPartnership deserialize(
            JsonElement json, Type typeOfT, JsonDeserializationContext context
    ) throws JsonParseException {
        Log.d(TAG, "deserialize: " + json);
        CurrentPartnership partnership = new CurrentPartnership();
        if (json.isJsonObject()) {

            List<LiveMatchBatsman_> batsmenList = new ArrayList<>();
            JsonObject object = json.getAsJsonObject();
            JsonArray batsmenArray = object.get("batsmen").getAsJsonArray();

            for (int i = 0; i < batsmenArray.size(); i++) {
                LiveMatchBatsman_ batsman = new LiveMatchBatsman_();
                JsonObject batsmanObject = (JsonObject) batsmenArray.get(i);
                batsman.setRuns(batsmanObject.get("runs").getAsInt());
                batsman.setBalls(batsmanObject.get("balls").getAsInt());
                batsman.setBatsmanId(batsmanObject.get("batsman_id").getAsInt());
                batsmenList.add(batsman);
            }

            partnership.setRuns(object.get("runs").getAsInt());
            partnership.setBalls(object.get("balls").getAsInt());
            partnership.setOvers(object.get("overs").getAsDouble());
            partnership.setBatsmen(batsmenList);
            Log.w(TAG, "deserialize() " + partnership.toString());
        } else if (json.isJsonArray()) {
            Log.e(TAG, "deserialize() Empty Array!");
        }
        return partnership;
    }
}
