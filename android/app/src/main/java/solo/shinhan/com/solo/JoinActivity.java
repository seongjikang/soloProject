package solo.shinhan.com.solo;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.andrognito.patternlockview.utils.ResourceUtils;
import com.andrognito.rxpatternlockview.RxPatternLockView;
import com.andrognito.rxpatternlockview.events.PatternLockCompleteEvent;
import com.andrognito.rxpatternlockview.events.PatternLockCompoundEvent;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import io.reactivex.functions.Consumer;
import solo.shinhan.com.solo.security.AES256Util;

public class JoinActivity extends Activity {
    private int patternCount = 0;
    private String tempPattern = "";
    private PatternLockView mPatternLockView;
    private TextView mPatternMessage;

    private SoloRegister task;

    private final static String REGISTER_URL = "http://13.125.12.186/v1/user/join";

    private boolean isRegister;
    private final static String FIRST_PATTERN_INPUT = "패턴을 입력해주세요.";
    private final static String SECOND_PATTERN_INPUT = "다시 한번 입력해주세요.";
    private final static String LENGTH_PATTERN_INPUT = "4개의 점 이상을 입력해주세요.";
    private final static String ERROR_PATTERN_INPUT = "패턴이 일치 하지 않습니다. 다시 등록 해주세요.";
    private final static String SUCCESS_PATTERN_INPUT = "패턴 등록 완료";

    private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {
            Log.d(getClass().getName(), "Pattern drawing started");
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            Log.d(getClass().getName(), "Pattern progress: " +
                    PatternLockUtils.patternToString(mPatternLockView, progressPattern));
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            Log.d(getClass().getName(), "Pattern complete: " +
                    PatternLockUtils.patternToString(mPatternLockView, pattern));
        }

        @Override
        public void onCleared() {
            Log.d(getClass().getName(), "Pattern has been cleared");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_join);


        mPatternMessage = (TextView) findViewById(R.id.patternMessage);
        mPatternMessage.setText(FIRST_PATTERN_INPUT);

        mPatternLockView = (PatternLockView) findViewById(R.id.patter_view);
        mPatternLockView.setDotCount(3);
        mPatternLockView.setDotNormalSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_size));
        mPatternLockView.setDotSelectedSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_selected_size));
        mPatternLockView.setPathWidth((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_path_width));
        mPatternLockView.setAspectRatioEnabled(true);
        mPatternLockView.setAspectRatio(PatternLockView.AspectRatio.ASPECT_RATIO_HEIGHT_BIAS);
        mPatternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
        mPatternLockView.setDotAnimationDuration(150);
        mPatternLockView.setPathEndAnimationDuration(100);
        mPatternLockView.setCorrectStateColor(ResourceUtils.getColor(this, R.color.white));
        mPatternLockView.setInStealthMode(false);
        mPatternLockView.setTactileFeedbackEnabled(true);
        mPatternLockView.setInputEnabled(true);
        mPatternLockView.addPatternLockListener(mPatternLockViewListener);

        RxPatternLockView.patternComplete(mPatternLockView)
                .subscribe(new Consumer<PatternLockCompleteEvent>() {
                    @Override
                    public void accept(PatternLockCompleteEvent patternLockCompleteEvent) throws Exception {
                        Log.d(getClass().getName(), "Complete: " + patternLockCompleteEvent.getPattern().toString());
                    }
                });

        RxPatternLockView.patternChanges(mPatternLockView)
                .subscribe(new Consumer<PatternLockCompoundEvent>() {
                    @Override
                    public void accept(PatternLockCompoundEvent event) throws Exception {
                        if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_STARTED) {
                            Log.d(getClass().getName(), "Pattern drawing started");
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_PROGRESS) {
                            Log.d(getClass().getName(), "Pattern progress: " +
                                    PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_COMPLETE) {
                            Log.d(getClass().getName(), "Pattern complete: " +
                                    PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
                                    String inputPattern = PatternLockUtils.patternToString(mPatternLockView, event.getPattern());
                                    if(patternCount == 0) {
                                        if(inputPattern.length() > 3) {
                                            mPatternMessage.setText(SECOND_PATTERN_INPUT);
                                            tempPattern = inputPattern;
                                            patternCount++;
                                        } else {
                                            mPatternMessage.setText(LENGTH_PATTERN_INPUT);
                                            patternCount = 0;
                                        }
                                    } else if (patternCount == 1) {
                                        if(tempPattern.equals(inputPattern)) {
                                            //등록 완료,
                                            AES256Util aes256Util = new AES256Util();
                                            mPatternMessage.setText(SUCCESS_PATTERN_INPUT);
                                            Intent getIntent = getIntent();
                                            String password = aes256Util.aesEncode(inputPattern);
                                            String uuid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                                            String idNum = getIntent.getStringExtra("id_num");
                                            idNum = aes256Util.aesEncode(idNum);
                                            String name = getIntent.getStringExtra("name");
                                            patternCount = 0;

                                            JSONObject requestJson = new JSONObject();
                                            requestJson.put("name", name);
                                            requestJson.put("password",password);
                                            requestJson.put("uuid",uuid);
                                            requestJson.put("id_number",idNum);

                                            task = new SoloRegister();
                                            task.execute(REGISTER_URL,requestJson.toString());

                                            if (isRegister) {
                                                Toast.makeText(JoinActivity.this, "회원 가입 실패", Toast.LENGTH_LONG).show();
                                                finish();
                                            } else {
                                                Toast.makeText(JoinActivity.this, "회원 가입 완료", Toast.LENGTH_LONG).show();
                                                finish();
                                            }

                                        } else {
                                            mPatternMessage.setText(ERROR_PATTERN_INPUT);
                                            patternCount = 0;
                                        }
                                    }
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_CLEARED) {
                            Log.d(getClass().getName(), "Pattern has been cleared");
                        }
                    }
                });
    }

    private class SoloRegister extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String register_url = strings[0];
                String requestJson = strings[1];
                Log.i("requestJson",requestJson);

                HttpClient http = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost(register_url);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Connection", "keep-alive");
                httpPost.setHeader("Content-Type", "application/json");

                httpPost.setEntity(new StringEntity(requestJson,HTTP.UTF_8));

                HttpResponse response = http.execute(httpPost);

                if (response.getStatusLine().getStatusCode() == 200) {
                    ResponseHandler<String> handler = new BasicResponseHandler();
                    String body = handler.handleResponse(response);
                    System.out.println(body);
                    return soloRegister(body);
                } else {
                    System.out.println("response is error : " + response.getStatusLine().getStatusCode());
                    return "fail";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return "fail";
        }
    }

    private String soloRegister(String body) {

        JsonParser parser = new JsonParser();
        JsonObject response = (JsonObject) parser.parse(body);

        Log.i("responseBody",response.toString());

        if ( response.get("result").toString().equals("fail")) {
            isRegister =false;
        } else if(response.get("result").toString().equals("success")) {
            Log.i("response" ,response.get("result").toString());
            isRegister =true;
        }


        return null;
    }
}
