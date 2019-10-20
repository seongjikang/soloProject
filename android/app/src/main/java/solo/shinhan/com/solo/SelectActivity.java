package solo.shinhan.com.solo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectActivity extends Activity {

	private LinearLayout mTakePicture;
	private LinearLayout mLoadData;
	private LinearLayout mLlGoAlbum;

	private String mImagePath = "";
	private Uri mImageUri;
	private static final int FROM_SELECT_PHOTO = 1000;
	private static final int FROM_CAMERA = 2000;

	private File tempFile;
//	File profileIconFile = new File(getDownloadDirectory(), name + ".png");

	long pressedTime = 0;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_common));
		}

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initView();
	}

	private void initView() {
		mTakePicture = (LinearLayout) findViewById(R.id.select_take_picture);
		mLoadData = (LinearLayout) findViewById(R.id.select_load_data);
		mLlGoAlbum = findViewById(R.id.ll_go_album);

		mTakePicture.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getPhotoFromCamera();
			}
		});

		mLoadData.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getBaseContext(), MainActivity.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
//                finish();
			}
		});

		mLlGoAlbum.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getPhotoFromAlbum();
			}
		});
	}

	@Override
	public void onBackPressed() {
		if (pressedTime == 0) {
			Toast.makeText(SelectActivity.this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
			pressedTime = System.currentTimeMillis();
		} else {
			int seconds = (int) (System.currentTimeMillis() - pressedTime);

			if (seconds > 2000) {
				Toast.makeText(SelectActivity.this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
				pressedTime = 0;
			} else {
				super.onBackPressed();
				finish();
			}
		}
	}

	/**
	 * 카메라로 사진 찍기
	 */
	private void getPhotoFromCamera() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		try {
			tempFile = createImageFile();
		} catch (IOException e) {
			Toast.makeText(this, "이미지 처리 오류! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
			finish();
		}
		if(tempFile != null) {
			Uri photoUri;
			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
				photoUri = FileProvider.getUriForFile(this,
						getPackageName(), tempFile);
				///storage/emulated/0/solo/solo_174701_7086573330312379974.jpg
				// content://solo.shinhan.com.solo.provider/storage%2Femulated/solo/solo_174701_7086573330312379974.jpg
			} else {
				photoUri = Uri.fromFile(tempFile);
			}
			intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
			startActivityForResult(intent, FROM_CAMERA);
			overridePendingTransition(0, 0);
		}
	}

	private String getDownloadDirectory() {
		String externalStorageState = Environment.getExternalStorageState();
		// 외장메모리 O
		if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
			String externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath(); ///storage/emulated/0
			return externalStoragePath;
		} else {
			// 외장메모리 없으면 내장메모리
			String dataDirectoryPath = Environment.getDataDirectory().getAbsolutePath(); ///data
			return dataDirectoryPath;
		}

	}

	private File createImageFile() throws IOException {
		// 이미지 파일 이름 ( blackJin_{시간}_ )
		String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
		String imageFileName = "solo_" + timeStamp + "_";

		// 이미지가 저장될 폴더 이름 ( blackJin )
		File storageDir = new File(Environment.getExternalStorageDirectory() + "/solo/");
		if (!storageDir.exists()) storageDir.mkdirs();

		// 빈 파일 생성
		File image = File.createTempFile(imageFileName, ".jpg", storageDir);
		mImagePath = image.getAbsolutePath();

		return image;
	}

	/**
	 * 사진 가져오기
	 */
	private void getPhotoFromAlbum() {
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType(MediaStore.Images.Media.CONTENT_TYPE);

		startActivityForResult(intent, FROM_SELECT_PHOTO);
		overridePendingTransition(0, 0);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == FROM_SELECT_PHOTO && data != null) {
			mImageUri = data.getData(); // 사용자가 선택한 사진 content://media/external/images/media/1465
			///external/images/media/1949
			String result = "";
			if (mImageUri != null) {

				// 이미지 파일의 경로 얻어오기 시작
				// 이 경로를 DB에 쓸 것이다.
				Cursor cursor = getContentResolver().query(mImageUri, null, null, null, null);
				if (cursor == null) {
					result = mImageUri.getPath();
				} else {
					cursor.moveToFirst();
					int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
					result = cursor.getString(idx);
					cursor.close();
				}
				mImagePath = result;

				Intent intent = new Intent(getBaseContext(), RegistImageActivity.class);
				intent.putExtra("imagePath", mImagePath);
				startActivity(intent);
				overridePendingTransition(0, 0);
			}
		} else if (requestCode == FROM_CAMERA && resultCode == RESULT_OK) {
			Intent intent = new Intent(getBaseContext(), RegistImageActivity.class);
			intent.putExtra("imagePath", mImagePath);
			startActivity(intent);
			overridePendingTransition(0, 0);
			/*
			Bitmap bitmap = (Bitmap) data.getExtras().get("data");
			if (bitmap != null) {
				Intent intent = new Intent(getBaseContext(), RegistImageActivity.class);
				intent.putExtra("image", bitmap);
				startActivity(intent);
				overridePendingTransition(0, 0);
			}*/
		}
	}

}
