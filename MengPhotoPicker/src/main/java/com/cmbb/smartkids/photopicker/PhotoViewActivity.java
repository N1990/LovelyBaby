package com.cmbb.smartkids.photopicker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cmbb.smartkids.mengbottomsheets.BottomSheet;
import com.cmbb.smartkids.photopicker.widget.MultiTouchViewPager;
import com.cmbb.smartkids.photopicker.widget.indication.CirclePageIndicator;
import com.cmbb.smartkids.photopicker.widget.photoview.PhotoDraweeView;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/19 下午8:13
 */
public class PhotoViewActivity extends AppCompatActivity {

    private static final String TAG = PhotoViewActivity.class.getSimpleName();

    private ArrayList<String> mDrawables = new ArrayList<>();
    private int index;
    private boolean showIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        mDrawables = getIntent().getStringArrayListExtra("Images");
        index = getIntent().getIntExtra("index", 0);
        showIndicator = getIntent().getBooleanExtra("show", true);
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        MultiTouchViewPager viewPager = (MultiTouchViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new DraweePagerAdapter());
        indicator.setViewPager(viewPager);
        viewPager.setCurrentItem(index);
        if (!showIndicator) {
            indicator.setVisibility(View.GONE);
        } else {
            indicator.setVisibility(View.VISIBLE);
        }
    }

    public static void IntentPhotoView(Context context, ArrayList<String> images) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putStringArrayListExtra("Images", images);
        context.startActivity(intent);
    }

    public static void IntentPhotoView(Context context, ArrayList<String> images, int position) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putStringArrayListExtra("Images", images);
        intent.putExtra("index", position);
        context.startActivity(intent);
    }

    public static void IntentPhotoView(Context context, ArrayList<String> images, int position, boolean showIndicator) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putStringArrayListExtra("Images", images);
        intent.putExtra("index", position);
        intent.putExtra("show", showIndicator);
        context.startActivity(intent);
    }

    public static void IntentPhotoView(Context context, String image) {
        ArrayList<String> ls = new ArrayList<>();
        ls.add(image);
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putStringArrayListExtra("Images", ls);
        context.startActivity(intent);
    }

    public class DraweePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mDrawables.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public PhotoDraweeView instantiateItem(ViewGroup viewGroup, final int position) {
            final PhotoDraweeView photoDraweeView = new PhotoDraweeView(viewGroup.getContext());
            final Uri uri;
            if (mDrawables.get(position).contains("file://")) {
                uri = Uri.parse(mDrawables.get(position));
            } else if (mDrawables.get(position).contains("http://") || mDrawables.get(position).contains("https://")) {
                uri = Uri.parse(mDrawables.get(position) + (BuildConfig.DEBUG ? "@1o" : "@1o|watermark=1&object=YXBwL3dhdGVybWFyay93YXRlcm1hcmtfNC5wbmdAMTAwdw&p=9"));
            } else {
                uri = Uri.parse("file://" + mDrawables.get(position));
            }
            photoDraweeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("onClick", "onClick");
                }
            });
            photoDraweeView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //网络图片用于保存
                    if (uri.toString().contains("http")) {
                        BottomSheet sheet = new BottomSheet.Builder(PhotoViewActivity.this)
                                .sheet(R.menu.photo_menu)
                                .listener(new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ImagePipeline imagePipeline = Fresco.getImagePipeline();
                                        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri).build();
                                        final DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, PhotoViewActivity.this);
                                        final DataSubscriber<CloseableReference<CloseableImage>> dataSubscriber = new BaseBitmapDataSubscriber() {
                                            @Override
                                            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                                                Toast.makeText(PhotoViewActivity.this, "保持失败", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            protected void onNewResultImpl(Bitmap bitmap) {
                                                saveBitmap(bitmap, "image_" + bitmap.hashCode() + ".png");
                                            }
                                        };

                                        final Handler handler = new Handler();
                                        dataSource.subscribe(dataSubscriber, new Executor() {
                                            @Override
                                            public void execute(Runnable command) {
                                                handler.post(command);
                                            }
                                        });
                                    }
                                }).build();
                        sheet.show();
                    }
                    return false;
                }
            });
            PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
            controller.setUri(uri);
            controller.setOldController(photoDraweeView.getController());
            controller.setControllerListener(new BaseControllerListener<ImageInfo>() {
                @Override
                public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                    super.onFinalImageSet(id, imageInfo, animatable);
                    if (imageInfo == null) {
                        return;
                    }
                    photoDraweeView.update(imageInfo.getWidth(), imageInfo.getHeight());
                }
            });
            photoDraweeView.setController(controller.build());
            try {
                viewGroup.addView(photoDraweeView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return photoDraweeView;
        }
    }

    public void saveBitmap(Bitmap bm, String picName) {
        Log.e(TAG, "保存图片");
        File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), picName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(PhotoViewActivity.this, "图片保存成功", Toast.LENGTH_SHORT).show();
            // 最后通知图库更新
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + f.getAbsolutePath())));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
