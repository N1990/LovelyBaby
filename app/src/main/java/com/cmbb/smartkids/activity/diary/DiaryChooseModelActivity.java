package com.cmbb.smartkids.activity.diary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;

public class DiaryChooseModelActivity extends BaseActivity {
    private final String TAG = DiaryChooseModelActivity.class.getSimpleName();
    private RadioGroup rp;
    private RadioButton rbAll, rbCare, rbSelf;
    private int privacy;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_diary_choose_model;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }

    private void initView(){
        setTitle(getString(R.string.title_activity_publish_baby_diary));
        rp = (RadioGroup) findViewById(R.id.rp_diary_choose_model);
        rbAll = (RadioButton) findViewById(R.id.rb_diary_all_preview);
        rbCare = (RadioButton) findViewById(R.id.rb_diary_care_preview);
        rbSelf = (RadioButton) findViewById(R.id.rb_diary_self_preview);
    }

    private void initData(){
        if(getIntent() != null){
            privacy = getIntent().getIntExtra("privacy", 2);
            refreshRadioButtom();
            switch (privacy){
                case 2:
                    rbAll.setChecked(true);
                    rbAll.setCompoundDrawablesWithIntrinsicBounds(null, null, DiaryChooseModelActivity.this.getResources().getDrawable(R.drawable.checkbox_marked), null);
                    break;
                case 1:
                    rbCare.setChecked(true);
                    rbCare.setCompoundDrawablesWithIntrinsicBounds(null, null, DiaryChooseModelActivity.this.getResources().getDrawable(R.drawable.checkbox_marked), null);
                    break;
                case 0:
                    rbSelf.setChecked(true);
                    rbSelf.setCompoundDrawablesWithIntrinsicBounds(null, null, DiaryChooseModelActivity.this.getResources().getDrawable(R.drawable.checkbox_marked), null);
                    break;
            }
        }
    }

    private void addListener(){
        rp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Intent intent = getIntent();
                refreshRadioButtom();
                switch (checkedId){
                    case R.id.rb_diary_all_preview:
                        rbAll.setCompoundDrawablesWithIntrinsicBounds(null, null, DiaryChooseModelActivity.this.getResources().getDrawable(R.drawable.checkbox_marked), null);
                        intent.putExtra("privacy_value", 2);
                        intent.putExtra("privacy_name", rbAll.getText().toString());
                        break;
                    case R.id.rb_diary_care_preview:
                        rbCare.setCompoundDrawablesWithIntrinsicBounds(null, null, DiaryChooseModelActivity.this.getResources().getDrawable(R.drawable.checkbox_marked), null);
                        intent.putExtra("privacy_value", 1);
                        intent.putExtra("privacy_name", rbCare.getText().toString());
                        break;
                    case R.id.rb_diary_self_preview:
                        rbSelf.setCompoundDrawablesWithIntrinsicBounds(null, null, DiaryChooseModelActivity.this.getResources().getDrawable(R.drawable.checkbox_marked), null);
                        intent.putExtra("privacy_value", 0);
                        intent.putExtra("privacy_name", rbSelf.getText().toString());
                        break;
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void refreshRadioButtom(){
        rbAll.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        rbCare.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        rbSelf.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);

    }

}
