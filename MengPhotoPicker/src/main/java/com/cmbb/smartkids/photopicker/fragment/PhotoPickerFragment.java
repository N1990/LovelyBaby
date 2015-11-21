package com.cmbb.smartkids.photopicker.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.cmbb.smartkids.photopicker.PhotoViewActivity;
import com.cmbb.smartkids.photopicker.R;
import com.cmbb.smartkids.photopicker.adpter.PhotoGridAdapter;
import com.cmbb.smartkids.photopicker.adpter.PopupDirectoryListAdapter;
import com.cmbb.smartkids.photopicker.entity.Photo;
import com.cmbb.smartkids.photopicker.entity.PhotoDirectory;
import com.cmbb.smartkids.photopicker.event.OnPhotoClickListener;
import com.cmbb.smartkids.photopicker.utils.ImageCaptureManager;
import com.cmbb.smartkids.photopicker.utils.MediaStoreHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/20 下午5:20
 */
public class PhotoPickerFragment extends Fragment {

    private ImageCaptureManager mImageCaptureManager;
    private PhotoGridAdapter mPhotoGridAdapter;

    private PopupDirectoryListAdapter mPopupDirectoryListAdapter;
    private List<PhotoDirectory> mPhotoDirectories;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
        Log.i("fragment", "fragment on Create");
        mPhotoDirectories = new ArrayList<>();
        mPhotoGridAdapter = new PhotoGridAdapter(getActivity(), mPhotoDirectories);
        mPopupDirectoryListAdapter = new PopupDirectoryListAdapter(getActivity(), mPhotoDirectories);
        mImageCaptureManager = new ImageCaptureManager(getActivity());
        MediaStoreHelper.getPhotoDirs(getActivity(), new MediaStoreHelper.PhotosResultCallback() {
            @Override
            public void onResultCallback(List<PhotoDirectory> directories) {
                mPhotoGridAdapter.notifyDataSetChanged();
                PhotoPickerFragment.this.mPhotoDirectories.addAll(directories);
                mPopupDirectoryListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_photo_picker, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_photos);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mPhotoGridAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        final Button btSwitchDirectory = (Button) rootView.findViewById(R.id.directory_button);
        final ListPopupWindow listPopupWindow = new ListPopupWindow(getActivity());
        listPopupWindow.setWidth(ListPopupWindow.MATCH_PARENT);
        listPopupWindow.setAnchorView(btSwitchDirectory);
        listPopupWindow.setAdapter(mPopupDirectoryListAdapter);
        listPopupWindow.setModal(true);
        listPopupWindow.setDropDownGravity(Gravity.BOTTOM);
        listPopupWindow.setAnimationStyle(R.style.Animation_AppCompat_DropDownUp);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listPopupWindow.dismiss();
                PhotoDirectory directory = mPhotoDirectories.get(position);
                btSwitchDirectory.setText(directory.getName());
                mPhotoGridAdapter.setCurrentDirectoryIndex(position);
                mPhotoGridAdapter.notifyDataSetChanged();
            }
        });

        mPhotoGridAdapter.setOnPhotoClickListener(new OnPhotoClickListener() {
            @Override
            public void onClick(View v, int position, boolean showCamera) {
                final int index = showCamera ? position - 1 : position;

                ArrayList<String> photos = mPhotoGridAdapter.getCurrentPhotoPaths();

                int[] screenLocation = new int[2];
                v.getLocationOnScreen(screenLocation);
                /*ImagePagerFragment imagePagerFragment = ImagePagerFragment.newInstance(photos, index, screenLocation, v.getWidth(), v.getHeight());
                ((PhotoPickerActivity) getActivity()).addImagePagerFragment(imagePagerFragment);*/

                PhotoViewActivity.IntentPhotoView(getActivity(), photos, index, false);
            }
        });

        mPhotoGridAdapter.setOnCameraClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = mImageCaptureManager.dispatchTakePictureIntent();
                    startActivityForResult(intent, ImageCaptureManager.REQUEST_TAKE_PHOTO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btSwitchDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listPopupWindow.isShowing()) {
                    listPopupWindow.dismiss();
                } else {
                    listPopupWindow.setHeight(Math.round(rootView.getHeight() * 0.8f));
                    listPopupWindow.show();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ImageCaptureManager.REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            mImageCaptureManager.galleryAddPic();
            if (mPhotoDirectories.size() > 0) {
                String path = mImageCaptureManager.getCurrentPhotoPath();
                PhotoDirectory directory = mPhotoDirectories.get(MediaStoreHelper.INDEX_ALL_PHOTOS);
                directory.getPhotos().add(MediaStoreHelper.INDEX_ALL_PHOTOS, new Photo(path.hashCode(), path));
                directory.setCoverPath(path);
                mPhotoGridAdapter.notifyDataSetChanged();
            }
        }
    }

    public PhotoGridAdapter getPhotoGridAdapter() {
        return mPhotoGridAdapter;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        mImageCaptureManager.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        mImageCaptureManager.onRestoreInstanceState(savedInstanceState);
        super.onViewStateRestored(savedInstanceState);
    }

    public ArrayList<String> getSelectedPhotoPaths() {
        return mPhotoGridAdapter.getSelectedPhotoPaths();
    }
}
