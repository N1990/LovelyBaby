package com.cmbb.smartkids.activity.more;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

public class GrownValusActivity extends BaseActivity {


    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_activity_grown_complain));
        ((SimpleDraweeView) findViewById(R.id.sdv)).setImageURI(Uri.parse("res://com.cmbb.smartkids/" + R.mipmap.grown_value_bg));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_grown_valus;
    }
    public static void newInstance(Activity activity){
        Intent intent = new Intent(activity, GrownValusActivity.class);
        activity.startActivity(intent);
    }
}
