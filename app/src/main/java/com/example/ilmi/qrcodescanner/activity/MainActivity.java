package com.example.ilmi.qrcodescanner.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.ilmi.qrcodescanner.R;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private static final int ZXING_CAMERA_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScannerView = new ZXingScannerView(this);
        getZxingCameraPermission();
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void handleResult(Result result) {

        String qrCodeData = result.getText();

        try {
            JSONObject obj = new JSONObject(qrCodeData);

            if (obj.has("nim")) {
                gotoNextActivity(qrCodeData);
            }

            else {
                showErrorMessage();
            }
        }

        catch (JSONException e) {
            e.printStackTrace();
            showErrorMessage();
        }

        mScannerView.resumeCameraPreview(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (ZXING_CAMERA_PERMISSION == 1) {
            mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
            mScannerView.startCamera();          // Start camera on resume
            mScannerView.setAutoFocus(true);
        }

        else {
            Toast.makeText(MainActivity.this, "Permintaan Ditolak, silahkan buka kembali aplikasi anda", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == ZXING_CAMERA_PERMISSION){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setContentView(mScannerView);
            }

            else {
                Toast.makeText(MainActivity.this, "Permintaan Ditolak, Silahkan Buka Kembali Aplikasi Anda", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void getZxingCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            setContentView(mScannerView);
        }

        else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) ) {
                Toast.makeText(MainActivity.this, "Kamera digunakan sebagai scanner barcode pada nametag", Toast.LENGTH_LONG).show();
            }

            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.CAMERA},
                    ZXING_CAMERA_PERMISSION);
        }
    }

    private void showErrorMessage() {
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Kesalahan");
        builder.setMessage("Format QR Code yang diberikan tidak sesuai. Gunakan Format QR Code yang Benar dan Coba Lagi");

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog
                .show();
    }

    private void gotoNextActivity(String qrCodeData) {
        Intent moveIntent = new Intent(MainActivity.this, AthleteDesc.class);
        moveIntent.putExtra(AthleteDesc.EXTRA_NAME, qrCodeData);
        startActivity(moveIntent);
    }
}
