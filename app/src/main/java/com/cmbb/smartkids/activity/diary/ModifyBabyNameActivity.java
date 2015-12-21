package com.cmbb.smartkids.activity.diary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;

public class ModifyBabyNameActivity extends BaseActivity {
    private EditText etName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_baby_name;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_activity_baby_name));
        setActionBarRight("确定");
        etName = (EditText) findViewById(R.id.et_baby_modify_name);
        if(getIntent() != null){
            String name = getIntent().getStringExtra("baby_name");
            if(!TextUtils.isEmpty(name))
                etName.setText(name);
        }
    }

    @Override
    public void onClick(View v) {
        String name = etName.getText().toString();
        if(TextUtils.isEmpty(name)){
            showShortToast("请输入您的宝宝姓名");
            return;
        }
        Intent intent = getIntent();
        intent.putExtra("baby_name", name);
        setResult(RESULT_OK, intent);
        finish();
    }
}
