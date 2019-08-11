package solo.shinhan.com.solo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectFurnitureActivity extends Activity {

    private ImageView mFurnitureImage;
    private TextView mBrand, mModel, mPrice;
    private LinearLayout mDetailFurnitureInfo;
    private LinearLayout mCollocateFurniture;
    private ImageView mLeftArrow,mRightArrow;
    private TextView mFurnitureCategory;
    private ListView mFurnitureListView;
    private FurnitureListAdapter mFurnitureListAdapter;
    private ArrayList<FurnitureInfo> mFurnitureList;

    private int mCurrentPosition;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_furniture);
        mCurrentPosition = 0;

        final int houseNo = getIntent().getIntExtra("houseNo", -1);

        final String[] furnitureCategory = {"소파", "테이블", "침대"};

        mFurnitureImage = (ImageView) findViewById(R.id.furniture_item);
        mBrand = (TextView)findViewById(R.id.brand);
        mModel = (TextView)findViewById(R.id.model);
        mPrice = (TextView)findViewById(R.id.price);
        mDetailFurnitureInfo = (LinearLayout) findViewById(R.id.detail_furniture_info);
        mCollocateFurniture = (LinearLayout)findViewById(R.id.collocate_furniture);
        mLeftArrow = (ImageView)findViewById(R.id.left_arrow);
        mRightArrow = (ImageView)findViewById(R.id.right_arrow);
        mFurnitureCategory = (TextView)findViewById(R.id.furniture_category);
        mFurnitureListView = (ListView)findViewById(R.id.furniture_list_view);

        mFurnitureCategory.setText(furnitureCategory[mCurrentPosition]);

        mFurnitureList = SoloSingleton.getInstance().getFurnitureMap().get(furnitureCategory[mCurrentPosition]);
        mFurnitureListAdapter = new FurnitureListAdapter(mFurnitureList);
        mFurnitureListView.setAdapter(mFurnitureListAdapter);

        mFurnitureImage.setImageBitmap(mFurnitureList.get(0).getFurnitureImage());
        mBrand.setText("브랜드 "+ mFurnitureList.get(0).getBrand());
        mModel.setText("모델명 " + mFurnitureList.get(0).getModel());
        mPrice.setText("가격 " + mFurnitureList.get(0).getPrice()+"원");

        mLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentPosition == 2 ){
                    mCurrentPosition=0;
                    mFurnitureCategory.setText(furnitureCategory[mCurrentPosition]);
                    mFurnitureList = SoloSingleton.getInstance().getFurnitureMap().get(furnitureCategory[mCurrentPosition]);
                    mFurnitureImage.setImageBitmap(mFurnitureList.get(0).getFurnitureImage());
                    mBrand.setText("브랜드 "+ mFurnitureList.get(0).getBrand());
                    mModel.setText("모델명 " + mFurnitureList.get(0).getModel());
                    mPrice.setText("가격 " + mFurnitureList.get(0).getPrice()+"원");
                    mFurnitureListAdapter = new FurnitureListAdapter(mFurnitureList);
                    mFurnitureListView.setAdapter(mFurnitureListAdapter);
                } else {
                    mCurrentPosition++;
                    mFurnitureCategory.setText(furnitureCategory[mCurrentPosition]);
                    mFurnitureList = SoloSingleton.getInstance().getFurnitureMap().get(furnitureCategory[mCurrentPosition]);
                    mFurnitureImage.setImageBitmap(mFurnitureList.get(0).getFurnitureImage());
                    mBrand.setText("브랜드 "+ mFurnitureList.get(0).getBrand());
                    mModel.setText("모델명 " + mFurnitureList.get(0).getModel());
                    mPrice.setText("가격 " + mFurnitureList.get(0).getPrice()+"원");
                    mFurnitureListAdapter = new FurnitureListAdapter(mFurnitureList);
                    mFurnitureListView.setAdapter(mFurnitureListAdapter);
                }
            }
        });

        mRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentPosition == 0 ){
                    mCurrentPosition=2;
                    mFurnitureCategory.setText(furnitureCategory[mCurrentPosition]);
                    mFurnitureList = SoloSingleton.getInstance().getFurnitureMap().get(furnitureCategory[mCurrentPosition]);
                    mFurnitureImage.setImageBitmap(mFurnitureList.get(0).getFurnitureImage());
                    mBrand.setText("브랜드 "+ mFurnitureList.get(0).getBrand());
                    mModel.setText("모델명 " + mFurnitureList.get(0).getModel());
                    mPrice.setText("가격 " + mFurnitureList.get(0).getPrice()+"원");
                    mFurnitureListAdapter = new FurnitureListAdapter(mFurnitureList);
                    mFurnitureListView.setAdapter(mFurnitureListAdapter);
                } else {
                    mCurrentPosition--;
                    mFurnitureCategory.setText(furnitureCategory[mCurrentPosition]);
                    mFurnitureList = SoloSingleton.getInstance().getFurnitureMap().get(furnitureCategory[mCurrentPosition]);
                    mFurnitureImage.setImageBitmap(mFurnitureList.get(0).getFurnitureImage());
                    mBrand.setText("브랜드 "+ mFurnitureList.get(0).getBrand());
                    mModel.setText("모델명 " + mFurnitureList.get(0).getModel());
                    mPrice.setText("가격 " + mFurnitureList.get(0).getPrice()+"원");
                    mFurnitureListAdapter = new FurnitureListAdapter(mFurnitureList);
                    mFurnitureListView.setAdapter(mFurnitureListAdapter);
                }
            }
        });

        mFurnitureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setSelected(true);
                view.setPressed(true);
                mFurnitureImage.setImageBitmap(mFurnitureList.get(i).getFurnitureImage());
                mBrand.setText("브랜드 "+ mFurnitureList.get(i).getBrand());
                mModel.setText("모델명 " + mFurnitureList.get(i).getModel());
                mPrice.setText("가격 " + mFurnitureList.get(i).getPrice()+"원");
            }
        });

        mCollocateFurniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CollocateFurnitureActivity.class);
                intent.putExtra("houseNo",houseNo);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getBaseContext(), HouseDetailActivity.class);
        intent.putExtra("houseNo",getIntent().getIntExtra("houseNo",-1));
        startActivity(intent);
        overridePendingTransition(0,0);
        finish();
    }
}
