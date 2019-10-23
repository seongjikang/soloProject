package solo.shinhan.com.solo.loan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solo.shinhan.com.solo.R;
import solo.shinhan.com.solo.SelectActivity;
import solo.shinhan.com.solo.data.CustomPreferences;
import solo.shinhan.com.solo.data.DataResult;
import solo.shinhan.com.solo.data.DataResultImpl;

import static android.view.View.GONE;

public class LoanResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loan_result);

		setTopTitleView();
		getResultOfLoanApply();

		// 대출 실행 버튼
		findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
	}

	public void getResultOfLoanApply() {
		String idNumber = CustomPreferences.getString(getApplicationContext(), "id_num");
		if(idNumber != null) {
			DataResult dataResult = new DataResultImpl();
			dataResult.getResultOfLoanApply(new Callback<JsonObject>() {
				@Override
				public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
					//response.body().toString();
					Log.d("testResponse", response.body().toString());
					String result = response.body().get("result").getAsString();
					if(result.equals("approve")) { // 승인
						findViewById(R.id.ll_refuse).setVisibility(GONE);
						findViewById(R.id.ll_apply).setVisibility(GONE);
						findViewById(R.id.ll_complete).setVisibility(View.VISIBLE);
						findViewById(R.id.btn_next).setVisibility(View.VISIBLE);
					} else if(result.equals("apply")) { // 신청 중
						findViewById(R.id.ll_refuse).setVisibility(GONE);
						findViewById(R.id.ll_apply).setVisibility(View.VISIBLE);
						findViewById(R.id.ll_complete).setVisibility(GONE);
						findViewById(R.id.btn_next).setVisibility(GONE);
					} else { // 거절
						findViewById(R.id.ll_refuse).setVisibility(View.VISIBLE);
						findViewById(R.id.ll_apply).setVisibility(GONE);
						findViewById(R.id.ll_complete).setVisibility(View.GONE);
						findViewById(R.id.btn_next).setVisibility(GONE);
					}
				}

				@Override
				public void onFailure(Call<JsonObject> call, Throwable t) {
					Log.d("testResponse", "FAIL");
				}
			}, idNumber);
		}
	}

	private void setTopTitleView() {
		ImageView backBtn = (ImageView) findViewById(R.id.back_btn);
		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});
		((ImageView)findViewById(R.id.setting_btn)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
			}
		});
	}
}
