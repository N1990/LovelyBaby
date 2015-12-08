package com.cmbb.smartkids.activity.more;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.utils.JsonParser;
import com.iflytek.cloud.RecognizerResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/10/13 11:05
 */
public class AboutActivity extends BaseActivity {
    private TextView tv;
    private TextView tv_about_content;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_activity_about));
        tv = (TextView) findViewById(R.id.tv_about);
        tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv.getPaint().setAntiAlias(true);//抗锯齿
        tv.setOnClickListener(this);
        tv_about_content = (TextView) findViewById(R.id.tv_about_content);
    }

    @Override
    public void onClick(View v) {
//        showShortToast("暂无内容");
        /*XunFeiUtils.getInstance(this).startVoice(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                printResult(recognizerResult, tv_about_content);
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });*/
        CompanyRule.newInstance(this);
    }

    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    private void printResult(RecognizerResult results, TextView mResultText) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }

        mResultText.setText(resultBuffer.toString());
    }
}
