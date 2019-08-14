package solo.shinhan.com.solo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CollocateFurnitureActivity extends Activity {

    private RelativeLayout mFloorPlanLayout;
    private ImageView mBackBtn,mPlusBtn;
    private LinearLayout mCollocateMenu;
    private TextView mAllPrice;
    private ImageView mSelectFurnitureBtn,mLendBtn,mBackCollocateBtn;
    private CollocateFurnitureView mCollocateFurnitureView;
    private ListView mItemList;
    private ItemListAdapter mItemListAdapter;
    private AlertDialog.Builder builder;

    private boolean mOpenMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collocate_furniture);

        mItemListAdapter = new ItemListAdapter(SoloSingleton.getInstance().getMyCollocateFurnitureInfoList());

        mItemList = (ListView) findViewById(R.id.item_list_view);

        mItemList.setAdapter(mItemListAdapter);

        final int houseNo= getIntent().getIntExtra("houseNo", -1);

        mFloorPlanLayout = (RelativeLayout) findViewById(R.id.floor_plan_layout);
        mBackBtn = (ImageView) findViewById(R.id.back_btn);
        mPlusBtn = (ImageView) findViewById(R.id.plus_btn);
        mCollocateMenu = (LinearLayout) findViewById(R.id.collocate_menu);
        mAllPrice = (TextView) findViewById(R.id.all_price);
        mSelectFurnitureBtn = (ImageView) findViewById(R.id.select_furniture_btn);
        mLendBtn = (ImageView) findViewById(R.id.lend_btn);
        mBackCollocateBtn = (ImageView) findViewById(R.id.back_collocate_btn);

        builder = new AlertDialog.Builder(this);

        mFloorPlanLayout.setBackground(new BitmapDrawable(SoloSingleton.getInstance().getHouseInfoList().get(houseNo).getHouseFloorPlan()));
        mCollocateFurnitureView = new CollocateFurnitureView(getApplicationContext());

        int priceSum = 0;

        if(SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().size() == 0){
            mAllPrice.setText("0원");
        } else {
            for(int i=0; i<SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().size(); i++) {
                priceSum += SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(i).getFurnitureInfo().getPrice();
            }
            mAllPrice.setText(priceSum + "원");
        }

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

        mItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                if (SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(i).isDeleteMode()){

                    builder.setTitle("가구 지우기");
                    builder.setMessage("가구를 지우겠습니까?");
                    builder.setPositiveButton("예",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().remove(i);
                                    Intent intent = new Intent(getBaseContext(), CollocateFurnitureActivity.class);
                                    intent.putExtra("houseNo",houseNo);
                                    startActivity(intent);
                                    overridePendingTransition(0,0);
                                    finish();
                                }
                            });
                    builder.setNegativeButton("아니오",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(i).setDeleteMode(false);
                                }
                            });
                    builder.show();

                } else {
                    for(int j=0; j< SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().size();j++) {
                        if ( j == i){
                            SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(j).setSelectFurniture(true);
                        } else {
                            SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(j).setSelectFurniture(false);
                        }
                    }
                }

                mItemListAdapter = new ItemListAdapter(SoloSingleton.getInstance().getMyCollocateFurnitureInfoList());
                mItemList.setAdapter(mItemListAdapter);

            }
        });

        mItemList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(i).isDeleteMode()) {
                    SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(i).setDeleteMode(false);
                    mItemListAdapter = new ItemListAdapter(SoloSingleton.getInstance().getMyCollocateFurnitureInfoList());
                    mItemList.setAdapter(mItemListAdapter);
                } else {
                    SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(i).setDeleteMode(true);
                    mItemListAdapter = new ItemListAdapter(SoloSingleton.getInstance().getMyCollocateFurnitureInfoList());
                    mItemList.setAdapter(mItemListAdapter);
                }
                return true;
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
