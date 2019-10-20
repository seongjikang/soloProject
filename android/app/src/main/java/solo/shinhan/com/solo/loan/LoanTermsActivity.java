package solo.shinhan.com.solo.loan;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import solo.shinhan.com.solo.R;

public class LoanTermsActivity extends Activity {

	private Button btn_next;
	private LinearLayout ll_check1;
	private LinearLayout ll_check2;
	private CheckBox cb1;
	private CheckBox cb2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loan_terms);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_common));
		}
		initView();
		setTopTitleView();
	}

	private void initView() {
		btn_next = findViewById(R.id.btn_next);
		btn_next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				validCheck();
			}
		});
		cb1 = findViewById(R.id.cb1);
		cb2 = findViewById(R.id.cb2);
		cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				((ImageView) findViewById(R.id.iv_check1)).setSelected(b);
				((ImageView) findViewById(R.id.iv_check2)).setSelected(b);
				((ImageView) findViewById(R.id.iv_check3)).setSelected(b);
				((ImageView) findViewById(R.id.iv_check4)).setSelected(b);
				((ImageView) findViewById(R.id.iv_check5)).setSelected(b);
				((ImageView) findViewById(R.id.iv_check6)).setSelected(b);
			}
		});
		ll_check1 = findViewById(R.id.ll_check1);
		ll_check1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				cb1.setChecked(!cb1.isChecked());
			}
		});
		ll_check2 = findViewById(R.id.ll_check2);
		ll_check2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				cb2.setChecked(!cb2.isChecked());
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

	/**
	 * 약관동의 완료되었다면 본인인증 화면으로 이동
	 */
	public void validCheck() {
		if (!cb1.isChecked()) {
			return; // alert
		}
		if (!cb2.isChecked()) {
			return; // alert
		}
		Intent intent = new Intent(LoanTermsActivity.this, InputRealInfoActivity.class);
		startActivity(intent);
	}
}
