package solo.shinhan.com.solo.data;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import solo.shinhan.com.solo.data.network.ApiServiceFactory;

public class DataResultImpl implements DataResult {
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

	@Override
	public void getUserList(Callback<JsonObject> callback) {
		ApiServiceFactory apiServiceFactory = new ApiServiceFactory();
		Call<JsonObject> call = apiServiceFactory.makeApiService().getUserList();

		call.enqueue(callback);
		return;
	}

	@Override
	public void joinUser(Callback<JsonObject> callback, String name, String uuid, String idNumber, String password) {
		ApiServiceFactory apiServiceFactory = new ApiServiceFactory();
		JsonObject param = new JsonObject();
		param.addProperty("name", name);
		param.addProperty("uuid", uuid);
		param.addProperty("id_number", idNumber);
		param.addProperty("password", password);

		Call<JsonObject> call = apiServiceFactory.makeApiService().joinUser(param);

		call.enqueue(callback);
		return;
	}

	@Override
	public void searchRegistUser(Callback<JsonObject> callback, String uuid) {
		ApiServiceFactory apiServiceFactory = new ApiServiceFactory();
		JsonObject param = new JsonObject();
		param.addProperty("uuid", uuid);

		Call<JsonObject> call = apiServiceFactory.makeApiService().searchRegistUser(param);

		call.enqueue(callback);
		return;
	}

	@Override
	public void certificateUser(Callback<JsonObject> callback, String name, String idNumber) {
		ApiServiceFactory apiServiceFactory = new ApiServiceFactory();
		JsonObject param = new JsonObject();
		param.addProperty("name", name);
		param.addProperty("id_number", idNumber);


		Call<JsonObject> call = apiServiceFactory.makeApiService().certificateUser(param);

		call.enqueue(callback);
		return;
	}
}
