package com.cmbb.smartkids.activity.user;

import android.app.Activity;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;


import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.adapter.DraftsAdapter;
import com.cmbb.smartkids.activity.user.model.DraftsModel;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.CustomListener;
import com.cmbb.smartkids.db.DBContent;
import com.cmbb.smartkids.mengbottomsheets.BottomSheet;
import com.cmbb.smartkids.utils.SPCache;
import com.cmbb.smartkids.utils.log.Log;

/**
 *
 */
public class MyDraftsActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private int userId = -1;
    private final int SUCCESSFUL_REQUIRE = 10102;
    private RecyclerView rv;
    private NestedScrollView nsv;
    private DraftsAdapter adapter;
    private BottomSheet sheet;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_drafts;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.title_activity_drafts));
        nsv = (NestedScrollView) findViewById(R.id.nsv_self);
        rv = (RecyclerView) findViewById(R.id.rv_self);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DraftsAdapter(this);
        rv.setAdapter(adapter);
        adapter.setOnItemListener(onItemListener);
        adapter.setOnItemLongListener(onItemLongListener);
        if (getIntent() != null) {
            userId = getIntent().getIntExtra("user_id", -1);
        }
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(0, null, this);
    }

    public static void skipFromActivity(Activity activity, int userId) {
        Intent intent = new Intent(activity, MyDraftsActivity.class);
        intent.putExtra("user_id", userId);
        activity.startActivity(intent);
    }

    public static void skipFromFragment(Fragment fragment, int userId) {
        Intent intent = new Intent(fragment.getContext(), MyDraftsActivity.class);
        intent.putExtra("user_id", userId);
        fragment.startActivity(intent);
    }

    private CustomListener.ItemClickListener onItemListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            DraftsModel draftsModel = (DraftsModel) object;
            Intent intent = new Intent(MyDraftsActivity.this, DraftsDetailActivity.class);
            intent.putExtra("topic_id", draftsModel.getId());
            startActivityForResult(intent, SUCCESSFUL_REQUIRE);
        }
    };

    private CustomListener.ItemClickListener onItemLongListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            final DraftsModel draftsModel = (DraftsModel) object;
            sheet = new BottomSheet.Builder(MyDraftsActivity.this).title("草稿箱删除").sheet(R.menu.menu_longlist_bs).listener(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    getContentResolver().delete(ContentUris.withAppendedId(DBContent.DBTopic.CONTENT_URI, draftsModel.getId()), null, null);
                    getSupportLoaderManager().restartLoader(0, null, MyDraftsActivity.this);
                }
            }).limit(R.integer.bs_initial_list_row).build();
            sheet.show();
        }
    };


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, ContentUris.withAppendedId(DBContent.DBTopic.CONTENT_URI, Long.parseLong(SPCache.getString(Constants.USER_ID, "-1"))), null, null, null, null);

    }

    /**
     * @param loader
     * @param data
     */
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.i("cursor", "cursor_address = " + data.toString());
        if (data != null && data.getCount() > 0) {
            nsv.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
            adapter.swapCursor(data);
        } else {
            nsv.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.i("cursor", "onLoaderReset = start" );
        adapter.swapCursor(null);
    }


}
