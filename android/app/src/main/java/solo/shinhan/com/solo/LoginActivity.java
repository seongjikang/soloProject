package solo.shinhan.com.solo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
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
import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.andrognito.patternlockview.utils.ResourceUtils;
import com.andrognito.rxpatternlockview.RxPatternLockView;
import com.andrognito.rxpatternlockview.events.PatternLockCompleteEvent;
import com.andrognito.rxpatternlockview.events.PatternLockCompoundEvent;
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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

import java.net.URLEncoder;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import io.reactivex.functions.Consumer;
import solo.shinhan.com.solo.data.CustomPreferences;
import solo.shinhan.com.solo.security.AES256Util;

public class LoginActivity extends AppCompatActivity {
	private long pressedTime = 0;

	private LinearLayout mNoUuid;
	private LinearLayout mSignUpBtn;
	private RelativeLayout mPatternLogin;
	private EditText mEditName;
	private EditText mEditIdNum;

	private boolean isLogin;

	private Button mCancelBtn;
	private TextView mPatternMessage;
	private PatternLockView mPatternLockView;

	private final static String FIRST_PATTERN_INPUT = "패턴을 입력해주세요.";
	private final static String ERROR_PATTERN_INPUT = "패턴이 일치하지 않습니다.다시 입력해 주세요.";

	private final static String CHECK_REGISTER_URL = "http://13.125.12.186/v1/user/search/register";
	private final static String LOGIN_URL = "http://13.125.12.186/v1/user/login";

	private CheckRegister checkRegisterTask;
	private LoginTask loginTask;

	private String uuid;

	private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
		@Override
		public void onStarted() {
			Log.d(getClass().getName(), "Pattern drawing started");
		}

		@Override
		public void onProgress(List<PatternLockView.Dot> progressPattern) {
			Log.d(getClass().getName(), "Pattern progress: " +
					PatternLockUtils.patternToString(mPatternLockView, progressPattern));
		}

		@Override
		public void onComplete(List<PatternLockView.Dot> pattern) {
			Log.d(getClass().getName(), "Pattern complete: " +
					PatternLockUtils.patternToString(mPatternLockView, pattern));
		}

