package solo.shinhan.com.solo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;

public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();
        loadData();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        }, 2000);
    }

    private void loadData() {
        Log.i("loaddata","loaddata");

        ArrayList<HouseInfo> houseInfos = new ArrayList<HouseInfo>();

        HouseInfo houseInfo;
        for(int i =0; i<3;i++) {
            houseInfo = new HouseInfo();
            houseInfo.setHouseNo(i);
            houseInfo.setHouseName("집 이름");
            houseInfo.setAddress("집 주소");
            houseInfo.setAddressDetail("집 세부 주소");
            houseInfo.setHouseSize(55);
            houseInfo.setHouseView(BitmapFactory.decodeResource(getResources(), R.drawable.default_house));

            houseInfos.add(houseInfo);
        }

        SoloSingleton.getInstance().setHouseInfoList(houseInfos);

        SoloSingleton.getInstance().setHouseInfoList(houseInfos);
    }
}
