package solo.shinhan.com.solo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solo.shinhan.com.solo.data.DataResult;
import solo.shinhan.com.solo.data.DataResultImpl;

public class LoadingActivity extends Activity {

	ProgressBar progress;

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
		loadData();
		SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().clear();
	}

	/**
	 * 가구 리스트, 집 리스트 가져옴
	 */
	private void loadData() {
		Log.i("loaddata", "loaddata");

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

				for(int i = 0; i < furnitureList.size(); i++) {
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

				furnitureMap.put("소파", sofaInfos); furnitureMap.put("테이블", tableInfos); furnitureMap.put("침대", bedInfos);
				furnitureMap.put("화장실", bathroomInfos); furnitureMap.put("캐비넷", cabinetInfos); furnitureMap.put("카페트", carpetInfos);
				furnitureMap.put("의자", chairInfos); furnitureMap.put("책상", deskInfos); furnitureMap.put("주방", kitchenInfos);
				furnitureMap.put("세탁기", washerInfos); furnitureMap.put("기타", etcInfos);

				SoloSingleton.getInstance().setFurnitureMap(furnitureMap);

				// 가구 끝나면 집리스트 가져오기
				getHouseInfo();
			}

			@Override
			public void onFailure(Call<JsonObject> call, Throwable t) {
				Log.d("testResponse", "ERROR!!");
			}
		});
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

				for(int i = 0; i < houseList.size(); i++) {
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
				progress.setVisibility(View.GONE);
				// 로그인 액티비티로 이동
				goNextActivity();
			}

			@Override
			public void onFailure(Call<JsonObject> call, Throwable t) {
				Log.d("testResponse", "ERROR!!");
			}
		});
	}

	/**
	 * 로그인 액티비티로 이동
	 */
	private void goNextActivity() {
		Intent intent = new Intent(getBaseContext(), LoginActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	protected void onPause() {
		super.onPause();
		overridePendingTransition(0, 0);
	}

}
