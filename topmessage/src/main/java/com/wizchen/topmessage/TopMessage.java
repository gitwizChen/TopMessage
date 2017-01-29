package com.wizchen.topmessage;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wizchen.topmessage.util.BarUtil;

/**
 * Author: wizChen
 * Date: 2017-01-28 16:59
 * Description: A beautiful way of message prompting
 */

public class TopMessage {

    private final static Handler mHandler;

    static {
        mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case MSG_SHOW:
                        ((TopMessage)message.obj).showView();
                        return true;
                    case MSG_DISMISS:
                        ((TopMessage)message.obj).hideView();
                        return true;
                }
                return false;
            }
        });
    }

    public enum DURATION {
        SHORT,
        MEDIUM,
        LONG
    }

    private static final int MSG_SHOW = 0;
    private static final int MSG_DISMISS = 1;
    private static final int ANIMATE_IN_DURATION = 300;
    private static final int ANIMATE_OUT_DURATION = 200;

    private ViewGroup mDecorView;
    private View mMessageView;
    private boolean isShow = false;

    private TopMessage() {
    }

    public static void showSuccess(Activity activity, String message, DURATION duration) {
        TopMessage topMessage = new TopMessage();
        topMessage.createView(activity, message, activity.getResources().getString(R.string.success), R.color.success, R.drawable.success_light);
        topMessage.scheduleTime(duration);
    }

    public static void showError(Activity activity, String message, DURATION duration) {
        TopMessage topMessage = new TopMessage();
        topMessage.createView(activity, message, activity.getResources().getString(R.string.error), R.color.error, R.drawable.error_light);
        topMessage.scheduleTime(duration);
    }

    public static void showWarning(Activity activity, String message, DURATION duration) {
        TopMessage topMessage = new TopMessage();
        topMessage.createView(activity, message, activity.getResources().getString(R.string.warning), R.color.warning, R.drawable.warning_light);
        topMessage.scheduleTime(duration);
    }

    public static void showInfo(Activity activity, String message, DURATION duration) {
        TopMessage topMessage = new TopMessage();
        topMessage.createView(activity, message, activity.getResources().getString(R.string.info), R.color.info, R.drawable.info_light);
        topMessage.scheduleTime(duration);
    }

    private void createView(Activity activity,
                            String message,
                            String type,
                            @ColorRes int backgroundColor,
                            @DrawableRes int drawableResId) {
        if (null == mDecorView) {
            mDecorView = (ViewGroup) activity.getWindow().getDecorView();
        }
        if (null == mMessageView) {
            int messageViewIndex = getMessageViewIndex(mDecorView, "top-message");
            if (-2 == messageViewIndex) {
                mMessageView = LayoutInflater.from(activity).inflate(R.layout.message_layout, mDecorView, false);
                mMessageView.setTag("top-message");
                mDecorView.addView(mMessageView, mDecorView.getChildCount());
            } else {
                mMessageView = mDecorView.getChildAt(messageViewIndex);
            }
        }
        mMessageView.setVisibility(View.INVISIBLE);
        final LinearLayout rootLL = (LinearLayout) mMessageView.findViewById(R.id.root);
        final TextView messageTV = (TextView) mMessageView.findViewById(R.id.message);
        final TextView typeTV = (TextView) mMessageView.findViewById(R.id.type);
        final ImageView iconIV = (ImageView) mMessageView.findViewById(R.id.icon);

        rootLL.setPadding(0, BarUtil.getStatusBarHeight(activity), 0, 0);
        rootLL.setBackgroundResource(backgroundColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            rootLL.setElevation(5);
        }
        messageTV.setText(message);
        typeTV.setText(type);
        iconIV.setImageDrawable(activity.getResources().getDrawable(drawableResId));
    }

    void animateIn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            ViewCompat.setTranslationY(mMessageView, -mMessageView.getHeight());
            ViewCompat.setAlpha(mMessageView, 0.6f);
            ViewCompat.animate(mMessageView)
                    .alpha(1.0f)
                    .translationY(0)
                    .setInterpolator(new FastOutSlowInInterpolator())
                    .setDuration(ANIMATE_IN_DURATION)
                    .setListener(new ViewPropertyAnimatorListener() {
                        @Override
                        public void onAnimationStart(View view) {
                            mMessageView.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(View view) {

                        }

                        @Override
                        public void onAnimationCancel(View view) {

                        }
                    }).start();
        }
    }

    void animateOut() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            ViewCompat.animate(mMessageView)
                    .alpha(0.6f)
                    .translationY(-mMessageView.getHeight())
                    .setInterpolator(new FastOutSlowInInterpolator())
                    .setDuration(ANIMATE_OUT_DURATION)
                    .setListener(new ViewPropertyAnimatorListener() {
                        @Override
                        public void onAnimationStart(View view) {

                        }

                        @Override
                        public void onAnimationEnd(View view) {
                            mMessageView.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationCancel(View view) {

                        }
                    }).start();
        }
    }

    private void showView() {
        if (!isShow) {
            isShow = true;
            animateIn();
        }
    }

    private void hideView() {
        if (isShow) {
            isShow = false;
            animateOut();
        }
    }

    private void scheduleTime(DURATION duration) {
        int timeOut = 1000;
        switch (duration) {
            case LONG:
                timeOut = 2000;
                break;
            case MEDIUM:
                timeOut = 1500;
                break;
            case SHORT:
            default:
                timeOut = 1000;
                break;
        }
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SHOW, TopMessage.this));
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_DISMISS, TopMessage.this), timeOut);
    }

    private int getMessageViewIndex(ViewGroup decorView, String messageViewTag) {
        for (int i = 0; i < decorView.getChildCount(); i++) {
            if (messageViewTag.equals(decorView.getChildAt(i).getTag())) {
                return i;
            }
        }
        return -2;
    }
}
