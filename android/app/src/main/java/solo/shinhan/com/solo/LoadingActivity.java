package solo.shinhan.com.solo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solo.shinhan.com.solo.data.CustomPreferences;
import solo.shinhan.com.solo.data.DataResult;
import solo.shinhan.com.solo.data.DataResultImpl;

public class LoadingActivity extends Activity {

	private final static String FURNITURE_LIST_URL = "http://13.125.12.186/v1/furniture/listup";

	ProgressBar progress;
	LoadingListCall task;

	private ArrayList<FurnitureInfo> sofaInfos = new ArrayList<FurnitureInfo>();
	private ArrayList<FurnitureInfo> tableInfos = new ArrayList<FurnitureInfo>();
	private ArrayList<FurnitureInfo> bedInfos = new ArrayList<FurnitureInfo>();
	private ArrayList<FurnitureInfo> bathroomInfos = new ArrayList<FurnitureInfo>();
	private ArrayList<FurnitureInfo> cabinetInfos = new ArrayList<FurnitureInfo>();
	private ArrayList<FurnitureInfo> carpetInfos = new ArrayList<FurnitureInfo>();
	private ArrayList<FurnitureInfo> chairInfos = new ArrayList<FurnitureInfo>();
	private ArrayList<FurnitureInfo> deskInfos = new ArrayList<FurnitureInfo>();
	private ArrayList<FurnitureInfo> kitchenInfos = new ArrayList<FurnitureInfo>();
	private ArrayList<FurnitureInfo> washerInfos = new ArrayList<FurnitureInfo>();
	private ArrayList<FurnitureInfo> etcInfos = new ArrayList<FurnitureInfo>();


	private String[] permission_list = {
			Manifest.permission.WRITE_EXTERNAL_STORAGE
			, Manifest.permission.CAMERA
			, Manifest.permission.READ_EXTERNAL_STORAGE
	};
	private static final int FROM_PERMISSION = 1111;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_common));
		}

		progress = (ProgressBar) findViewById(R.id.progress);

		checkPermission();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	private void checkPermission() {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
			return;

		for (String permission : permission_list) {
			//권한 허용 여부를 확인한다.
			int chk = checkCallingOrSelfPermission(permission);

			if (chk == PackageManager.PERMISSION_DENIED) {
				//권한 허용을여부를 확인하는 창을 띄운다
				requestPermissions(permission_list, FROM_PERMISSION);
				return;
			}
		}
		startLoading();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == FROM_PERMISSION) {
			for (int i = 0; i < grantResults.length; i++) {
				//허용됬다면
				if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
					if (i == grantResults.length - 1) {
						startLoading();
					}
				} else {
					Toast.makeText(getApplicationContext(), "설정 > 앱 권한 > 저장공간에서 권한을 허용해주세요.", Toast.LENGTH_LONG).show();
					finish();
				}
			}
		}
	}

	private void startLoading() {
		loadData();
		String uuid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
		CustomPreferences.setString(this, "uuid",uuid);

		SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().clear();
	}

	/**
	 * 가구 리스트, 집 리스트 가져옴
	 */
	private void loadData() {

		task = new LoadingListCall();
		task.execute(FURNITURE_LIST_URL);

		Log.i("loaddata", "loaddata");
/*
		DataResult dataResult = new DataResultImpl();
		// 가구 리스트 가져오기
		dataResult.getAllFurniture(new Callback<JsonObject>() {
			@Override
			public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
				Log.d("testResponse", response.body().toString());
				HashMap<String, ArrayList<FurnitureInfo>> furnitureMap = new HashMap<String, ArrayList<FurnitureInfo>>();

				JsonArray furnitureList = response.body().getAsJsonArray("furnitureList");

				ArrayList<FurnitureInfo> allFurnitureInfos = new ArrayList<FurnitureInfo>();
				FurnitureInfo furnitureInfo = null;

				for (int i = 0; i < furnitureList.size(); i++) {
					furnitureInfo = new FurnitureInfo();
					furnitureInfo.setCategory(furnitureList.get(i).getAsJsonObject().get("category").getAsString());
					furnitureInfo.setBrand(furnitureList.get(i).getAsJsonObject().get("brand").getAsString());
					furnitureInfo.setModel(furnitureList.get(i).getAsJsonObject().get("model").getAsString());
					furnitureInfo.setFurnitureNo(furnitureList.get(i).getAsJsonObject().get("furnitureNo").getAsInt());
					furnitureInfo.setDirection(furnitureList.get(i).getAsJsonObject().get("direction").getAsInt());
//					furnitureInfo.setFurnitureImage(getBitmapFromURL(furnitureList.get(i).getAsJsonObject().get("furnitureUrl").getAsString()));
					furnitureInfo.setFurnitureImageString(furnitureList.get(i).getAsJsonObject().get("furnitureUrl").getAsString());
					furnitureInfo.setFurnitureName(furnitureList.get(i).getAsJsonObject().get("furnitureName").getAsString());
					furnitureInfo.setPrice(furnitureList.get(i).getAsJsonObject().get("price").getAsInt());

					allFurnitureInfos.add(furnitureInfo);
					setCategoryList(furnitureInfo);
				}

				furnitureMap.put("소파", sofaInfos);
				furnitureMap.put("테이블", tableInfos);
				furnitureMap.put("침대", bedInfos);
				furnitureMap.put("화장실", bathroomInfos);
				furnitureMap.put("캐비넷", cabinetInfos);
				furnitureMap.put("카페트", carpetInfos);
				furnitureMap.put("의자", chairInfos);
				furnitureMap.put("책상", deskInfos);
				furnitureMap.put("주방", kitchenInfos);
				furnitureMap.put("세탁기", washerInfos);
				furnitureMap.put("기타", etcInfos);

				SoloSingleton.getInstance().setFurnitureMap(furnitureMap);

				// 가구 끝나면 집리스트 가져오기
				getHouseInfo();
			}

			@Override
			public void onFailure(Call<JsonObject> call, Throwable t) {
				Log.d("testResponse", "ERROR!!");
			}
		});*/
	}

	private void setCategoryList(FurnitureInfo furnitureInfo) {
		String category = furnitureInfo.getCategory();
		switch (category) {
			case "소파":
				sofaInfos.add(furnitureInfo);
				break;
			case "테이블":
				tableInfos.add(furnitureInfo);
				break;
			case "침대":
				bedInfos.add(furnitureInfo);
				break;
			case "화장실":
				bathroomInfos.add(furnitureInfo);
				break;
			case "캐비넷":
				cabinetInfos.add(furnitureInfo);
				break;
			case "카페트":
				carpetInfos.add(furnitureInfo);
				break;
			case "의자":
				chairInfos.add(furnitureInfo);
				break;
			case "책상":
				deskInfos.add(furnitureInfo);
				break;
			case "주방":
				kitchenInfos.add(furnitureInfo);
				break;
			case "세탁기":
				washerInfos.add(furnitureInfo);
				break;
			case "기타":
				etcInfos.add(furnitureInfo);
				break;
		}
	}

	private Bitmap getBitmapFromURL(String strImageURL) {
		Bitmap imgBitmap = null;

		try {
			URL url = new URL(strImageURL);
			URLConnection conn = url.openConnection();
			conn.connect();

			int nSize = conn.getContentLength();
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream(), nSize);
			imgBitmap = BitmapFactory.decodeStream(bis);

			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgBitmap;
	}

	@Override
	protected void onPause() {
		super.onPause();
		overridePendingTransition(0, 0);
	}

	private class LoadingListCall extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			Intent intent = new Intent(getBaseContext(), LoginActivity.class);
			startActivity(intent);
