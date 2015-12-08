package com.cmbb.smartkids.activity.user;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

public class ScoreComplainActivity extends BaseActivity {

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_activity_score_complain));
        ((SimpleDraweeView) findViewById(R.id.sdv)).setImageURI(Uri.parse("res://com.cmbb.smartkids/" + R.mipmap.score_complain_bg));
    }

    @Override
    protected int getLayoutId() {

        return R.layout.activity_score_complain;

    }

}