		@Override
		public void onCleared() {
			Log.d(getClass().getName(), "Pattern has been cleared");
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_common));
		}

		mNoUuid = findViewById(R.id.ll_no_uuid);
		mSignUpBtn = (LinearLayout) findViewById(R.id.sign_up_btn);
		mEditName = (EditText) findViewById(R.id.et_name);
		mEditIdNum = (EditText) findViewById(R.id.et_id_num);
		mPatternLogin = (RelativeLayout) findViewById(R.id.pattern_rl);
		mCancelBtn = (Button) findViewById(R.id.cancel_btn);
		mPatternLogin.setVisibility(View.GONE);

		mPatternMessage = (TextView) findViewById(R.id.pattern_message);
		mPatternMessage.setText(FIRST_PATTERN_INPUT);

		mPatternLockView = (PatternLockView) findViewById(R.id.patter_view);
		mPatternLockView.setDotCount(3);
		mPatternLockView.setDotNormalSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_size));
		mPatternLockView.setDotSelectedSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_selected_size));
		mPatternLockView.setPathWidth((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_path_width));
		mPatternLockView.setAspectRatioEnabled(true);
		mPatternLockView.setAspectRatio(PatternLockView.AspectRatio.ASPECT_RATIO_HEIGHT_BIAS);
		mPatternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
		mPatternLockView.setDotAnimationDuration(150);
		mPatternLockView.setPathEndAnimationDuration(100);
		mPatternLockView.setCorrectStateColor(ResourceUtils.getColor(this, R.color.white));
		mPatternLockView.setInStealthMode(false);
		mPatternLockView.setTactileFeedbackEnabled(true);
		mPatternLockView.setInputEnabled(true);
		mPatternLockView.addPatternLockListener(mPatternLockViewListener);

		RxPatternLockView.patternComplete(mPatternLockView)
				.subscribe(new Consumer<PatternLockCompleteEvent>() {
					@Override
					public void accept(PatternLockCompleteEvent patternLockCompleteEvent) throws Exception {
						Log.d(getClass().getName(), "Complete: " + patternLockCompleteEvent.getPattern().toString());
					}
				});

		RxPatternLockView.patternChanges(mPatternLockView)
				.subscribe(new Consumer<PatternLockCompoundEvent>() {
					@Override
					public void accept(PatternLockCompoundEvent event) throws Exception {
						if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_STARTED) {
							Log.d(getClass().getName(), "Pattern drawing started");
						} else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_PROGRESS) {
							Log.d(getClass().getName(), "Pattern progress: " +
									PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
						} else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_COMPLETE) {
							Log.d(getClass().getName(), "Pattern complete: " +
									PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
							String inputPattern = PatternLockUtils.patternToString(mPatternLockView, event.getPattern());

							AES256Util aes256Util = new AES256Util();
							String password = aes256Util.aesEncode(inputPattern);
							String uuid = CustomPreferences.getString(getApplicationContext(), "uuid");
							JSONObject requestJson = new JSONObject();
							requestJson.put("uuid", uuid);
							requestJson.put("password", password);

							SoloUser soloUser = new SoloUser(0, "강성지", 30, "M");

							SoloSingleton.getInstance().setSoloUser(soloUser);
							SoloSingleton.getInstance().setSoloUser(soloUser);

							loginTask = new LoginTask();
							loginTask.execute(LOGIN_URL, requestJson.toString());


						} else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_CLEARED) {
							Log.d(getClass().getName(), "Pattern has been cleared");
						}
					}
				});

		uuid = CustomPreferences.getString(this, "uuid");

		Log.i("uuid", "uuid : " + uuid);

		showLoginBtn(uuid);

		mSignUpBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mEditName.getText().toString() == null || mEditName.getText().toString().equals("") || mEditIdNum.getText().toString() == null || mEditIdNum.getText().toString().equals("")) {
					Toast.makeText(getBaseContext(), "이름과 주민등록번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
				} else {
					if (mEditIdNum.getText().toString().length() != 13) {
						Toast.makeText(getBaseContext(), "잘못된 주민등록번호 입니다. 다시입력해주세요.", Toast.LENGTH_SHORT).show();
					} else {
						AES256Util aes256Util = null;
						try {
							aes256Util = new AES256Util();
							try {
								String idNumEncode = aes256Util.aesEncode(mEditIdNum.getText().toString());
								CustomPreferences.setString(getApplicationContext(), "id_num", idNumEncode);
							} catch (NoSuchAlgorithmException e) {
							} catch (NoSuchPaddingException e) {
							} catch (InvalidKeyException e) {
							} catch (InvalidAlgorithmParameterException e) {
							} catch (IllegalBlockSizeException e) {
							} catch (BadPaddingException e) {
							}
						} catch (UnsupportedEncodingException e) {
						}
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

			checkRegisterTask = new CheckRegister();
			checkRegisterTask.execute(CHECK_REGISTER_URL, requestJson.toString());

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

	private class LoginTask extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			if (isLogin) {
				Log.i("login", "success");
				getHouseInfo();
			} else {
				Log.i("login", "fail");
				mPatternMessage.setText(ERROR_PATTERN_INPUT);
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
				String login_url = strings[0];
				String requestJson = strings[1];
				Log.i("requestJson", requestJson);

				HttpClient http = new DefaultHttpClient();

				HttpPost httpPost = new HttpPost(login_url);
				httpPost.setHeader("Accept", "application/json");
				httpPost.setHeader("Connection", "keep-alive");
				httpPost.setHeader("Content-Type", "application/json");

				httpPost.setEntity(new StringEntity(requestJson));

				HttpResponse response = http.execute(httpPost);

				if (response.getStatusLine().getStatusCode() == 200) {
					ResponseHandler<String> handler = new BasicResponseHandler();
					String body = handler.handleResponse(response);
					System.out.println(body);
					return login(body);
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

	private String login(String body) {

		JsonParser parser = new JsonParser();
		JsonObject response = (JsonObject) parser.parse(body);

		Log.i("responseBody", response.toString());

		JsonElement element = parser.parse(response.toString());
		String result = element.getAsJsonObject().get("result").getAsString();

		Log.i("result_data", result);

		if (result.equals("success")) {
			//CustomPreferences.setBoolean(this,"is_login",true);
			Log.i("result_data", "success");
			isLogin = true;
		} else if (result.equals("fail")) {
			// CustomPreferences.setBoolean(this,"is_login",false);
			Log.i("result_data", "fail");
			isLogin = false;
		}

		return null;
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
				mPatternLogin.setVisibility(View.VISIBLE);
				mNoUuid.setVisibility(View.GONE);
			} else {
				mPatternLogin.setVisibility(View.GONE);
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
