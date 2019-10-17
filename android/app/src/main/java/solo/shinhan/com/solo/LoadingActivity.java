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
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class LoadingActivity extends Activity {

	ProgressBar progress;

	LoadingListCall task;

	private final static String FURNITURE_LIST_URL = "http://13.125.12.186/v1/furniture/listup";
	private final static String HOUSE_LIST_URL = "http://13.125.12.186/v1/house/listup";

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

		progress = (ProgressBar)findViewById(R.id.progress);

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
					if(i == grantResults.length-1) {
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
		Handler handler = new Handler();
		loadData();
		SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().clear();
	}

	private void loadData() {
		Log.i("loaddata", "loaddata");
		task = new LoadingListCall();

		task.execute(FURNITURE_LIST_URL, HOUSE_LIST_URL);
	}


	private void loadFurnitureInfo(String body) throws JSONException {
		HashMap<String, ArrayList<FurnitureInfo>> furnitureMap = new HashMap<String, ArrayList<FurnitureInfo>>();
		FurnitureInfo furnitureInfo;

		ArrayList<FurnitureInfo> allFurnitureInfos = new ArrayList<FurnitureInfo>();

		ArrayList<FurnitureInfo> sofaInfos = new ArrayList<FurnitureInfo>();
		ArrayList<FurnitureInfo> tableInfos = new ArrayList<FurnitureInfo>();
		ArrayList<FurnitureInfo> bedInfos = new ArrayList<FurnitureInfo>();
		ArrayList<FurnitureInfo> bathroomInfos = new ArrayList<FurnitureInfo>();
		ArrayList<FurnitureInfo> cabinetInfos = new ArrayList<FurnitureInfo>();
		ArrayList<FurnitureInfo> carpetInfos = new ArrayList<FurnitureInfo>();
		ArrayList<FurnitureInfo> chairInfos = new ArrayList<FurnitureInfo>();
		ArrayList<FurnitureInfo> deskInfos = new ArrayList<FurnitureInfo>();
		ArrayList<FurnitureInfo> kitchenInfos = new ArrayList<FurnitureInfo>();
		ArrayList<FurnitureInfo> washerInfos = new ArrayList<FurnitureInfo>();
		ArrayList<FurnitureInfo> etcInfos = new ArrayList<FurnitureInfo>();

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
			furnitureInfo.setFurnitureName(row.getString("furnitureName"));
			furnitureInfo.setPrice(row.getInt("price"));
			allFurnitureInfos.add(furnitureInfo);
		}

		for(int i=0; i<allFurnitureInfos.size(); i++) {
			if(allFurnitureInfos.get(i).getCategory().equals("소파")) {
				sofaInfos.add(allFurnitureInfos.get(i));
			} else if (allFurnitureInfos.get(i).getCategory().equals("테이블")) {
				tableInfos.add(allFurnitureInfos.get(i));
			} else if (allFurnitureInfos.get(i).getCategory().equals("침대")) {
				bedInfos.add(allFurnitureInfos.get(i));
			} else if (allFurnitureInfos.get(i).getCategory().equals("화장실")) {
				bathroomInfos.add(allFurnitureInfos.get(i));
			} else if (allFurnitureInfos.get(i).getCategory().equals("캐비넷")) {
				cabinetInfos.add(allFurnitureInfos.get(i));
			} else if (allFurnitureInfos.get(i).getCategory().equals("카페트")) {
				carpetInfos.add(allFurnitureInfos.get(i));
			} else if (allFurnitureInfos.get(i).getCategory().equals("의자")) {
				chairInfos.add(allFurnitureInfos.get(i));
			} else if (allFurnitureInfos.get(i).getCategory().equals("책상")) {
				deskInfos.add(allFurnitureInfos.get(i));
			} else if (allFurnitureInfos.get(i).getCategory().equals("주방")) {
				kitchenInfos.add(allFurnitureInfos.get(i));
			} else if (allFurnitureInfos.get(i).getCategory().equals("세탁기")) {
				washerInfos.add(allFurnitureInfos.get(i));
			} else if (allFurnitureInfos.get(i).getCategory().equals("기타")) {
				etcInfos.add(allFurnitureInfos.get(i));
			}
		}

		furnitureMap.put("소파", sofaInfos); furnitureMap.put("테이블", tableInfos); furnitureMap.put("침대", bedInfos);
		furnitureMap.put("화장실", bathroomInfos); furnitureMap.put("캐비넷", cabinetInfos); furnitureMap.put("카페트", carpetInfos);
		furnitureMap.put("의자", chairInfos); furnitureMap.put("책상", deskInfos); furnitureMap.put("주방", kitchenInfos);
		furnitureMap.put("세탁기", washerInfos); furnitureMap.put("기타", etcInfos);

		SoloSingleton.getInstance().setFurnitureMap(furnitureMap);
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


	private void loadHouseInfo(String body) throws JSONException {
		ArrayList<HouseInfo> houseInfos = new ArrayList<HouseInfo>();

		HouseInfo houseInfo;

		JSONObject jObject = new JSONObject(body);
		JSONArray jArray = (JSONArray)jObject.get("houseList");

		for(int i=0; i<jArray.length(); i++) {
			JSONObject row = jArray.getJSONObject(i);
			houseInfo = new HouseInfo();
			houseInfo.setHouseNo(row.getInt("houseNo"));
			houseInfo.setHouseName(row.getString("houseName"));
			houseInfo.setAddress(row.getString("address"));
			houseInfo.setAddressDetail(row.getString("addressDetail"));
			houseInfo.setHouseSize(row.getDouble("houseSize"));
			houseInfo.setHouseView(getBitmapFromURL(row.getString("houseViewUrl")));
			houseInfo.setHouseFloorPlan(getBitmapFromURL(row.getString("houseFloorPlanUrl")));
			houseInfo.setBedroom(row.getInt("bedroom"));
			houseInfo.setRestroom(row.getInt("restroom"));
			houseInfo.setBalcony(row.getInt("balcony"));
			houseInfo.setKitchen(row.getInt("kitchen"));
			houseInfo.setLivingroom(row.getInt("livingroom"));
			houseInfo.setHall(row.getInt("hall"));
			houseInfos.add(houseInfo);

		}

		SoloSingleton.getInstance().setHouseInfoList(houseInfos);
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
			overridePendingTransition(0, 0);
			finish();
		}

		@Override
		protected String doInBackground(String... strings) {

			try {
				String furniture_url = strings[0];
				String house_url = strings[1];

				HttpClient http = new DefaultHttpClient();

				HttpPost httpPost = new HttpPost(furniture_url);

				HttpResponse response = http.execute(httpPost);

				String body = EntityUtils.toString(response.getEntity());
				Log.i("body", "body :" + body);
				loadFurnitureInfo(body);

				httpPost = new HttpPost(house_url);
				response = http.execute(httpPost);
				body = EntityUtils.toString(response.getEntity());

				loadHouseInfo(body);

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
}
