package com.wizchen.topmessage;

import android.app.Activity;

/**
 * Author: wizChen
 * Date: 2017-01-30 19:34
 * Description: the manager that helps to show message of all kinds
 */

public class TopMessageManager {

    /**
     * show success message with default title and medium duration
     *
     * @param message the message
     */
    public static void showSuccess(String message) {
        showSuccess(message, null);
    }

    /**
     * show success message with custom duration
     *
     * @param message the message
     * @param title   the big title
     */
    public static void showSuccess(String message, String title) {
        showSuccess(message, title, TopMessage.DURATION.MEDIUM);
    }

    /**
     * show success message with custom duration and custom title
     *
     * @param message  the message
     * @param title    the big title
     * @param duration the duration
     */
    public static void showSuccess(String message, String title, TopMessage.DURATION duration) {
        showSuccess(message, duration, title, null, null, null, null, null, null, null);
    }

    /**
     * show success message with a common button
     *
     * @param message          the message
     * @param title            the big title
     * @param commonCallback   if you need to set a common button, then you should get
     *                         callback from the event
     * @param commonButtonText the common button's title
     */
    public static void showSuccess(String message, String title,
                                   TopMessage.CommonCallback commonCallback, String commonButtonText) {
        showSuccess(message, null, title, commonCallback, commonButtonText,
                null, null, null,
                null, null);
    }

    /**
     * show success message with a confirm button and a cancel button
     *
     * @param message                 the message
     * @param title                   the big title
     * @param confirmOrCancelCallback if you need to set a confirm and a cancel button, then you
     *                                should get callback from the event
     * @param confirmButtonText       the confirm button's title
     * @param cancelButtonText        the cancel button's title
     */
    public static void showSuccess(String message, String title,
                                   TopMessage.ConfirmOrCancelCallback confirmOrCancelCallback, String confirmButtonText, String cancelButtonText) {
        showSuccess(message, null, title, null, null,
                confirmOrCancelCallback, confirmButtonText, cancelButtonText,
                null, null);
    }

    /**
     * show success message with something user need to input
     *
     * @param message        the message
     * @param title          the big title
     * @param sendCallback   if you need to set a send button and the input area, then you
     *                       should get callback from the event
     * @param sendButtonText the send button's title
     * @param inputHint      if it is not equal null, the first should be the default hint
     *                       message and if it has a second element, it will be the hint
     *                       that will be showed when the user clicks submit though he inputs nothing
     */
    public static void showSuccess(String message, String title,
                                   TopMessage.SendCallback sendCallback, String sendButtonText, String... inputHint) {
        showSuccess(message, null, title,
                null, null,
                null, null, null,
                sendCallback, sendButtonText, inputHint);
    }

    /**
     * show success message completely
     *
     * @param message                 the message
     * @param duration                the duration
     * @param title                   the big title
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
    private static void showSuccess(String message, TopMessage.DURATION duration, String title,
                                    TopMessage.CommonCallback commonCallback, String commonButtonText,
                                    TopMessage.ConfirmOrCancelCallback confirmOrCancelCallback, String confirmButtonText, String cancelButtonText,
                                    TopMessage.SendCallback sendCallback, String sendButtonText, String... inputHint) {
        if (null == title || "".equals(title)) {
            title = TopApplication.getInstance().getResources().getString(R.string.success);
        }
        TopMessage topMessage = new TopMessage();
        topMessage.createView(message, title,
                R.color.success, R.drawable.success_light, commonCallback, commonButtonText,
                confirmOrCancelCallback, confirmButtonText, cancelButtonText,
                sendCallback, sendButtonText, inputHint);
        topMessage.scheduleTime(duration);
    }

    /**
     * show error message with default title and medium duration
     *
     * @param message the message
     */
    public static void showError(String message) {
        showError(message, null);
    }

    /**
     * show error message with custom duration
     *
     * @param message the message
     * @param title   the big title
     */
    public static void showError(String message, String title) {
        showError(message, title, TopMessage.DURATION.MEDIUM);
    }

    /**
     * show error message with custom duration and custom title
     *
     * @param message  the message
     * @param title    the big title
     * @param duration the duration
     */
    public static void showError(String message, String title, TopMessage.DURATION duration) {
        showError(message, duration, title, null, null, null, null, null, null, null);
    }

    /**
     * show error message with a common button
     *
     * @param message          the message
     * @param title            the big title
     * @param commonCallback   if you need to set a common button, then you should get
     *                         callback from the event
     * @param commonButtonText the common button's title
     */
    public static void showError(String message, String title,
                                 TopMessage.CommonCallback commonCallback, String commonButtonText) {
        showError(message, null, title, commonCallback, commonButtonText,
                null, null, null,
                null, null);
    }

