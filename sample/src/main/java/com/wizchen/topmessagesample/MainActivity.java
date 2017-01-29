package com.wizchen.topmessagesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wizchen.topmessage.TopMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView helloTV = (TextView) findViewById(R.id.hello);

        findViewById(R.id.success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopMessage.showSuccess(MainActivity.this, "This is a sample of success top message.", TopMessage.DURATION.SHORT);
            }
        });

        findViewById(R.id.error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopMessage.showError(MainActivity.this, "This is a sample of error top message.", TopMessage.DURATION.MEDIUM);
            }
        });

        findViewById(R.id.warning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopMessage.showWarning(MainActivity.this, "This is a sample of warning top message.", TopMessage.DURATION.LONG);
            }
        });

        findViewById(R.id.info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopMessage.showInfo(MainActivity.this, "This is a sample of info top message.", TopMessage.DURATION.LONG);
            }
        });

        findViewById(R.id.confirm_and_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopMessage.showWarning(MainActivity.this, "This is a sample of confirm and cancel button top message.", TopMessage.DURATION.LONG, null, new TopMessage.ConfirmCallback() {
                    @Override
                    public void confirmClick(View self) {
                        helloTV.setText("confirm button clicked");
                    }
                }, new TopMessage.CancelCallback() {
                    @Override
                    public void cancelClick(View self) {
                        helloTV.setText("cancel button click");
                    }
                });
            }
        });

        findViewById(R.id.common).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopMessage.showSuccess(MainActivity.this, "This is a sample of common button top message.", TopMessage.DURATION.LONG, new TopMessage.CommonCallback() {
                    @Override
                    public void commonClick(View self) {
                        helloTV.setText("common button clicked");
                    }
                }, null, null);
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
