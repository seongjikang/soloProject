package solo.shinhan.com.solo.data.network;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

	public static final String URL = "http://13.125.12.186";

	// 가구 조회
	@POST("/v1/furniture/listup")
	public Call<JsonObject> getFurnitureList();

	// 집 조회
	@POST("/v1/house/listup")
	public Call<JsonObject> getHouseList();

	// 유저 전체 조회
	@POST("/v1/user/join")
	public Call<JsonObject> getUserList();

	// 유저 가입
	@Headers({ "Content-Type: application/json;charset=UTF-8"})
	@POST("/v1/user/join")
	public Call<JsonObject> joinUser(@Body JsonObject body);

	// 유저 등록 가능 여부
	@Headers({ "Content-Type: application/json;charset=UTF-8"})
	@POST("/v1/user/search/register")
	public Call<JsonObject> searchRegistUser(@Body JsonObject body);

	// 유저 인증
	@Headers({ "Content-Type: application/json;charset=UTF-8"})
	@POST("/v1/user/search/certificate")
	public Call<JsonObject> certificateUser(@Body JsonObject body);

}
