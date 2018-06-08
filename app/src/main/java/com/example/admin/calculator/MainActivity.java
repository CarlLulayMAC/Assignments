package com.example.admin.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Double result;
    private String inputString;
    private char operator;

    private boolean lastInputWasNumber;
    private String valueString;
    private Double inputValue;
//    private Double result;

    private TextView inputView;
    private TextView resultView;
    //TODO: Add decimal functionality

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.lastInputWasNumber = false;
        this.inputString = "";
        this.valueString = "";
        this.inputValue = null;
        this.result = null;

        this.resultView = findViewById(R.id.resultView);
        this.inputView = findViewById(R.id.inputView);
    }

    public void onNumberClick(View view) {
        // If the operator is the null character, then we know the user is entering a new
        // numerical value and we need to clear the previous results and inputString
        if (this.operator == '\u0000' )
            this.clear();
        // building the inputString string that will be displayed to the user
        this.inputString = this.inputString + ((Button) view).getText();
        this.inputView.setText(this.inputString);
        this.valueString = this.valueString + ((Button) view).getText();
        this.inputValue = Double.parseDouble(this.valueString);
        this.lastInputWasNumber = true;
    }

    public void onOperatorClick(View view) {
        if (inputString.length() > 0) {
            if (this.lastInputWasNumber) {
                this.evaluate();
                this.inputString = this.inputString + ((Button) view).getText();
                this.inputValue = null;
                this.result = Double.parseDouble(this.valueString);
                this.valueString = "";
            } else {
                //Swapping operator with last used operator;
                this.inputString = this.inputString.substring(0, this.inputString.length() - 1);
                this.inputString = this.inputString + ((Button) view).getText();
            }
            this.operator = ((Button) view).getText().charAt(0);
            this.lastInputWasNumber = false;
            this.inputView.setText(this.inputString);
        }
    }

    public void onEqualsClick(View view) {
        this.evaluate();
        if (!this.lastInputWasNumber) {
            this.inputString = this.inputString.substring(0, this.inputString.length() - 1);
        }
    }

    public void onClearClicked(View view) {
        this.clear();
    }

    private void evaluate() {
        if (this.inputValue != null && this.result != null && this.operator != '\u0000') {
            if (this.operator == '+')
                this.result = this.inputValue + this.result;
            else if (this.operator == '-')
                this.result = this.result - inputValue;
            else if (this.operator == '*')
                this.result = this.inputValue * result;
            else if (this.operator == '/')
                this.result = this.result / this.inputValue;
            this.resultView.setText(this.result.toString());
            this.valueString = this.result.toString();
            this.inputValue = this.result;
            // In the event that an operator key is used next, we are treating the result
            // as if it were a number inputString
            this.lastInputWasNumber = true;
        }
        // Updating display
        if (this.result != null) {
            this.inputString = "" + this.result;
            this.inputView.setText(this.inputString);
        }
        //setting operator to null
        this.operator = '\u0000';
    }

    private void clear(){
        this.inputString = "";
        this.valueString = "";
        this.inputValue = null;
        this.result = null;
        this.operator = '\u0000';
        this.inputView.setText("");
        this.resultView.setText("");
        this.lastInputWasNumber = false;
    }
}
