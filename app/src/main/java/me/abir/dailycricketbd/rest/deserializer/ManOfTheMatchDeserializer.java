package me.abir.dailycricketbd.rest.deserializer;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import me.abir.dailycricketbd.model.single_match_scorecard.ManOfTheMatch;

/**
 * This custom deserialization is done because the 'man_of_the_match' field
 * sometimes come as an object and sometime it comes as empty string
 * so to get the actual value when provided and to avoid crash, this deserialization is implemented
 */
public class ManOfTheMatchDeserializer implements JsonDeserializer<ManOfTheMatch> {

    private static final String TAG = ManOfTheMatchDeserializer.class.getSimpleName();

    @Override
    public ManOfTheMatch deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Log.d(TAG, "deserialize: " + json);
        ManOfTheMatch man = new ManOfTheMatch();
        if (json.isJsonObject()) {
            JsonObject object = json.getAsJsonObject();
            man.setName(object.get("name").getAsString());
            man.setPid(object.get("pid").getAsInt());
            man.setThumbUrl(object.get("thumb_url").getAsString());
            Log.w(TAG, "deserialize() " + man.toString());
        } else if (json.isJsonPrimitive()) {
            Log.e(TAG, "deserialize() String!");
            man.setName("");
        }
        return man;
    }
}
