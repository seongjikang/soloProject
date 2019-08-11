package solo.shinhan.com.solo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CollocateFurnitureActivity extends Activity {

    private RelativeLayout mFloorPlanLayout;
    private ImageView mBackBtn,mPlusBtn;
    private LinearLayout mCollocateMenu;
    private TextView mAllPrice;
    private ImageView mSelectFurnitureBtn,mLendBtn,mBackCollocateBtn;

    private boolean mOpenMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collocate_furniture);

        final int houseNo= getIntent().getIntExtra("houseNo",-1);

        mFloorPlanLayout = (RelativeLayout) findViewById(R.id.floor_plan_layout);
        mBackBtn = (ImageView) findViewById(R.id.back_btn);
        mPlusBtn = (ImageView) findViewById(R.id.plus_btn);
        mCollocateMenu = (LinearLayout) findViewById(R.id.collocate_menu);
        mAllPrice = (TextView) findViewById(R.id.all_price);
        mSelectFurnitureBtn = (ImageView) findViewById(R.id.select_furniture_btn);
        mLendBtn = (ImageView) findViewById(R.id.lend_btn);
        mBackCollocateBtn = (ImageView) findViewById(R.id.back_collocate_btn);

        mFloorPlanLayout.setBackground(new BitmapDrawable(SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getHouseFloorPlan()));

        mPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOpenMenu = true;
                mCollocateMenu.setVisibility(View.VISIBLE);
            }
        });

        mSelectFurnitureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SelectFurnitureActivity.class);
                intent.putExtra("houseNo", houseNo);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });

        mBackCollocateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOpenMenu = false;
                mCollocateMenu.setVisibility(View.GONE);
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HouseDetailActivity.class);
                intent.putExtra("houseNo",houseNo);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        if(mOpenMenu){
            mCollocateMenu.setVisibility(View.GONE);
            mOpenMenu = false;
        } else {
            Intent intent = new Intent(getBaseContext(), HouseDetailActivity.class);
            intent.putExtra("houseNo",getIntent().getIntExtra("houseNo",-1));
            startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        }

    }
}
