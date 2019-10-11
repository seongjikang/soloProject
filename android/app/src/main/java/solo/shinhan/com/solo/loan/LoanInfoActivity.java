package solo.shinhan.com.solo.loan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import solo.shinhan.com.solo.R;

public class LoanInfoActivity extends Activity {

	private Button btn_apply;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loan_info);

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
	}

	private void setTopTitleView() {
		ImageView backBtn = (ImageView) findViewById(R.id.back_btn);
		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});
	}
}
