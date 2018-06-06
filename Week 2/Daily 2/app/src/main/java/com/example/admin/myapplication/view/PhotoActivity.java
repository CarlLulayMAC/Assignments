package com.example.admin.myapplication.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.Utils.Constants;

import java.security.spec.ECField;

public class PhotoActivity extends AppCompatActivity {

    Button photoBtn;
    ImageView imgView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText etShareText;
    private EditText etCallNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        photoBtn = findViewById(R.id.btnPhoto);
        imgView = findViewById(R.id.imageView);
        etShareText = findViewById(R.id.etShareText);
        etCallNumber = findViewById(R.id.etPhoneInput);
    }


    public void takePhoto(View view) {
        Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (photoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(photoIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imageBitmap);
        }
    }

    public void shareText(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Constants.Key.SHARE_TEXT, etShareText.getText().toString());
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.putExtra(Intent.EXTRA_TEXT , etShareText.getText().toString());

        intent.setType("text/plain");
        startActivity(intent);
    }

    public void callNumber(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + etCallNumber.getText().toString()));
        startActivity(intent);
    }
}
