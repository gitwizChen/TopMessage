package com.wizchen.topmessagesample;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wizchen.topmessage.TopMessage;
import com.wizchen.topmessage.TopMessageManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView helloTV = (TextView) findViewById(R.id.hello);

        findViewById(R.id.success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopMessageManager.showSuccess("This is a sample of success top message.", null, TopMessage.DURATION.SHORT);
            }
        });

        findViewById(R.id.error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopMessageManager.showError("This is a sample of error top message.", null, TopMessage.DURATION.MEDIUM);
            }
        });

        findViewById(R.id.warning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopMessageManager.showWarning("This is a sample of warning top message.", null, TopMessage.DURATION.LONG);
            }
        });

        findViewById(R.id.info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopMessageManager.showInfo("This is a sample of info top message.");
            }
        });

        findViewById(R.id.confirm_and_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopMessageManager.showWarning("This is a sample of confirm and cancel button top message.", null, new TopMessage.ConfirmOrCancelCallback() {
                    @Override
                    public void confirmClick(View self) {
                        helloTV.setText("confirm button clicked");
                    }

                    @Override
                    public void cancelClick(View self) {
                        helloTV.setText("cancel button click");
                    }
                }, "Confirm", "Cancel");
            }
        });

        findViewById(R.id.common).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopMessageManager.showSuccess("This is a sample of common button top message.", null, new TopMessage.CommonCallback() {
                    @Override
                    public void commonClick(View self) {
                        helloTV.setText("common button click");
                    }
                }, "Common");
            }
        });

        findViewById(R.id.input).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopMessageManager.showInfo("请告诉我WI-FI密码多少?", "提示", new TopMessage.SendCallback() {
                    @Override
                    public void send(String content) {
                        helloTV.setText("Input content:" + content);
                    }
                }, "给你", "WI-FI是密码是...", "不要告诉我你家的WI-FI没有密码...");
            }
        });

        findViewById(R.id.toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "This is a toast sample.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
