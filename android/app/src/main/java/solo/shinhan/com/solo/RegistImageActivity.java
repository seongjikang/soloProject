package solo.shinhan.com.solo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.inputmethod.EditorInfo.IME_ACTION_NEXT;

public class RegistImageActivity extends Activity {

	private EditText mEtHouseName, mEtAddress, mEtAddressDetail, mEtHouseSize, mEtBedroom, mEtRestroom, mEtBalcony, mEtKitchen, mEtLivingroom, mEtHall;
	private TextView mTvAlertHouseName, mTvAlertAddress, mTvAlertAddressDetail, mTvAlertHouseSize, mTvAlertBedRoom, mTvAlertRestroom, mTvAlertBalcony, mTvAlertKitchen, mTvAlrertLivingroom, mTvAlertHall;
	private Button mBtnGoNext;

	private InputMethodManager imm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist_image);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		initView();

	}

	private void initView() {
		mTvAlertHouseName = findViewById(R.id.tv_alert_house_name);
		mTvAlertAddress = findViewById(R.id.tv_alert_address);
		mTvAlertAddressDetail = findViewById(R.id.tv_alert_address_detail);
		mTvAlertBalcony = findViewById(R.id.tv_alert_balcony);
		mTvAlertBedRoom = findViewById(R.id.tv_alert_bedroom);
		mTvAlertHall = findViewById(R.id.tv_alert_hall);
		mTvAlertHouseSize = findViewById(R.id.tv_alert_house_size);
		mTvAlertKitchen = findViewById(R.id.tv_alert_kitchen);
		mTvAlrertLivingroom = findViewById(R.id.tv_alert_livingroom);
		mTvAlertRestroom = findViewById(R.id.tv_alert_restroom);
		mEtHouseName = findViewById(R.id.et_house_name);
		setTextChangeListener(mEtHouseName, mTvAlertHouseName);
		mEtAddress = findViewById(R.id.et_house_address);
		setTextChangeListener(mEtAddress, mTvAlertAddress);
		mEtAddressDetail = findViewById(R.id.et_house_address_detail);
		setTextChangeListener(mEtAddressDetail, mTvAlertAddressDetail);
		mEtHouseSize = findViewById(R.id.et_house_size);
		setTextChangeListener(mEtHouseSize, mTvAlertHouseSize);
		mEtBedroom = findViewById(R.id.et_bedroom);
		setTextChangeListener(mEtBedroom, mTvAlertBedRoom);
		mEtRestroom = findViewById(R.id.et_restroom);
		setTextChangeListener(mEtRestroom, mTvAlertRestroom);
		mEtBalcony = findViewById(R.id.et_balcony);
		setTextChangeListener(mEtBalcony, mTvAlertBalcony);
		mEtKitchen = findViewById(R.id.et_kitchen);
		setTextChangeListener(mEtKitchen, mTvAlertKitchen);
		mEtLivingroom = findViewById(R.id.et_livingroom);
		setTextChangeListener(mEtLivingroom, mTvAlrertLivingroom);
		mEtHall = findViewById(R.id.et_hall);
		setTextChangeListener(mEtHall, mTvAlertHall);

		mEtHall.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
				if (i == EditorInfo.IME_ACTION_DONE) {
					mEtHall.clearFocus();
					imm.hideSoftInputFromWindow(mEtHall.getWindowToken(), 0);
				}
				return true;
			}
		});
		mBtnGoNext = findViewById(R.id.btn_go_next);
		mBtnGoNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				checkValidation();
			}
		});
	}

	private void setTextChangeListener(final EditText et, final TextView tv) {
		et.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				if (charSequence.length() > 0) {
					tv.setVisibility(View.GONE);
				}
			}

			@Override
			public void afterTextChanged(Editable editable) {

			}
		});
	}

	private void registInfo() {

		ArrayList<HouseInfo> houseInfos = (ArrayList<HouseInfo>) SoloSingleton.getInstance().getHouseInfoList();
		int houseno = houseInfos.size();

		Bitmap bitmap;
		String imagePath = getIntent().getExtras().getString("imagePath");
		bitmap = BitmapFactory.decodeFile(imagePath);

		HouseInfo houseInfo = new HouseInfo();
		houseInfo.setHouseNo(houseno);
		houseInfo.setHouseName(mEtHouseName.getText().toString());
		houseInfo.setAddress(mEtAddress.getText().toString());
		houseInfo.setAddressDetail(mEtAddressDetail.getText().toString());
		houseInfo.setHouseSize(Integer.parseInt(mEtHouseSize.getText().toString()));
		houseInfo.setHouseView(bitmap);
		houseInfo.setHouseFloorPlan(bitmap);
		houseInfo.setBedroom(Integer.parseInt(mEtBedroom.getText().toString()));
		houseInfo.setRestroom(Integer.parseInt(mEtRestroom.getText().toString()));
		houseInfo.setBalcony(Integer.parseInt(mEtBalcony.getText().toString()));
		houseInfo.setKitchen(Integer.parseInt(mEtKitchen.getText().toString()));
		houseInfo.setLivingroom(Integer.parseInt(mEtLivingroom.getText().toString()));
		houseInfo.setHall(Integer.parseInt(mEtHall.getText().toString()));
		houseInfos.add(houseInfo);

		SoloSingleton.getInstance().setHouseInfoList(houseInfos);

		Intent intent = new Intent(getBaseContext(), MainActivity.class);
		startActivity(intent);
		finish();
		Toast.makeText(getApplicationContext(), "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
		overridePendingTransition(0, 0);
	}

	private void checkValidation() {
		if (mEtHouseName.getText() == null || mEtHouseName.getText().length() < 1) {
			mEtHouseName.requestFocus();
			imm.showSoftInput(mEtHouseName, 0);
			mTvAlertHouseName.setVisibility(View.VISIBLE);
			return;
		}
		if (mEtAddress.getText() == null || mEtAddress.getText().length() < 1) {
			mEtAddress.requestFocus();
			imm.showSoftInput(mEtAddress, 0);
			mTvAlertAddress.setVisibility(View.VISIBLE);
			return;
		}
		if (mEtAddressDetail.getText() == null || mEtAddressDetail.getText().length() < 1) {
			mEtAddressDetail.requestFocus();
			imm.showSoftInput(mEtAddressDetail, 0);
			mTvAlertAddressDetail.setVisibility(View.VISIBLE);
			return;
		}
		if (mEtHouseSize.getText() == null || mEtHouseSize.getText().length() < 1) {
			mEtHouseSize.requestFocus();
			imm.showSoftInput(mEtHouseSize, 0);
			mTvAlertHouseSize.setVisibility(View.VISIBLE);
			return;
		}
		if (mEtBedroom.getText() == null || mEtBedroom.getText().length() < 1) {
			mEtBedroom.requestFocus();
			imm.showSoftInput(mEtBedroom, 0);
			mTvAlertBedRoom.setVisibility(View.VISIBLE);
			return;
		}
		if (mEtRestroom.getText() == null || mEtRestroom.getText().length() < 1) {
			mEtRestroom.requestFocus();
			imm.showSoftInput(mEtRestroom, 0);
			mTvAlertRestroom.setVisibility(View.VISIBLE);
			return;
		}
		if (mEtBalcony.getText() == null || mEtBalcony.getText().length() < 1) {
			mEtBalcony.requestFocus();
			imm.showSoftInput(mEtBalcony, 0);
			mTvAlertBalcony.setVisibility(View.VISIBLE);
			return;
		}
		if (mEtKitchen.getText() == null || mEtKitchen.getText().length() < 1) {
			mEtKitchen.requestFocus();
			imm.showSoftInput(mEtKitchen, 0);
			mTvAlertKitchen.setVisibility(View.VISIBLE);
			return;
		}
		if (mEtLivingroom.getText() == null || mEtLivingroom.getText().length() < 1) {
			mEtLivingroom.requestFocus();
			imm.showSoftInput(mEtLivingroom, 0);
			mTvAlrertLivingroom.setVisibility(View.VISIBLE);
			return;
		}
		if (mEtHall.getText() == null || mEtHall.getText().length() < 1) {
			mEtHall.requestFocus();
			imm.showSoftInput(mEtHall, 0);
			mTvAlertHall.setVisibility(View.VISIBLE);
			return;
		}

		registInfo();
	}
}
