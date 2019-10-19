package solo.shinhan.com.solo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SelectFurnitureActivity extends Activity {

	private LinearLayout mLlSofa, mLlBed, mLlTable, mLlDesk, mLlCabinet, mLlCarpet, mLlChair, mLlKitchen, mLlWasher, mLlBathroom, mLlEtc;
	private ImageView mFurnitureImage;
	private TextView mBrand, mModel, mPrice;
	private Button mDetailFurnitureInfo;
	private Button mCollocateFurniture;
	private ImageView mLeftArrow, mRightArrow;
	private TextView mFurnitureCategory;
	private ListView mFurnitureListView;
	private FurnitureListAdapter mFurnitureListAdapter;
	private ArrayList<FurnitureInfo> mFurnitureList;
	private String mCategory;
	private int mFurnitureType;
	private final String[] furnitureCategory = {"소파", "테이블", "침대", "화장실", "캐비넷", "카페트", "의자", "책상", "주방", "세탁기", "기타"};

	private int mFurniturePostion;
	private int mCurrentPosition;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_furniture2);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
		}
		mCurrentPosition = 0;

		final int houseNo = getIntent().getIntExtra("houseNo", -1);

		mCategory = furnitureCategory[0];
		mFurnitureType = 0;

		mFurnitureImage = (ImageView) findViewById(R.id.furniture_item);
		mBrand = (TextView) findViewById(R.id.brand);
		mModel = (TextView) findViewById(R.id.model);
		mPrice = (TextView) findViewById(R.id.price);
		mDetailFurnitureInfo = (Button) findViewById(R.id.detail_furniture_info);
		mCollocateFurniture = (Button) findViewById(R.id.collocate_furniture);
		mLeftArrow = (ImageView) findViewById(R.id.left_arrow);
		mRightArrow = (ImageView) findViewById(R.id.right_arrow);
		mFurnitureCategory = (TextView) findViewById(R.id.furniture_category);
		mFurnitureListView = (ListView) findViewById(R.id.furniture_list_view);
		mLlSofa = findViewById(R.id.ll_sofa);
		mLlBed = findViewById(R.id.ll_bed);
		mLlTable = findViewById(R.id.ll_table);
		mLlDesk = findViewById(R.id.ll_desk);
		mLlCabinet = findViewById(R.id.ll_cabinet);
		mLlCarpet = findViewById(R.id.ll_carpet);
		mLlChair = findViewById(R.id.ll_chair);
		mLlKitchen = findViewById(R.id.ll_kitchen);
		mLlWasher = findViewById(R.id.ll_washer);
		mLlBathroom = findViewById(R.id.ll_bathroom);
		mLlEtc = findViewById(R.id.ll_etc);

		mFurnitureCategory.setText(furnitureCategory[mCurrentPosition]);

		mFurnitureList = new ArrayList<FurnitureInfo>();
		for (int i = 0; i < SoloSingleton.getInstance().getFurnitureMap().get(furnitureCategory[mCurrentPosition]).size(); i++) {
			if (SoloSingleton.getInstance().getFurnitureMap().get(furnitureCategory[mCurrentPosition]).get(i).getDirection() == 1) {
				mFurnitureList.add(SoloSingleton.getInstance().getFurnitureMap().get(furnitureCategory[mCurrentPosition]).get(i));
			}
		}
		// mFurnitureList = SoloSingleton.getInstance().getFurnitureMap().get(furnitureCategory[mCurrentPosition]);
		mFurnitureListAdapter = new FurnitureListAdapter(mFurnitureList);
		mFurnitureListView.setAdapter(mFurnitureListAdapter);

//		mFurnitureImage.setImageBitmap(mFurnitureList.get(0).getFurnitureImage());
		Picasso.get().load(mFurnitureList.get(0).getFurnitureImageString()).into(mFurnitureImage);
		mBrand.setText(mFurnitureList.get(0).getBrand());
		mModel.setText(mFurnitureList.get(0).getModel());
		mPrice.setText(mFurnitureList.get(0).getPrice() + "원");

		mLlSofa.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mCurrentPosition = 0;
				getPositionInfo();
				setViewSelected((ImageButton) findViewById(R.id.ib_btn_sofa));
			}
		});

		mLlTable.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mCurrentPosition = 1;
				getPositionInfo();
				setViewSelected((ImageButton) findViewById(R.id.ib_btn_table));
			}
		});

		mLlBed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mCurrentPosition = 2;
				getPositionInfo();
				setViewSelected((ImageButton) findViewById(R.id.ib_btn_bed));
			}
		});

		mLlDesk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mCurrentPosition = 7;
				getPositionInfo();
				setViewSelected((ImageButton) findViewById(R.id.ib_btn_desk));
			}
		});

		mLlCabinet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mCurrentPosition = 4;
				getPositionInfo();
				setViewSelected((ImageButton) findViewById(R.id.ib_btn_cabinet));
			}
		});

		mLlCarpet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mCurrentPosition = 5;
				getPositionInfo();
				setViewSelected((ImageButton) findViewById(R.id.ib_btn_carpet));
			}
		});

		mLlChair.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mCurrentPosition = 6;
				getPositionInfo();
				setViewSelected((ImageButton) findViewById(R.id.ib_btn_chair));
			}
		});

		mLlKitchen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mCurrentPosition = 8;
				getPositionInfo();
				setViewSelected((ImageButton) findViewById(R.id.ib_btn_kitchen));
			}
		});

		mLlWasher.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mCurrentPosition = 9;
				getPositionInfo();
				setViewSelected((ImageButton) findViewById(R.id.ib_btn_washer));
			}
		});

		mLlBathroom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mCurrentPosition = 3;
				getPositionInfo();
				setViewSelected((ImageButton) findViewById(R.id.ib_btn_bathroom));
			}
		});

		mLlEtc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mCurrentPosition = 10;
				getPositionInfo();
				setViewSelected((ImageButton) findViewById(R.id.ib_btn_etc));
			}
		});

		mRightArrow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mCurrentPosition == 10) {
					mCurrentPosition = 0;
					getPositionInfo();
				} else {
					mCurrentPosition++;
					getPositionInfo();
				}

			}
		});

		mLeftArrow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mCurrentPosition == 0) {
					mCurrentPosition = 10;
					getPositionInfo();
				} else {
					mCurrentPosition--;
					getPositionInfo();
					mFurniturePostion = 0;
				}
			}
		});

		mFurnitureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				view.setSelected(true);
				view.setPressed(true);