//			overridePendingTransition(0, 0);
			finish();
		}

		@Override
		protected String doInBackground(String... strings) {

			try {
				String furniture_url = strings[0];

				HttpClient http = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(furniture_url);
				HttpResponse response = http.execute(httpPost);

				String body = EntityUtils.toString(response.getEntity());
				Log.i("body", "body :" + body);
				loadFurnitureInfo(body);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}
	}
	private void loadFurnitureInfo(String body) throws JSONException {
		HashMap<String, ArrayList<FurnitureInfo>> furnitureMap = new HashMap<String, ArrayList<FurnitureInfo>>();
		FurnitureInfo furnitureInfo;

		ArrayList<FurnitureInfo> allFurnitureInfos = new ArrayList<FurnitureInfo>();

		JSONObject jObject = new JSONObject(body);
		JSONArray jArray = (JSONArray)jObject.get("furnitureList");

		for(int i=0; i<jArray.length(); i++) {
			JSONObject row = jArray.getJSONObject(i);
			furnitureInfo = new FurnitureInfo();
			furnitureInfo.setCategory(row.getString("category"));
			furnitureInfo.setBrand(row.getString("brand"));
			furnitureInfo.setModel(row.getString("model"));
			furnitureInfo.setFurnitureNo(row.getInt("furnitureNo"));
			furnitureInfo.setDirection(row.getInt("direction"));
			furnitureInfo.setFurnitureImage(getBitmapFromURL(row.getString("furnitureUrl")));
			furnitureInfo.setFurnitureImageString(row.getString("furnitureUrl"));
			furnitureInfo.setFurnitureName(row.getString("furnitureName"));
			furnitureInfo.setPrice(row.getInt("price"));
			allFurnitureInfos.add(furnitureInfo);
			setCategoryList(furnitureInfo);
		}

		furnitureMap.put("소파", sofaInfos);
		furnitureMap.put("테이블", tableInfos);
		furnitureMap.put("침대", bedInfos);
		furnitureMap.put("화장실", bathroomInfos);
		furnitureMap.put("캐비넷", cabinetInfos);
		furnitureMap.put("카페트", carpetInfos);
		furnitureMap.put("의자", chairInfos);
		furnitureMap.put("책상", deskInfos);
		furnitureMap.put("주방", kitchenInfos);
		furnitureMap.put("세탁기", washerInfos);
		furnitureMap.put("기타", etcInfos);

		SoloSingleton.getInstance().setFurnitureMap(furnitureMap);

//		getHouseInfo();
	}
}
