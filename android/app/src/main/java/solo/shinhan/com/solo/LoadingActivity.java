package solo.shinhan.com.solo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class LoadingActivity extends Activity {
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
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(getBaseContext(), LoginActivity.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		}, 2000);
	}

	private void loadData() {
		Log.i("loaddata", "loaddata");
		loadHouseInfo();
		loadFurnitureInfo();
	}

	private void loadFurnitureInfo() {
		HashMap<String, ArrayList<FurnitureInfo>> furnitureMap = new HashMap<String, ArrayList<FurnitureInfo>>();
		ArrayList<FurnitureInfo> furnitureInfos;
		FurnitureInfo furnitureInfo;

		furnitureInfos = new ArrayList<FurnitureInfo>();

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("소파");
		furnitureInfo.setBrand("이케아");
		furnitureInfo.setModel("IK1SKSJSKSKJ");
		furnitureInfo.setFurnitureNo(0);
		furnitureInfo.setDirection(1);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.sofa1_1));
		furnitureInfo.setFurnitureName("이케아 1인용 원형 소파");
		furnitureInfo.setPrice(200000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("소파");
		furnitureInfo.setBrand("이케아");
		furnitureInfo.setModel("IK1SKSJSKSKJ");
		furnitureInfo.setFurnitureNo(0);
		furnitureInfo.setDirection(2);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.sofa1_2));
		furnitureInfo.setFurnitureName("이케아 1인용 원형 소파");
		furnitureInfo.setPrice(200000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("소파");
		furnitureInfo.setBrand("이케아");
		furnitureInfo.setModel("IK1SKSJSKSKJ");
		furnitureInfo.setFurnitureNo(0);
		furnitureInfo.setDirection(3);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.sofa1_3));
		furnitureInfo.setFurnitureName("이케아 1인용 원형 소파");
		furnitureInfo.setPrice(200000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("소파");
		furnitureInfo.setBrand("이케아");
		furnitureInfo.setModel("IK1SKSJSKSKJ");
		furnitureInfo.setFurnitureNo(0);
		furnitureInfo.setDirection(4);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.sofa1_4));
		furnitureInfo.setFurnitureName("이케아 1인용 원형 소파");
		furnitureInfo.setPrice(200000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("소파");
		furnitureInfo.setBrand("마티노 가구");
		furnitureInfo.setModel("MT11KSJSK223");
		furnitureInfo.setFurnitureNo(1);
		furnitureInfo.setDirection(1);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.sofa2_1));
		furnitureInfo.setFurnitureName("마티노가구 로코코 3인용 소파");
		furnitureInfo.setPrice(310000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("소파");
		furnitureInfo.setBrand("마티노 가구");
		furnitureInfo.setModel("MT11KSJSK223");
		furnitureInfo.setFurnitureNo(1);
		furnitureInfo.setDirection(2);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.sofa2_2));
		furnitureInfo.setFurnitureName("마티노가구 로코코 3인용 소파");
		furnitureInfo.setPrice(310000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("소파");
		furnitureInfo.setBrand("마티노 가구");
		furnitureInfo.setModel("MT11KSJSK223");
		furnitureInfo.setFurnitureNo(1);
		furnitureInfo.setDirection(3);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.sofa2_3));
		furnitureInfo.setFurnitureName("마티노가구 로코코 3인용 소파");
		furnitureInfo.setPrice(310000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("소파");
		furnitureInfo.setBrand("마티노 가구");
		furnitureInfo.setModel("MT11KSJSK223");
		furnitureInfo.setFurnitureNo(1);
		furnitureInfo.setDirection(4);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.sofa2_4));
		furnitureInfo.setFurnitureName("마티노가구 로코코 3인용 소파");
		furnitureInfo.setPrice(310000);
		furnitureInfos.add(furnitureInfo);

		furnitureMap.put("소파", furnitureInfos);

		furnitureInfos = new ArrayList<FurnitureInfo>();

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("테이블");
		furnitureInfo.setBrand("이케아");
		furnitureInfo.setModel("IK1SI2ISIAOO");
		furnitureInfo.setFurnitureNo(0);
		furnitureInfo.setDirection(1);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.table1_1));
		furnitureInfo.setFurnitureName("이케아 신상 원형 테이블");
		furnitureInfo.setPrice(1000000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("테이블");
		furnitureInfo.setBrand("이케아");
		furnitureInfo.setModel("IK1SI2ISIAOO");
		furnitureInfo.setFurnitureNo(0);
		furnitureInfo.setDirection(2);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.table1_2));
		furnitureInfo.setFurnitureName("이케아 신상 원형 테이블");
		furnitureInfo.setPrice(1000000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("테이블");
		furnitureInfo.setBrand("이케아");
		furnitureInfo.setModel("IK1SI2ISIAOO");
		furnitureInfo.setFurnitureNo(0);
		furnitureInfo.setDirection(3);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.table1_3));
		furnitureInfo.setFurnitureName("이케아 신상 원형 테이블");
		furnitureInfo.setPrice(1000000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("테이블");
		furnitureInfo.setBrand("이케아");
		furnitureInfo.setModel("IK1SI2ISIAOO");
		furnitureInfo.setFurnitureNo(0);
		furnitureInfo.setDirection(4);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.table1_4));
		furnitureInfo.setFurnitureName("이케아 신상 원형 테이블");
		furnitureInfo.setPrice(1000000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("테이블");
		furnitureInfo.setBrand("세움디자인");
		furnitureInfo.setModel("SE12KSJS33KJ");
		furnitureInfo.setFurnitureNo(1);
		furnitureInfo.setDirection(1);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.table2_1));
		furnitureInfo.setFurnitureName("이쁜 사각 테이블");
		furnitureInfo.setPrice(800000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("테이블");
		furnitureInfo.setBrand("세움디자인");
		furnitureInfo.setModel("SE12KSJS33KJ");
		furnitureInfo.setFurnitureNo(1);
		furnitureInfo.setDirection(2);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.table2_2));
		furnitureInfo.setFurnitureName("이쁜 사각 테이블");
		furnitureInfo.setPrice(800000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("테이블");
		furnitureInfo.setBrand("세움디자인");
		furnitureInfo.setModel("SE12KSJS33KJ");
		furnitureInfo.setFurnitureNo(1);
		furnitureInfo.setDirection(3);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.table2_3));
		furnitureInfo.setFurnitureName("이쁜 사각 테이블");
		furnitureInfo.setPrice(800000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("테이블");
		furnitureInfo.setBrand("세움디자인");
		furnitureInfo.setModel("SE12KSJS33KJ");
		furnitureInfo.setFurnitureNo(1);
		furnitureInfo.setDirection(4);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.table2_4));
		furnitureInfo.setFurnitureName("이쁜 사각 테이블");
		furnitureInfo.setPrice(800000);
		furnitureInfos.add(furnitureInfo);

		furnitureMap.put("테이블", furnitureInfos);

		furnitureInfos = new ArrayList<FurnitureInfo>();

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("침대");
		furnitureInfo.setBrand("에이스");
		furnitureInfo.setModel("ACE1SKS22SKJ");
		furnitureInfo.setFurnitureNo(0);
		furnitureInfo.setDirection(1);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.bed1_1));
		furnitureInfo.setFurnitureName("엄청 편한 침대 싱글");
		furnitureInfo.setPrice(1300000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("침대");
		furnitureInfo.setBrand("에이스");
		furnitureInfo.setModel("ACE1SKS22SKJ");
		furnitureInfo.setFurnitureNo(0);
		furnitureInfo.setDirection(2);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.bed1_2));
		furnitureInfo.setFurnitureName("엄청 편한 침대 싱글");
		furnitureInfo.setPrice(1300000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("침대");
		furnitureInfo.setBrand("에이스");
		furnitureInfo.setModel("ACE1SKS22SKJ");
		furnitureInfo.setFurnitureNo(0);
		furnitureInfo.setDirection(3);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.bed1_3));
		furnitureInfo.setFurnitureName("엄청 편한 침대 싱글");
		furnitureInfo.setPrice(1300000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("침대");
		furnitureInfo.setBrand("에이스");
		furnitureInfo.setModel("ACE1SKS22SKJ");
		furnitureInfo.setFurnitureNo(0);
		furnitureInfo.setDirection(4);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.bed1_4));
		furnitureInfo.setFurnitureName("엄청 편한 침대 싱글");
		furnitureInfo.setPrice(1300000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("침대");
		furnitureInfo.setBrand("장수돌침대");
		furnitureInfo.setModel("JS1BBSJ4828");
		furnitureInfo.setFurnitureNo(1);
		furnitureInfo.setDirection(1);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.bed2_1));
		furnitureInfo.setFurnitureName("장수돌침대 퀸");
		furnitureInfo.setPrice(1800000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("침대");
		furnitureInfo.setBrand("장수돌침대");
		furnitureInfo.setModel("JS1BBSJ4828");
		furnitureInfo.setFurnitureNo(1);
		furnitureInfo.setDirection(2);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.bed2_2));
		furnitureInfo.setFurnitureName("장수돌침대 퀸");
		furnitureInfo.setPrice(1800000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("침대");
		furnitureInfo.setBrand("장수돌침대");
		furnitureInfo.setModel("JS1BBSJ4828");
		furnitureInfo.setFurnitureNo(1);
		furnitureInfo.setDirection(3);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.bed2_3));
		furnitureInfo.setFurnitureName("장수돌침대 퀸");
		furnitureInfo.setPrice(1800000);
		furnitureInfos.add(furnitureInfo);

		furnitureInfo = new FurnitureInfo();
		furnitureInfo.setCategory("침대");
		furnitureInfo.setBrand("장수돌침대");
		furnitureInfo.setModel("JS1BBSJ4828");
		furnitureInfo.setFurnitureNo(1);
		furnitureInfo.setDirection(4);
		furnitureInfo.setFurnitureImage(BitmapFactory.decodeResource(getResources(), R.drawable.bed2_4));
		furnitureInfo.setFurnitureName("장수돌침대 퀸");
		furnitureInfo.setPrice(1800000);
		furnitureInfos.add(furnitureInfo);

		furnitureMap.put("침대", furnitureInfos);

		SoloSingleton.getInstance().setFurnitureMap(furnitureMap);
	}

	private void loadHouseInfo() {
		ArrayList<HouseInfo> houseInfos = new ArrayList<HouseInfo>();

		HouseInfo houseInfo;
        /*
        for(int i =0; i<3;i++) {

            houseInfo = new HouseInfo();
            houseInfo.setHouseNo(i);
            houseInfo.setHouseName("충무로 자이");
            houseInfo.setAddress("필동 3가");
            houseInfo.setAddressDetail("102동 1402호");
            houseInfo.setHouseSize(55);
            houseInfo.setHouseView(BitmapFactory.decodeResource(getResources(), R.drawable.default_house));
            houseInfo.setHouseFloorPlan(BitmapFactory.decodeResource(getResources(), R.drawable.default_floor_plan));
            houseInfo.setBedroom(3);
            houseInfo.setRestroom(1);
            houseInfo.setBalcony(1);
            houseInfo.setKitchen(1);
            houseInfo.setLivingroom(1);
            houseInfo.setHall(1);
            houseInfos.add(houseInfo);
        }*/

		houseInfo = new HouseInfo();
		houseInfo.setHouseNo(0);
		houseInfo.setHouseName("충무로 자이");
		houseInfo.setAddress("필동 3가");
		houseInfo.setAddressDetail("102동 1402호");
		houseInfo.setHouseSize(55);
		houseInfo.setHouseView(BitmapFactory.decodeResource(getResources(), R.drawable.house_view1));
		houseInfo.setHouseFloorPlan(BitmapFactory.decodeResource(getResources(), R.drawable.default_floor_plan));
		houseInfo.setBedroom(3);
		houseInfo.setRestroom(1);
		houseInfo.setBalcony(1);
		houseInfo.setKitchen(1);
		houseInfo.setLivingroom(1);
		houseInfo.setHall(1);
		houseInfos.add(houseInfo);

		houseInfo = new HouseInfo();
		houseInfo.setHouseNo(1);
		houseInfo.setHouseName("마산 e 편한세상");
		houseInfo.setAddress("마산시 산호동");
		houseInfo.setAddressDetail("201동 306호");
		houseInfo.setHouseSize(76.2);
		houseInfo.setHouseView(BitmapFactory.decodeResource(getResources(), R.drawable.house_view2));
		houseInfo.setHouseFloorPlan(BitmapFactory.decodeResource(getResources(), R.drawable.default_floor_plan2));
		houseInfo.setBedroom(2);
		houseInfo.setRestroom(1);
		houseInfo.setBalcony(1);
		houseInfo.setKitchen(1);
		houseInfo.setLivingroom(1);
		houseInfo.setHall(1);
		houseInfos.add(houseInfo);

		houseInfo = new HouseInfo();
		houseInfo.setHouseNo(2);
		houseInfo.setHouseName("삼송 두산 위브");
		houseInfo.setAddress("고양시 덕양구");
		houseInfo.setAddressDetail("107동 1101호");
		houseInfo.setHouseSize(65.4);
		houseInfo.setHouseView(BitmapFactory.decodeResource(getResources(), R.drawable.house_view3));
		houseInfo.setHouseFloorPlan(BitmapFactory.decodeResource(getResources(), R.drawable.default_floor_plan3));
		houseInfo.setBedroom(3);
		houseInfo.setRestroom(2);
		houseInfo.setBalcony(1);
		houseInfo.setKitchen(1);
		houseInfo.setLivingroom(1);
		houseInfo.setHall(1);
		houseInfos.add(houseInfo);

		SoloSingleton.getInstance().setHouseInfoList(houseInfos);
	}
}
