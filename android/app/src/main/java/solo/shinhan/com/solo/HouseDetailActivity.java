package solo.shinhan.com.solo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HouseDetailActivity extends Activity {
    private Button mCollocateFurnitureBtn;
    private ImageView mBackBtn;
    private ImageView mFloorPlanView;
    private TextView mHouseName, mHouseSize, mBedroom, mRestroom, mBalcony, mKitchen, mLivingroom,mHall, mHouseDetail;
    int houseNo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_common));
        }
        mCollocateFurnitureBtn = (Button) findViewById(R.id.collocate_furniture_btn);
        mBackBtn = (ImageView) findViewById(R.id.back_btn);
        mFloorPlanView = (ImageView) findViewById(R.id.floor_plan_view);
        mHouseName = (TextView) findViewById(R.id.house_name) ;
        mHouseDetail = findViewById(R.id.house_detail);
        mHouseSize = (TextView) findViewById(R.id.house_size);
        mBedroom = (TextView) findViewById(R.id.bedroom);
        mRestroom = (TextView) findViewById(R.id.restroom);
        mBalcony = (TextView) findViewById(R.id.balcony);
        mKitchen = (TextView) findViewById(R.id.kitchen);
        mLivingroom = (TextView) findViewById(R.id.livingroom);
        mHall = (TextView) findViewById(R.id.hall);

        houseNo = getIntent().getIntExtra("houseNo",-1);

        mFloorPlanView.setImageBitmap(SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getHouseFloorPlan());
        mHouseName.setText(SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getHouseName());
        mHouseDetail.setText(SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getAddressDetail());
        mHouseSize.setText(SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getHouseSize()+"㎡");
        mBedroom.setText(SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getBedroom()+"");
        mRestroom.setText(SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getRestroom()+"");
        mBalcony.setText(SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getBalcony()+"");
        mKitchen.setText(SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getKitchen()+"");
        mLivingroom.setText(SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getLivingroom()+"");
        mHall.setText(SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getHall()+"");

        mCollocateFurnitureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CollocateFurnitureActivity.class);
                intent.putExtra("houseNo", houseNo);
                intent.putExtra("category","not");
                intent.putExtra("furnitureType",-1);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
    }

    /*@Override
    public void onBackPressed() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("houseNo", houseNo);
        startActivity(intent);
        overridePendingTransition(0,0);
        finish();
    }*/
}
