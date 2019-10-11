package solo.shinhan.com.solo.loan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import solo.shinhan.com.solo.R;

public class InputRealInfoActivity extends Activity {

	private EditText et_name;
	private EditText et_id1;
	private EditText et_id2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_real_info);


		initView();
		setTopTitleView();
	}

	private void initView() {
		Button btn_next = findViewById(R.id.btn_next);
		btn_next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				checkValid();
			}
		});
		et_name = findViewById(R.id.et_name);
		et_id1 = findViewById(R.id.et_id1);
		et_id2 = findViewById(R.id.et_id2);
	}

	public void setTopTitleView() {
		ImageView backBtn = (ImageView) findViewById(R.id.back_btn);
		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});
	}

	/**
	 * 본인인증 완료 후 직장/소득정보 입력 화면으로 이동
	 */
	private void checkValid() {
		if(et_name.getText() == null || et_name.getText().length() < 1) {
			return;
		}
		if(et_id1.getText() == null || et_id1.getText().length() < 1) {
			return;
		}
		if(et_id2.getText() == null || et_id2.getText().length() < 1) {
			return;
		}
		Intent intent = new Intent(getApplicationContext(), InputInfoActivity.class);
		startActivity(intent);
	}
}
