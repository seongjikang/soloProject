package solo.shinhan.com.solo.data.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

	public static final String URL = "http://13.125.12.186";

	@POST("/v1/furniture/listup")
	public Call<JsonObject> getFurnitureList();

	@POST("/v1/house/listup")
	public Call<JsonObject> getHouseList();
}
