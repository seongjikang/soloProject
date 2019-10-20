package solo.shinhan.com.solo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solo.shinhan.com.solo.data.CustomPreferences;
import solo.shinhan.com.solo.data.DataResult;
import solo.shinhan.com.solo.data.DataResultImpl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solo.shinhan.com.solo.data.DataResult;
import solo.shinhan.com.solo.data.DataResultImpl;


public class LoginActivity extends AppCompatActivity {
	private long pressedTime = 0;

	private LinearLayout mSolLoginBtn;
	private LinearLayout mNoUuid;
	private LinearLayout mSignUpBtn;
	private RelativeLayout mPatternLogin;
	private EditText mEditName;
	private EditText mEditIdNum;

	private final static String CHECK_REGISTER_URL = "http://13.125.12.186/v1/user/search/register";

	private CheckRegister task;

	private String uuid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_common));
		}

		mSolLoginBtn = (LinearLayout) findViewById(R.id.sol_login_btn);
		mNoUuid = findViewById(R.id.ll_no_uuid);
        mSignUpBtn = (LinearLayout)findViewById(R.id.sign_up_btn);
        mEditName = (EditText)findViewById(R.id.et_name);
        mEditIdNum = (EditText)findViewById(R.id.et_id_num);
        mPatternLogin = (RelativeLayout)findViewById(R.id.pattern_rl) ;
        mPatternLogin.setVisibility(View.GONE);


		uuid = CustomPreferences.getString(this, "uuid");

		Log.i("uuid", "uuid : " + uuid);

		showLoginBtn(uuid);

		mSolLoginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getHouseInfo();
			    mSolLoginBtn.setVisibility(View.GONE);
			    mPatternLogin.setVisibility(View.VISIBLE);
				//				joinUser("lina", "1234", "9401072222222", "lina");
                /*
                    임시 USER DATA;
                */
				SoloUser soloUser = new SoloUser(0, "강성지", 30, "M");

				SoloSingleton.getInstance().setSoloUser(soloUser);
				SoloSingleton.getInstance().setSoloUser(soloUser);
				//Intent intent = new Intent(getBaseContext(), SelectActivity.class);
				//startActivity(intent);
				//overridePendingTransition(0, 0);
				//finish();
			}
		});

		mSignUpBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mEditName.getText().toString() == null || mEditName.getText().toString().equals("") || mEditIdNum.getText().toString() == null || mEditIdNum.getText().toString().equals("")) {
					Toast.makeText(getBaseContext(), "이름과 주민등록번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
				} else {
					if (mEditIdNum.getText().toString().length() != 13) {
						Toast.makeText(getBaseContext(), "잘못된 주민등록번호 입니다. 다시입력해주세요.", Toast.LENGTH_SHORT).show();
					} else {
						Intent intent = new Intent(getBaseContext(), JoinActivity.class);
						intent.putExtra("name", mEditName.getText().toString());
						intent.putExtra("id_num", mEditIdNum.getText().toString());
						startActivity(intent);
						overridePendingTransition(0, 0);
					}
				}

			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		showLoginBtn(uuid);
	}

	/**
	 * Uuid 있으면 SOL로 로그인 버튼 / 아니면 회원가입 뷰 보여줌
	 *
	 * @return
	 */
	public void showLoginBtn(String uuid) {
		try {

			JSONObject requestJson = new JSONObject();
			requestJson.put("uuid", uuid);

			task = new CheckRegister();
			task.execute(CHECK_REGISTER_URL, requestJson.toString());

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		if (pressedTime == 0) {
			Toast.makeText(LoginActivity.this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
			pressedTime = System.currentTimeMillis();
		} else {
			int seconds = (int) (System.currentTimeMillis() - pressedTime);

			if (seconds > 2000) {
				Toast.makeText(LoginActivity.this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
				pressedTime = 0;
			} else {
				super.onBackPressed();
				finish();
			}
		}
	}

	private class CheckRegister extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			boolean check = CustomPreferences.getBoolean(getApplicationContext(), "register_check");
			Log.i("register_check", check + "");
			if (CustomPreferences.getBoolean(getApplicationContext(), "register_check")) {
				mSolLoginBtn.setVisibility(View.VISIBLE);
				mNoUuid.setVisibility(View.GONE);
			} else {
				mSolLoginBtn.setVisibility(View.GONE);
				mNoUuid.setVisibility(View.VISIBLE);
			}
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected void onCancelled(String s) {
			super.onCancelled(s);
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected String doInBackground(String... strings) {

			try {
				String check_register_url = strings[0];
				String requestJson = strings[1];
				Log.i("requestJson", requestJson);

				HttpClient http = new DefaultHttpClient();

				HttpPost httpPost = new HttpPost(check_register_url);
				httpPost.setHeader("Accept", "application/json");
				httpPost.setHeader("Connection", "keep-alive");
				httpPost.setHeader("Content-Type", "application/json");

				httpPost.setEntity(new StringEntity(requestJson));

				HttpResponse response = http.execute(httpPost);

				if (response.getStatusLine().getStatusCode() == 200) {
					ResponseHandler<String> handler = new BasicResponseHandler();
					String body = handler.handleResponse(response);
					System.out.println(body);
					return checkRegister(body);
				} else {
					System.out.println("response is error : " + response.getStatusLine().getStatusCode());
					return "fail";
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			return "fail";
		}
	}

	private String checkRegister(String body) {

		JsonParser parser = new JsonParser();
		JsonObject response = (JsonObject) parser.parse(body);

		Log.i("responseBody", response.toString());

		JsonElement element = parser.parse(response.toString());
		String result = element.getAsJsonObject().get("result").getAsString();

		Log.i("result_data", result);

		if (result.equals("able")) {
			//isUuid =false;
			CustomPreferences.setBoolean(this, "register_check", false);
		} else if (result.equals("unable")) {
			//isUuid =true;
			CustomPreferences.setBoolean(this, "register_check", true);
		}

		return null;
	}

	private void joinUser(String name, String uuid, String idNumber, String password) {
		DataResult dataResult = new DataResultImpl();
		dataResult.joinUser(new Callback<JsonObject>() {
			@Override
			public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
				if (response.code() == 200) {

				}
				Log.d("test!!!", response.toString());
			}

			@Override
			public void onFailure(Call<JsonObject> call, Throwable t) {
				Log.d("test!!!", "FAIL!!!");
			}
		}, name, uuid, idNumber, password);
	}

	private void certiUser(String name, String idNum) {
		DataResult dataResult = new DataResultImpl();
		dataResult.certificateUser(new Callback<JsonObject>() {
			@Override
			public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
				Log.d("test!!!", response.toString());
			}

			@Override
			public void onFailure(Call<JsonObject> call, Throwable t) {
				Log.d("test!!!", "FAIL!!!");
			}
		}, "lina", "123");
	}


	/**
	 * 집 리스트 가져오기
	 */
	private void getHouseInfo() {

		DataResult dataResult = new DataResultImpl();
		// 집 리스트 가져오기
		dataResult.getAllHouse(new Callback<JsonObject>() {
			@Override
			public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
				Log.d("testResponse", response.body().toString());

				JsonArray houseList = response.body().getAsJsonArray("houseList");

				ArrayList<HouseInfo> houseInfos = new ArrayList<HouseInfo>();
				HouseInfo houseInfo;

				for (int i = 0; i < houseList.size(); i++) {
					houseInfo = new HouseInfo();

					houseInfo.setHouseNo(houseList.get(i).getAsJsonObject().get("houseNo").getAsInt());
					houseInfo.setHouseName(houseList.get(i).getAsJsonObject().get("houseName").getAsString());
					houseInfo.setAddress(houseList.get(i).getAsJsonObject().get("address").getAsString());
					houseInfo.setAddressDetail(houseList.get(i).getAsJsonObject().get("addressDetail").getAsString());
					houseInfo.setHouseSize(houseList.get(i).getAsJsonObject().get("houseSize").getAsDouble());
//					houseInfo.setHouseView(getBitmapFromURL(houseList.get(i).getAsJsonObject().get("houseViewUrl").getAsString()));
//					houseInfo.setHouseFloorPlan(getBitmapFromURL(houseList.get(i).getAsJsonObject().get("houseFloorPlanUrl").getAsString()));
					houseInfo.setBedroom(houseList.get(i).getAsJsonObject().get("bedroom").getAsInt());
					houseInfo.setRestroom(houseList.get(i).getAsJsonObject().get("restroom").getAsInt());
					houseInfo.setBalcony(houseList.get(i).getAsJsonObject().get("balcony").getAsInt());
					houseInfo.setKitchen(houseList.get(i).getAsJsonObject().get("kitchen").getAsInt());
					houseInfo.setLivingroom(houseList.get(i).getAsJsonObject().get("livingroom").getAsInt());
					houseInfo.setHall(houseList.get(i).getAsJsonObject().get("hall").getAsInt());
					houseInfo.setHouseFloorPlanString(houseList.get(i).getAsJsonObject().get("houseFloorPlanUrl").getAsString());
					houseInfo.setHouseViewString(houseList.get(i).getAsJsonObject().get("houseViewUrl").getAsString());

					houseInfos.add(houseInfo);
				}

				SoloSingleton.getInstance().setHouseInfoList(houseInfos);

				// 액티비티 이동
				SoloUser soloUser = new SoloUser(0, "강성지", 30, "M");

				SoloSingleton.getInstance().setSoloUser(soloUser);
				Intent intent = new Intent(getBaseContext(), SelectActivity.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}

			@Override
			public void onFailure(Call<JsonObject> call, Throwable t) {
				Log.d("testResponse", "ERROR!!");
			}
		});
	}

}