    /**
     * show error message with a confirm button and a cancel button
     *
     * @param message                 the message
     * @param title                   the big title
     * @param confirmOrCancelCallback if you need to set a confirm and a cancel button, then you
     *                                should get callback from the event
     * @param confirmButtonText       the confirm button's title
     * @param cancelButtonText        the cancel button's title
     */
    public static void showError(String message, String title,
                                 TopMessage.ConfirmOrCancelCallback confirmOrCancelCallback, String confirmButtonText, String cancelButtonText) {
        showError(message, null, title, null, null,
                confirmOrCancelCallback, confirmButtonText, cancelButtonText,
                null, null);
    }

    /**
     * show error message with something user need to input
     *
     * @param message        the message
     * @param title          the big title
     * @param sendCallback   if you need to set a send button and the input area, then you
     *                       should get callback from the event
     * @param sendButtonText the send button's title
     * @param inputHint      if it is not equal null, the first should be the default hint
     *                       message and if it has a second element, it will be the hint
     *                       that will be showed when the user clicks submit though he inputs nothing
     */
    public static void showError(String message, String title,
                                 TopMessage.SendCallback sendCallback, String sendButtonText, String... inputHint) {
        showError(message, null, title,
                null, null,
                null, null, null,
                sendCallback, sendButtonText, inputHint);
    }

    /**
     * show error message completely
     *
     * @param message                 the message
     * @param duration                the duration
     * @param title                   the big title
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
    private static void showError(String message, TopMessage.DURATION duration, String title,
                                  TopMessage.CommonCallback commonCallback, String commonButtonText,
                                  TopMessage.ConfirmOrCancelCallback confirmOrCancelCallback, String confirmButtonText, String cancelButtonText,
                                  TopMessage.SendCallback sendCallback, String sendButtonText, String... inputHint) {
        if (null == title || "".equals(title)) {
            title = TopApplication.getInstance().getResources().getString(R.string.error);
        }
        TopMessage topMessage = new TopMessage();
        topMessage.createView(message, title,
                R.color.error, R.drawable.error_light, commonCallback, commonButtonText,
                confirmOrCancelCallback, confirmButtonText, cancelButtonText,
                sendCallback, sendButtonText, inputHint);
        topMessage.scheduleTime(duration);
    }

    /**
     * show warning message with default title and medium duration
     *
     * @param message the message
     */
    public static void showWarning(String message) {
        showWarning(message, null);
    }

    /**
     * show warning message with custom duration
     *
     * @param message the message
     * @param title   the big title
     */
    public static void showWarning(String message, String title) {
        showWarning(message, title, TopMessage.DURATION.MEDIUM);
    }

    /**
     * show warning message with custom duration and custom title
     *
     * @param message  the message
     * @param title    the big title
     * @param duration the duration
     */
    public static void showWarning(String message, String title, TopMessage.DURATION duration) {
        showWarning(message, duration, title, null, null, null, null, null, null, null);
    }

    /**
     * show warning message with a common button
     *
     * @param message          the message
     * @param title            the big title
     * @param commonCallback   if you need to set a common button, then you should get
     *                         callback from the event
     * @param commonButtonText the common button's title
     */
    public static void showWarning(String message, String title,
                                   TopMessage.CommonCallback commonCallback, String commonButtonText) {
        showWarning(message, null, title, commonCallback, commonButtonText,
                null, null, null,
                null, null);
    }

    /**
     * show warning message with a confirm button and a cancel button
     *
     * @param message                 the message
     * @param title                   the big title
     * @param confirmOrCancelCallback if you need to set a confirm and a cancel button, then you
     *                                should get callback from the event
     * @param confirmButtonText       the confirm button's title
     * @param cancelButtonText        the cancel button's title
     */
    public static void showWarning(String message, String title,
                                   TopMessage.ConfirmOrCancelCallback confirmOrCancelCallback, String confirmButtonText, String cancelButtonText) {
        showWarning(message, null, title, null, null,
                confirmOrCancelCallback, confirmButtonText, cancelButtonText,
                null, null);
    }

    /**
     * show warning message with something user need to input
     *
     * @param message        the message
     * @param title          the big title
     * @param sendCallback   if you need to set a send button and the input area, then you
     *                       should get callback from the event
     * @param sendButtonText the send button's title
     * @param inputHint      if it is not equal null, the first should be the default hint
     *                       message and if it has a second element, it will be the hint
     *                       that will be showed when the user clicks submit though he inputs nothing
     */
    public static void showWarning(String message, String title,
                                   TopMessage.SendCallback sendCallback, String sendButtonText, String... inputHint) {
        showWarning(message, null, title,
                null, null,
                null, null, null,
                sendCallback, sendButtonText, inputHint);
    }

