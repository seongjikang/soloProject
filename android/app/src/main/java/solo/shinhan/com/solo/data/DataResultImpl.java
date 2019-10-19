package solo.shinhan.com.solo.data;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import solo.shinhan.com.solo.data.network.ApiServiceFactory;

public class DataResultImpl implements DataResult{
	@Override
	public void getAllFurniture(Callback<JsonObject> callback) {
		ApiServiceFactory apiServiceFactory = new ApiServiceFactory();
		Call<JsonObject> call = apiServiceFactory.makeApiService().getFurnitureList();

		call.enqueue(callback);
		return;
	}

	@Override
	public void getAllHouse(Callback<JsonObject> callback) {
		ApiServiceFactory apiServiceFactory = new ApiServiceFactory();
		Call<JsonObject> call = apiServiceFactory.makeApiService().getHouseList();

		call.enqueue(callback);
		return;
	}
}
