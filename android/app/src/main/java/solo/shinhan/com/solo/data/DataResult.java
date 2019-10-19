package solo.shinhan.com.solo.data;

import com.google.gson.JsonObject;

import retrofit2.Callback;

public interface DataResult {
	public void getAllFurniture(Callback<JsonObject> callback);
	public void getAllHouse(Callback<JsonObject> callback);
}