    /**
     * show warning message completely
     *
     * @param message                 the message
     * @param duration                the duration
     * @param title                   the big title
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
    private static void showWarning(String message, TopMessage.DURATION duration, String title,
                                    TopMessage.CommonCallback commonCallback, String commonButtonText,
                                    TopMessage.ConfirmOrCancelCallback confirmOrCancelCallback, String confirmButtonText, String cancelButtonText,
                                    TopMessage.SendCallback sendCallback, String sendButtonText, String... inputHint) {
        if (null == title || "".equals(title)) {
            title = TopApplication.getInstance().getResources().getString(R.string.warning);
        }
        TopMessage topMessage = new TopMessage();
        topMessage.createView(message, title,
                R.color.warning, R.drawable.warning_light, commonCallback, commonButtonText,
                confirmOrCancelCallback, confirmButtonText, cancelButtonText,
                sendCallback, sendButtonText, inputHint);
        topMessage.scheduleTime(duration);
    }

    /**
     * show info message with default title and medium duration
     *
     * @param message the message
     */
    public static void showInfo(String message) {
        showInfo(message, null);
    }

    /**
     * show info message with custom duration
     *
     * @param message the message
     * @param title   the big title
     */
    public static void showInfo(String message, String title) {
        showInfo(message, title, TopMessage.DURATION.MEDIUM);
    }

    /**
     * show info message with custom duration and custom title
     *
     * @param message  the message
     * @param title    the big title
     * @param duration the duration
     */
    public static void showInfo(String message, String title, TopMessage.DURATION duration) {
        showInfo(message, duration, title, null, null, null, null, null, null, null);
    }

    /**
     * show info message with a common button
     *
     * @param message          the message
     * @param title            the big title
     * @param commonCallback   if you need to set a common button, then you should get
     *                         callback from the event
     * @param commonButtonText the common button's title
     */
    public static void showInfo(String message, String title,
                                TopMessage.CommonCallback commonCallback, String commonButtonText) {
        showInfo(message, null, title, commonCallback, commonButtonText,
                null, null, null,
                null, null);
    }

    /**
     * show info message with a confirm button and a cancel button
     *
     * @param message                 the message
     * @param title                   the big title
     * @param confirmOrCancelCallback if you need to set a confirm and a cancel button, then you
     *                                should get callback from the event
     * @param confirmButtonText       the confirm button's title
     * @param cancelButtonText        the cancel button's title
     */
    public static void showInfo(String message, String title,
                                TopMessage.ConfirmOrCancelCallback confirmOrCancelCallback, String confirmButtonText, String cancelButtonText) {
        showInfo(message, null, title, null, null,
                confirmOrCancelCallback, confirmButtonText, cancelButtonText,
                null, null);
    }

    /**
     * show info message with something user need to input
     *
     * @param message        the message
     * @param title          the big title
     * @param sendCallback   if you need to set a send button and the input area, then you
     *                       should get callback from the event
     * @param sendButtonText the send button's title
     * @param inputHint      if it is not equal null, the first should be the default hint
     *                       message and if it has a second element, it will be the hint
     *                       that will be showed when the user clicks submit though he inputs nothing
     */
    public static void showInfo(String message, String title,
                                TopMessage.SendCallback sendCallback, String sendButtonText, String... inputHint) {
        showInfo(message, null, title,
                null, null,
                null, null, null,
                sendCallback, sendButtonText, inputHint);
    }

    /**
     * show info message completely
     *
     * @param message                 the message
     * @param duration                the duration
     * @param title                   the big title
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
    private static void showInfo(String message, TopMessage.DURATION duration, String title,
                                 TopMessage.CommonCallback commonCallback, String commonButtonText,
                                 TopMessage.ConfirmOrCancelCallback confirmOrCancelCallback, String confirmButtonText, String cancelButtonText,
                                 TopMessage.SendCallback sendCallback, String sendButtonText, String... inputHint) {
        if (null == title || "".equals(title)) {
            title = TopApplication.getInstance().getResources().getString(R.string.info);
        }
        TopMessage topMessage = new TopMessage();
        topMessage.createView(message, title,
                R.color.info, R.drawable.info_light, commonCallback, commonButtonText,
                confirmOrCancelCallback, confirmButtonText, cancelButtonText,
                sendCallback, sendButtonText, inputHint);
        topMessage.scheduleTime(duration);
    }
}
