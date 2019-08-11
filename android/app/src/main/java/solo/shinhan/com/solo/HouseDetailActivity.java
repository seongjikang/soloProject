package solo.shinhan.com.solo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HouseDetailActivity extends Activity {
    private RelativeLayout mCollocateFurnitureBtn;
    private RelativeLayout mBackBtn;
    private ImageView mFloorPlanView;
    private TextView mHouseName, mHouseSize, mBedroom, mRestroom, mBalcony, mKitchen, mLivingroom,mHall;
    int houseNo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);
        mCollocateFurnitureBtn = (RelativeLayout) findViewById(R.id.collocate_furniture_btn);
        mBackBtn = (RelativeLayout) findViewById(R.id.back_btn);
        mFloorPlanView = (ImageView) findViewById(R.id.floor_plan_view);
        mHouseName = (TextView) findViewById(R.id.house_name) ;
        mHouseSize = (TextView) findViewById(R.id.house_size);
        mBedroom = (TextView) findViewById(R.id.bedroom);
        mRestroom = (TextView) findViewById(R.id.restroom);
        mBalcony = (TextView) findViewById(R.id.balcony);
        mKitchen = (TextView) findViewById(R.id.kitchen);
        mLivingroom = (TextView) findViewById(R.id.livingroom);
        mHall = (TextView) findViewById(R.id.hall);

        houseNo = getIntent().getIntExtra("houseNo",-1);

        mFloorPlanView.setImageBitmap(SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getHouseFloorPlan());
        mHouseName.setText(SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getHouseName()+ " " + SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getAddressDetail());
        mHouseSize.setText(SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getHouseSize()+"㎡");
        mBedroom.setText("침실 "+ SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getBedroom());
        mRestroom.setText("화장실 " + SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getRestroom());
        mBalcony.setText("발코니 " + SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getBalcony());
        mKitchen.setText("주방/식당 " + SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getKitchen());
        mLivingroom.setText("거실 " + SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getLivingroom());
        mHall.setText("홀 " + SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getHall());

        mCollocateFurnitureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SelectFurnitureActivity.class);
                intent.putExtra("houseNo", houseNo);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("houseNo", houseNo);
        startActivity(intent);
        overridePendingTransition(0,0);
        finish();
    }
}
