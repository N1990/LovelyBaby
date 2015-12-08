package com.cmbb.smartkids.widget.wheelview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.CustomListener;

/**
 * 项目名称：JavonFZZ-master
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/20 16:15
 */
public class CustomDialogBuilder extends Dialog implements View.OnClickListener {
    private final String TAG = CustomDialogBuilder.class.getSimpleName();
    private LinearLayout llNavigate;
    private View vDivider;
    private final String defTextColor = "#FFFFFFFF";
    /**
     * Dialog动画效果
     */
//    private Effectstype type = null;


    private FrameLayout flContent;

    private TextView tvTitle;

    private TextView tvMessage;


    private TextView tvCancel;

    private TextView tvComfirm;

    private int mDuration = -1;

    private static int mOrientation = 1;
    private boolean isCancelable = true;
    private int windowWidth = 0;
    private int windowHeight = 0;
    private CustomListener.DialogListener onAbolishListener;
    private CustomListener.DialogListener onComfirmListener;
    private volatile static CustomDialogBuilder instance;
    private Context context;

    public CustomDialogBuilder(Context context) {
        super(context);
        init(context);
    }

    public CustomDialogBuilder(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    public CustomDialogBuilder(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        //设置窗体的宽高来控制Dialog的大小x`
        if (windowWidth == 0 || windowHeight == 0) {
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            params.width = windowWidth;
            params.height = windowHeight;
        }
        getWindow().setAttributes((WindowManager.LayoutParams) params);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCanceledOnTouchOutside(isCancelable);
    }

    public static CustomDialogBuilder getInstance(Context context) {

        int ort = context.getResources().getConfiguration().orientation;
        if (mOrientation != ort) {
            mOrientation = ort;
            instance = null;
        }

        if (instance == null || ((Activity) context).isFinishing()) {
            synchronized (CustomDialogBuilder.class) {
                if (instance == null) {
                    instance = new CustomDialogBuilder(context, R.style.dialog_untran);
                }
            }
        }
        return instance;

    }

    private void init(Context context) {
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View root = getLayoutInflater().from(context).inflate(R.layout.dialog_custom, null);
        tvTitle = (TextView) root.findViewById(R.id.tv_custom_dialog_title);
        flContent = (FrameLayout) root.findViewById(R.id.fl_custom_conent);
        tvMessage = (TextView) root.findViewById(R.id.tv_custom_dialog_content);
        llNavigate = (LinearLayout) root.findViewById(R.id.ll_custom_dialog_navigate);
        tvCancel = (TextView) root.findViewById(R.id.tv_custom_dialog_cancel);
        tvCancel.setOnClickListener(this);
        tvComfirm = (TextView) root.findViewById(R.id.tv_custom_dialog_comfirm);
        tvComfirm.setOnClickListener(this);
        vDivider = root.findViewById(R.id.v_custom_dialog_divider);
        setContentView(root);

       /* this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

                mLinearLayoutView.setVisibility(View.VISIBLE);
                if (type == null) {
                    // Dialog默认的动画效果
                    type = Effectstype.Fall;
                }
                start(type);
            }
        });*/
    }

    public void setDialogDismiss() {
        instance = null;
    }

    public void toDefault() {
        tvMessage.setVisibility(View.VISIBLE);
    }

    private void ifNeedDivider() {
        if (tvCancel.getVisibility() == View.VISIBLE && tvComfirm.getVisibility() == View.VISIBLE)
            vDivider.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (v.getId() == R.id.tv_custom_dialog_cancel && onAbolishListener != null) {
            onAbolishListener.onClick(v);
        } else if (v.getId() == R.id.tv_custom_dialog_comfirm && onComfirmListener != null) {
            onComfirmListener.onClick(v);
        }
    }

    public CustomDialogBuilder setDialogWindows(int width, int height) {
        //设置窗体的宽高来控制Dialog的大小
        windowWidth = width;
        windowHeight = height;
        return this;
    }


    public CustomDialogBuilder withMessageMiss(int visibility) {
        tvMessage.setVisibility(visibility);
        return this;
    }


    public CustomDialogBuilder withTitle(CharSequence title) {
        toggleView(tvTitle, title);
        tvTitle.setText(title);
        return this;
    }


    public CustomDialogBuilder withTitleColor(String colorString) {
        tvTitle.setTextColor(Color.parseColor(colorString));
        return this;
    }


    public CustomDialogBuilder withMessage(int textResId) {
        toggleView(tvMessage, textResId);
        tvMessage.setText(textResId);
        return this;
    }


    public CustomDialogBuilder withMessage(CharSequence msg) {
        toggleView(tvMessage, msg);
        tvMessage.setText(msg);
        return this;
    }


    public CustomDialogBuilder withMessageColor(String colorString) {
        tvMessage.setTextColor(Color.parseColor(colorString));
        return this;
    }


    public CustomDialogBuilder withDuration(int duration) {
        this.mDuration = duration;
        return this;
    }


    /*public CustomADialogBuilder setEffect(Effectstype type) {
        this.type = type;
        return this;
    }*/


    public CustomDialogBuilder withBottomResid(int resid, CustomListener.DialogListener onCancelListener) {
        llNavigate.setVisibility(View.VISIBLE);
        tvCancel.setVisibility(View.VISIBLE);
        tvCancel.setVisibility(View.VISIBLE);
        ifNeedDivider();
        tvCancel.setCompoundDrawables(context.getResources().getDrawable(resid), null, null, null);
        tvComfirm.setCompoundDrawables(context.getResources().getDrawable(resid), null, null, null);
        return this;
    }


    public CustomDialogBuilder withCancelText(CharSequence text, CustomListener.DialogListener onCancelListener) {
        llNavigate.setVisibility(View.VISIBLE);
        tvCancel.setVisibility(View.VISIBLE);
        ifNeedDivider();
        if (!TextUtils.isEmpty(text))
            tvCancel.setText(text);
        this.onAbolishListener = onCancelListener;
        return this;
    }


    public CustomDialogBuilder withComfirmText(CharSequence text, CustomListener.DialogListener onComfirmListener) {
        llNavigate.setVisibility(View.VISIBLE);
        tvComfirm.setVisibility(View.VISIBLE);
        ifNeedDivider();
        if (!TextUtils.isEmpty(text))
            tvComfirm.setText(text);
        this.onComfirmListener = onComfirmListener;
        tvComfirm.setText(text);
        return this;
    }


    public CustomDialogBuilder withCustomContentView(int resId, Context context) {
        View customView = View.inflate(context, resId, null);
        if (flContent.getChildCount() > 0) {
            flContent.removeAllViews();
        }
        flContent.addView(customView);
        return this;
    }

    public CustomDialogBuilder withCustomContentView(View view, Context context) {
        flContent.setVisibility(View.VISIBLE);
        if (flContent.getChildCount() > 0) {
            flContent.removeAllViews();
        }
        flContent.addView(view);

        return this;
    }


    public CustomDialogBuilder isCancelableOnTouchOutside(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCanceledOnTouchOutside(cancelable);
        return this;
    }


    public CustomDialogBuilder isCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCancelable(cancelable);
        return this;
    }

    private void toggleView(View view, Object obj) {
        if (obj == null) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void show() {
        super.show();
    }

    /*private void start(Effectstype type) {
        BaseEffects animator = type.getAnimator();
        if (mDuration != -1) {
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(mRelativeLayoutView);
    }*/

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public FrameLayout getCustomContentLayout() {
        return flContent;
    }

}