//				mFurnitureImage.setImageBitmap(mFurnitureList.get(i).getFurnitureImage());
				Picasso.get().load(mFurnitureList.get(i).getFurnitureImageString()).into(mFurnitureImage);
				mFurniturePostion = i;
				mBrand.setText(mFurnitureList.get(i).getBrand());
				mModel.setText(mFurnitureList.get(i).getModel());
				mPrice.setText(mFurnitureList.get(i).getPrice() + "원");
				mFurnitureType = i;
			}
		});

		mCollocateFurniture.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getBaseContext(), CollocateFurnitureActivity.class);
				intent.putExtra("houseNo", houseNo);
				intent.putExtra("category", mCategory);
				intent.putExtra("furnitureType", mFurnitureType);
				if (SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().size() > 0) {
					for (int i = 0; i < SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().size(); i++) {
						SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(i).setSelectFurniture(false);
					}
					ArrayList<FurnitureInfo> tempFurnitureArrayList = SoloSingleton.getInstance().getFurnitureMap().get(furnitureCategory[mCurrentPosition]);
					for (int i = 0; i < tempFurnitureArrayList.size(); i++) {
						if (tempFurnitureArrayList.get(i).getDirection() == 1 && tempFurnitureArrayList.get(i).getFurnitureNo() == mFurniturePostion) {
							SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().add(new MyCollocateFurnitureInfo(houseNo, tempFurnitureArrayList.get(i), -1, -1, true, 1));
						}
					}
				} else {
					ArrayList<FurnitureInfo> tempFurnitureArrayList = SoloSingleton.getInstance().getFurnitureMap().get(furnitureCategory[mCurrentPosition]);
					for (int i = 0; i < tempFurnitureArrayList.size(); i++) {
						if (tempFurnitureArrayList.get(i).getDirection() == 1 && tempFurnitureArrayList.get(i).getFurnitureNo() == mFurniturePostion) {
							SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().add(new MyCollocateFurnitureInfo(houseNo, tempFurnitureArrayList.get(i), -1, -1, true, 1));
						}
					}
				}

				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		});
	}
	private void setViewSelected(ImageButton selectedIb) {
		findViewById(R.id.ib_btn_sofa).setSelected(false);
		findViewById(R.id.ib_btn_table).setSelected(false);
		findViewById(R.id.ib_btn_bed).setSelected(false);
		findViewById(R.id.ib_btn_desk).setSelected(false);
		findViewById(R.id.ib_btn_cabinet).setSelected(false);
		findViewById(R.id.ib_btn_carpet).setSelected(false);
		findViewById(R.id.ib_btn_chair).setSelected(false);
		findViewById(R.id.ib_btn_kitchen).setSelected(false);
		findViewById(R.id.ib_btn_washer).setSelected(false);
		findViewById(R.id.ib_btn_bathroom).setSelected(false);
		findViewById(R.id.ib_btn_etc).setSelected(false);

		selectedIb.setSelected(true);
	}

	private void getPositionInfo() {
		mCategory = furnitureCategory[mCurrentPosition];
		mFurnitureCategory.setText(mCategory);
		mFurnitureCategory.setText(furnitureCategory[mCurrentPosition]);

		mFurnitureList = new ArrayList<FurnitureInfo>();
		for (int i = 0; i < SoloSingleton.getInstance().getFurnitureMap().get(furnitureCategory[mCurrentPosition]).size(); i++) {
			if (SoloSingleton.getInstance().getFurnitureMap().get(furnitureCategory[mCurrentPosition]).get(i).getDirection() == 1) {
				mFurnitureList.add(SoloSingleton.getInstance().getFurnitureMap().get(furnitureCategory[mCurrentPosition]).get(i));
			}
		}
//		mFurnitureImage.setImageBitmap(mFurnitureList.get(0).getFurnitureImage());
		Picasso.get().load(mFurnitureList.get(0).getFurnitureImageString()).into(mFurnitureImage);
		mBrand.setText(mFurnitureList.get(0).getBrand());
		mModel.setText(mFurnitureList.get(0).getModel());
		mPrice.setText(mFurnitureList.get(0).getPrice() + "원");
		mFurnitureListAdapter = new FurnitureListAdapter(mFurnitureList);
		mFurnitureListView.setAdapter(mFurnitureListAdapter);
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(getBaseContext(), HouseDetailActivity.class);
		intent.putExtra("houseNo", getIntent().getIntExtra("houseNo", -1));
		startActivity(intent);
		overridePendingTransition(0, 0);
		finish();
	}
}
