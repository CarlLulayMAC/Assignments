package com.example.admin.myapplication;

import android.content.Intent;
import android.graphics.pdf.PdfRenderer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
        else
            Timber.plant(new NoLoggingTree());
    }

    public void onScanBarcode(View view) {
        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity
    }

    public void onReadPdf(View view) throws IOException {
        // create a new renderer
        PdfHelper pdfHelper = new PdfHelper(this);
        pdfHelper.openPdf();
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Timber.d("No Barcode Scanned");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Timber.d("Scanned: " + result.getContents());
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
            Timber.d("No Result");
        }
    }
}
