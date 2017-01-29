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

    public enum DURATION {
        SHORT,
        MEDIUM,
        LONG
    }

    private static final int MSG_SHOW = 0;
    private static final int MSG_DISMISS = 1;
    private static final int ANIMATE_IN_DURATION = 300;
    private static final int ANIMATE_OUT_DURATION = 200;
    private static final Handler mHandler;

    private ViewGroup mDecorView;
    private View mMessageView;
    private boolean isShow = false;

    static {
        mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case MSG_SHOW:
                        ((TopMessage) message.obj).showView();
                        return true;
                    case MSG_DISMISS:
                        ((TopMessage) message.obj).hideView();
                        return true;
                }
                return false;
            }
        });
    }

    private TopMessage() {
    }

    public static void showSuccess(Activity activity, String message) {
        showSuccess(activity, message, DURATION.MEDIUM);
    }

    public static void showSuccess(Activity activity, String message, DURATION duration) {
        showSuccess(activity, message, duration, null, null, null);
    }

    /**
     * show success message
     *
     * @param activity
     * @param message
     * @param duration
     */
    public static void showSuccess(Activity activity, String message, DURATION duration, CommonCallback commonCallback, ConfirmCallback confirmCallback, CancelCallback cancelCallback) {
        TopMessage topMessage = new TopMessage();
        topMessage.createView(activity, message, activity.getResources().getString(R.string.success), R.color.success, R.drawable.success_light, commonCallback, confirmCallback, cancelCallback);
        topMessage.scheduleTime(duration);
    }

    public static void showError(Activity activity, String message) {
        showError(activity, message, DURATION.MEDIUM);
    }

    public static void showError(Activity activity, String message, DURATION duration) {
        showError(activity, message, duration, null, null, null);
    }

    /**
     * show error message
     *
     * @param activity
     * @param message
     * @param duration
     */
    public static void showError(Activity activity, String message, DURATION duration, CommonCallback commonCallback, ConfirmCallback confirmCallback, CancelCallback cancelCallback) {
        TopMessage topMessage = new TopMessage();
        topMessage.createView(activity, message, activity.getResources().getString(R.string.error), R.color.error, R.drawable.error_light, commonCallback, confirmCallback, cancelCallback);
        topMessage.scheduleTime(duration);
    }

    public static void showWarning(Activity activity, String message) {
        showWarning(activity, message, DURATION.MEDIUM);
    }

    public static void showWarning(Activity activity, String message, DURATION duration) {
        showWarning(activity, message, duration, null, null, null);
    }

    /**
     * show wanring message
     *
     * @param activity
     * @param message
     * @param duration
     */
    public static void showWarning(Activity activity, String message, DURATION duration, CommonCallback commonCallback, ConfirmCallback confirmCallback, CancelCallback cancelCallback) {
        TopMessage topMessage = new TopMessage();
        topMessage.createView(activity, message, activity.getResources().getString(R.string.warning), R.color.warning, R.drawable.warning_light, commonCallback, confirmCallback, cancelCallback);
        topMessage.scheduleTime(duration);
    }

    public static void showInfo(Activity activity, String message) {
        showInfo(activity, message, DURATION.MEDIUM);
    }

    public static void showInfo(Activity activity, String message, DURATION duration) {
        showInfo(activity, message, duration, null, null, null);
    }

    /**
     * show info message
     *
     * @param activity
     * @param message
     * @param duration
     */
    public static void showInfo(Activity activity, String message, DURATION duration, CommonCallback commonCallback, ConfirmCallback confirmCallback, CancelCallback cancelCallback) {
        TopMessage topMessage = new TopMessage();
        topMessage.createView(activity, message, activity.getResources().getString(R.string.info), R.color.info, R.drawable.info_light, commonCallback, confirmCallback, cancelCallback);
        topMessage.scheduleTime(duration);
    }

    /**
     * create the message view in activity
     *
     * @param activity
     * @param message
     * @param type
     * @param backgroundColor
     * @param drawableResId
     * @param commonCallback
     * @param confirmCallback
     * @param cancelCallback
     */

    private void createView(Activity activity,
                            String message,
                            String type,
                            @ColorRes int backgroundColor,
                            @DrawableRes int drawableResId,
                            final CommonCallback commonCallback,
                            final ConfirmCallback confirmCallback,
                            final CancelCallback cancelCallback) {
        if (null == mDecorView) {
            mDecorView = (ViewGroup) activity.getWindow().getDecorView();
        }
        if (null == mMessageView) {
            int messageViewIndex = getMessageViewIndex(mDecorView, "top-message");
            // if activity already has a message view, retrieving it from decorview
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
        final TextView confirmTV = (TextView) mMessageView.findViewById(R.id.confirm);
        final TextView cancelTV = (TextView) mMessageView.findViewById(R.id.cancel);
        final TextView commonTV = (TextView) mMessageView.findViewById(R.id.common);

        rootLL.setPadding(0, BarUtil.getStatusBarHeight(activity), 0, 0);
        rootLL.setBackgroundResource(backgroundColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            rootLL.setElevation(5);
        }
        messageTV.setText(message);
        typeTV.setText(type);
        iconIV.setImageDrawable(activity.getResources().getDrawable(drawableResId));

        if (null != commonCallback) {
            commonTV.setVisibility(View.VISIBLE);
            commonTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHandler.removeCallbacksAndMessages(null);
                    mHandler.sendMessage(mHandler.obtainMessage(MSG_DISMISS, TopMessage.this));
                    commonCallback.commonClick(v);
                }
            });
        } else {
            commonTV.setVisibility(View.GONE);
        }
        if (null != confirmCallback && null != cancelCallback) {
            confirmTV.setVisibility(View.VISIBLE);
            cancelTV.setVisibility(View.VISIBLE);
            confirmTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHandler.removeCallbacksAndMessages(null);
                    mHandler.sendMessage(mHandler.obtainMessage(MSG_DISMISS, TopMessage.this));
                    confirmCallback.confirmClick(v);
                }
            });
            cancelTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHandler.removeCallbacksAndMessages(null);
                    mHandler.sendMessage(mHandler.obtainMessage(MSG_DISMISS, TopMessage.this));
                    cancelCallback.cancelClick(v);
                }
            });
        } else {
            confirmTV.setVisibility(View.GONE);
            cancelTV.setVisibility(View.GONE);
        }
    }

    /**
     * animate when show in
     */
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
                            view.setVisibility(View.VISIBLE);
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

    /**
     * animate when show out
     */
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
                            view.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationCancel(View view) {

                        }
                    }).start();
        }
    }

    /**
     * call to show the message view
     */
    private void showView() {
        if (!isShow) {
            isShow = true;
            animateIn();
        }
    }

    /**
     * call to dismiss the message view
     */
    private void hideView() {
        if (isShow) {
            isShow = false;
            animateOut();
        }
    }

    /**
     * schedule time to dismiss message view after show it
     *
     * @param duration
     */
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

    /**
     * get message view from decor view
     *
     * @param decorView
     * @param messageViewTag
     * @return
     */
    private int getMessageViewIndex(ViewGroup decorView, String messageViewTag) {
        for (int i = 0; i < decorView.getChildCount(); i++) {
            if (messageViewTag.equals(decorView.getChildAt(i).getTag())) {
                return i;
            }
        }
        return -2;
    }

    public interface CommonCallback {
        void commonClick(View self);
    }

    public interface ConfirmCallback {
        void confirmClick(View self);
    }

    public interface CancelCallback {
        void cancelClick(View self);
    }
}
