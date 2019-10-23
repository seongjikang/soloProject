package solo.shinhan.com.solo.data;

import com.google.gson.JsonObject;

import retrofit2.Callback;

public interface DataResult {
	public void getAllFurniture(Callback<JsonObject> callback);
	public void getAllHouse(Callback<JsonObject> callback);
	public void getUserList(Callback<JsonObject> callback);
	public void joinUser(Callback<JsonObject> callback, String name, String uuid, String idNumber, String password);
	public void searchRegistUser(Callback<JsonObject> callback, String uuid);
	public void certificateUser(Callback<JsonObject> callback, String name, String idNumber);
	public void getResultOfLoanApply(Callback<JsonObject> callback, String idNumber);
}
