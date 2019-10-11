package solo.shinhan.com.solo.loan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import solo.shinhan.com.solo.R;

public class ConfirmIDActivity extends AppCompatActivity {

	private Button btn_confirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_id);

		initView();
		setTopTitleView();
	}

	private void initView() {
		btn_confirm = findViewById(R.id.btn_confirm);
		btn_confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(ConfirmIDActivity.this, LoanTermsActivity.class);
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
