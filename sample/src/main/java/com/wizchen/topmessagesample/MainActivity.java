package com.wizchen.topmessagesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wizchen.topmessage.TopMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}
