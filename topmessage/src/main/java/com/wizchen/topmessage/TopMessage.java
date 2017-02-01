package com.wizchen.topmessage;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wizchen.topmessage.util.BarUtil;
import com.wizchen.topmessage.util.TopActivityManager;

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
    private MessageLayout mMessageView;
    private boolean mIsShow = false;
    private CommonCallback mCommonCallback;
    private ConfirmOrCancelCallback mConfirmOrCancelCallback;
    private SendCallback mSendCallback;

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

    /**
     * create the message view in current activity
     *
     * @param message                 the message
     * @param title                   the big title
     * @param backgroundColor         the background of message
     * @param drawableResId           the icon image
     * @param commonCallback          if you need to set a common button, then you should get
     *                                callback from the event
     * @param commonButtonText        the common button's title
     * @param confirmOrCancelCallback if you need to set a confirm and a cancel button, then you
     *                                should get callback from the event
     * @param confirmButtonText       the confirm button's title
     * @param cancelButtonText        the cancel button's title
     * @param sendCallback            if you need to set a send button and the input area, then you
     *                                should get callback from the event
     * @param sendButtonText          the send button's title
     * @param inputHint               if it is not equal null, the first should be the default hint
     *                                message and if it has a second element, it will be the hint
     *                                that will be showed when the user clicks submit though he inputs nothing
     */
    protected void createView(String message,
                              String title,
                              @ColorRes int backgroundColor,
                              @DrawableRes int drawableResId,
                              final CommonCallback commonCallback, String commonButtonText,
                              final ConfirmOrCancelCallback confirmOrCancelCallback, String confirmButtonText, String cancelButtonText,
                              final SendCallback sendCallback, String sendButtonText, final String... inputHint) {
        this.mCommonCallback = commonCallback;
        this.mConfirmOrCancelCallback = confirmOrCancelCallback;
        this.mSendCallback = sendCallback;
        Activity currentActivity = TopActivityManager.getInstance().getCurrentActivity();
        if (null == mDecorView) {
            mDecorView = (ViewGroup) currentActivity.getWindow().getDecorView();
        }
        if (null == mMessageView) {
            int messageViewIndex = getMessageViewIndex(mDecorView);
            // if activity already has a message view, retrieving it from decorview
            if (-2 != messageViewIndex) {
                mDecorView.removeViewAt(messageViewIndex);
            }
            mMessageView = (MessageLayout) LayoutInflater.from(currentActivity).inflate(R.layout.message_layout, mDecorView, false);
            mDecorView.addView(mMessageView, mDecorView.getChildCount());
        }
        mMessageView.setVisibility(View.INVISIBLE);

        // find all widgets
        final LinearLayout rootLL = (LinearLayout) mMessageView.findViewById(R.id.root);
        final TextView messageTV = (TextView) mMessageView.findViewById(R.id.message);
        final TextView titleTV = (TextView) mMessageView.findViewById(R.id.title);
        final ImageView iconIV = (ImageView) mMessageView.findViewById(R.id.icon);
        final TextView confirmTV = (TextView) mMessageView.findViewById(R.id.confirm);
        final TextView cancelTV = (TextView) mMessageView.findViewById(R.id.cancel);
        final TextView commonTV = (TextView) mMessageView.findViewById(R.id.common);
        final RelativeLayout inputAreaRL = (RelativeLayout) mMessageView.findViewById(R.id.input_area);
        final EditText inputET = (EditText) mMessageView.findViewById(R.id.input);
        final TextView sendTV = (TextView) mMessageView.findViewById(R.id.send);

        rootLL.setPadding(0, BarUtil.getStatusBarHeight(currentActivity), 0, 0);
        rootLL.setBackgroundResource(backgroundColor);
        inputAreaRL.setBackgroundResource(backgroundColor);
        messageTV.setText(message);
        titleTV.setText(title);
        iconIV.setImageDrawable(currentActivity.getResources().getDrawable(drawableResId));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mMessageView.setElevation(5);
        }

        final ViewGroup.LayoutParams lp = rootLL.getLayoutParams();
        if (lp instanceof CoordinatorLayout.LayoutParams && null == commonCallback && null == confirmOrCancelCallback && null == sendCallback) {
            final Behavior behavior = new Behavior();
            behavior.setStartAlphaSwipeDistance(0.1f);
            behavior.setEndAlphaSwipeDistance(0.7f);
            behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);
            behavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
                @Override
                public void onDismiss(View view) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mMessageView.setElevation(0);
                    }
                }

                @Override
                public void onDragStateChanged(int state) {
                    switch (state) {
                        case SwipeDismissBehavior.STATE_DRAGGING:
                        case SwipeDismissBehavior.STATE_SETTLING:
                            // If the view is being dragged or settling, cancel the timeout
                            cancelAllSchedule();
                            break;
                        case SwipeDismissBehavior.STATE_IDLE:
                            // If the view has been released and is idle, restore the timeout
                            restoreSchedule(1000);
                            break;
                    }
                }
            });
            ((CoordinatorLayout.LayoutParams) lp).setBehavior(behavior);
        }

        // for common button
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
            commonTV.setText(commonButtonText);
        } else {
            commonTV.setVisibility(View.GONE);
        }

        // for confirm and cancel button
        if (null != confirmOrCancelCallback) {
            confirmTV.setVisibility(View.VISIBLE);
            cancelTV.setVisibility(View.VISIBLE);
            confirmTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHandler.removeCallbacksAndMessages(null);
                    mHandler.sendMessage(mHandler.obtainMessage(MSG_DISMISS, TopMessage.this));
                    confirmOrCancelCallback.confirmClick(v);
                }
            });
            cancelTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHandler.removeCallbacksAndMessages(null);
                    mHandler.sendMessage(mHandler.obtainMessage(MSG_DISMISS, TopMessage.this));
                    confirmOrCancelCallback.cancelClick(v);
                }
            });
            confirmTV.setText(confirmButtonText);
            cancelTV.setText(cancelButtonText);
        } else {
            confirmTV.setVisibility(View.GONE);
            cancelTV.setVisibility(View.GONE);
        }

        // for send button and input area
        if (null != sendCallback) {
            inputAreaRL.setVisibility(View.VISIBLE);
            final InputMethodManager imm = (InputMethodManager) currentActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputHint.length > 0) {
                inputET.setHint(inputHint[0]);
            }
            sendTV.setText(sendButtonText);
            sendTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String content = inputET.getText().toString().trim();
                    if ("".equals(content)) {
                        if (inputHint.length > 1) {
                            inputET.setHint(inputHint[1]);
                        } else {
                            inputET.setHint("You must input something!");
                        }
                        inputET.setHintTextColor(v.getResources().getColor(R.color.hint_warning));
                        return;
                    }
                    mHandler.removeCallbacksAndMessages(null);
                    mHandler.sendMessage(mHandler.obtainMessage(MSG_DISMISS, TopMessage.this));
                    // hide the soft keyboard
                    imm.hideSoftInputFromWindow(inputET.getWindowToken(), 0);
                    sendCallback.send(content);
                }
            });
            // show the soft keyboard automatically; request the focus on the edittext so user can
            // input directly
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            inputET.requestFocus();
            mHandler.removeCallbacksAndMessages(null);
        } else {
            inputAreaRL.setVisibility(View.GONE);
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
        if (!mIsShow) {
            mIsShow = true;
            animateIn();
        }
    }

    /**
     * call to dismiss the message view
     */
    private void hideView() {
        if (mIsShow) {
            mIsShow = false;
            animateOut();
        }
    }

    /**
     * schedule time to dismiss message view after show it
     *
     * @param duration the duration
     */
    protected void scheduleTime(DURATION duration) {
        int timeOut;
        if (null != duration) {
            switch (duration) {
                case LONG:
                    timeOut = 2000;
                    break;
                case MEDIUM:
                    timeOut = 1500;
                    break;
                case SHORT:
                    timeOut = 1000;
                    break;
                default:
                    timeOut = 0;
                    break;
            }
        } else {
            timeOut = 0;
        }
        cancelAllSchedule();
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SHOW, TopMessage.this));
        restoreSchedule(timeOut);
    }

    /**
     * get message view from decor view
     *
     * @param decorView the activity decor view
     * @return
     */
    private int getMessageViewIndex(ViewGroup decorView) {
        for (int i = 0; i < decorView.getChildCount(); i++) {
            if (decorView.getChildAt(i) instanceof MessageLayout) {
                return i;
            }
        }
        return -2;
    }

    /**
     * the behavior when user just swipe the message
     */
    private final class Behavior extends SwipeDismissBehavior<View> {
        @Override
        public boolean canSwipeDismissView(@NonNull View view) {
            return view instanceof LinearLayout;
        }

        @Override
        public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent event) {
            // to make sure if user want to make some interaction with the message,
            // then we cancel all messages in the handler
            if (parent.isPointInChildBounds(child, (int) event.getX(), (int) event.getY())) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        cancelAllSchedule();
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        restoreSchedule(1000);
                        break;

                }
            }
            return super.onInterceptTouchEvent(parent, child, event);
        }
    }

    /**
     * restore the schedule
     *
     * @param timeOut the time that the message should dismiss
     */
    private void restoreSchedule(int timeOut) {
        if (null == mCommonCallback && null == mConfirmOrCancelCallback && null == mSendCallback) {
            mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_DISMISS, TopMessage.this), timeOut);
        }
    }

    /**
     * cancel all the message on the handler
     */
    private void cancelAllSchedule() {
        mHandler.removeCallbacksAndMessages(null);
    }

    /* * * * * * * * *
     * INTERFACES
     * * * * * * * * *
     */

    public interface CommonCallback {
        void commonClick(View self);
    }

    public interface ConfirmOrCancelCallback {
        void confirmClick(View self);

        void cancelClick(View self);
    }

    public interface SendCallback {
        void send(String content);
    }
}
