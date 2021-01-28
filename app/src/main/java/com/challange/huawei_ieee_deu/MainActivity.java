package com.challange.huawei_ieee_deu;

import android.Manifest;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.huawei.hms.hmsscankit.OnResultCallback;
import com.huawei.hms.hmsscankit.RemoteView;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;

public class MainActivity extends AppCompatActivity {
    private Button scanButton;
    private TextView resultText;
    private final int PERMISSION_REQ_CODE = 111;
    private final int SCAN_REQ_CODE = 222;
    Button codepg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanButton = findViewById(R.id.scanner_button);
        resultText = findViewById(R.id.result_text);
        codepg = (Button) findViewById(R.id.button2);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request for permissions
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQ_CODE);
            }
        });
        codepg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchFor=((TextView) findViewById(R.id.result_text)).getText().toString();
                Intent viewSearch = new Intent(Intent.ACTION_WEB_SEARCH);
                viewSearch.putExtra(SearchManager.QUERY, searchFor);
                startActivity(viewSearch);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQ_CODE && grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MainActivity.this, Second_Activity.class);
            MainActivity.this.startActivityForResult(intent, SCAN_REQ_CODE);
            Log.d("demo", "onRequestPermissionsResult: We have permissions. Lets go to second activity.");
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, Intent data) {
        //receive result after your activity finished scanning
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SCAN_REQ_CODE) {
            String Barcode = data.getStringExtra("SCAN_RESULT");
            resultText.setText("Result: " + Barcode);
            Log.d("demo", "onActivityResult: Scan successful. Scanned code is: " + Barcode);
        }
    }
}

