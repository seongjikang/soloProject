package solo.shinhan.com.solo.loan;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import solo.shinhan.com.solo.R;
import solo.shinhan.com.solo.SelectActivity;

public class LoanInfoActivity extends Activity {

	private Button btn_apply;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loan_info);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_common));
		}
		initView();
		setTopTitleView();
	}

	private void initView() {
		btn_apply = findViewById(R.id.btn_apply);
		btn_apply.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(LoanInfoActivity.this, LoanTermsActivity.class);
				startActivity(intent);
			}
		});
		((Button)findViewById(R.id.btn_loan_result)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO 대출 결과 확인
				Intent intent = new Intent(LoanInfoActivity.this, LoanResultActivity.class);
				startActivity(intent);
			}
		});
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
