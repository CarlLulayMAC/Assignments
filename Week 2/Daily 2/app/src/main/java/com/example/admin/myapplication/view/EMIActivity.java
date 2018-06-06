package com.example.admin.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.admin.myapplication.R;

import static java.lang.Integer.parseInt;

public class EMIActivity extends AppCompatActivity {

    private EditText etAmount;
    private TextView tvEmiValue;
    public static final String TAG = "EMI ACTIVITY";
    private SeekBar seekbar;
    private TextView tvMonths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi);
        etAmount = findViewById(R.id.etMonthlyAmount);
        tvEmiValue = findViewById(R.id.tvEmiAmount);
        seekbar = findViewById(R.id.seekBarEmi);
        tvMonths = findViewById(R.id.tvMonths);
        initializeListeners();
        updateDisplay();
    }

    private void initializeListeners() {
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateDisplay();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateDisplay();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void updateDisplay() {
        String rateString = etAmount.getText().toString();
        int numMonths = seekbar.getProgress();
        if(rateString.length() == 0)
            tvEmiValue.setText("EMI: ");
        else {
            int rate = parseInt(rateString);
            tvEmiValue.setText("EMI: " + (numMonths * rate));
        }
        tvMonths.setText("Months: " + numMonths);
    }
}
