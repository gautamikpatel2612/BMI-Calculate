package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText age;
    private EditText feet;
    private EditText inches;
    private EditText weight;
    private Button calculate;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setUpButtonClickListener();
    }

    private void findViews() {
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        age = findViewById(R.id.edit_text_age);
        feet = findViewById(R.id.edit_text_feet);
        inches = findViewById(R.id.edit_text_inches);
        weight = findViewById(R.id.edit_text_weight);
        calculate = findViewById(R.id.button_calculate);
        result = findViewById(R.id.textView_result);
    }

    private void setUpButtonClickListener() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "clicked on Calculate",
                        Toast.LENGTH_SHORT).show();
                calculateBmi();
            }
        });
    }

    private void calculateBmi() {
        String feetText = feet.getText().toString();
        String inchesText = inches.getText().toString();
        String weightText = weight.getText().toString();
        // its just for knowledge... we need to put formula here to count BMI
        //result.setText("Age: " +ageText+ ", Feet: " +feetText+ ", Inches: " +inchesText+ ", Weight: " +weightText);
        //make them in int
      // int age = Integer.parseInt(ageText);
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);
        // calculate in math
        int totalInches = (feet * 12) + inches;
        //Height in meters is the inches multiplied by 0.0254
        double heightInMeters = totalInches * 0.0254;
        //BMI formula = weight in kg / height in meters squared
        double bmi = weight / (heightInMeters * heightInMeters);
        //we must convert the decimal/double into a String for our TextView
        //String bmiTextResult = String.valueOf(bmi);

      //  displyResult(bmi); // after bmiResult we need male female //use it in >=18 age
        String ageText = age.getText().toString();
        int age = Integer.parseInt(ageText);

        if(age >= 18){
            displyResult(bmi);
        }else if (age <= 18){
            displayGuidance(bmi);
        }
    }

    private void displyResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);
        //result.setText(bmiTextResult);
        String fullResultText;
        if(bmi < 18.5){
            //underweight
            fullResultText = bmiTextResult + " - You are underWeight";
        }else if (bmi > 25){
            //overweight
            fullResultText = bmiTextResult + " - You are overWeight";
        }else{
            //healthy weight
            fullResultText = bmiTextResult + " - You are a healthy Weight";
        }
        result.setText(fullResultText);
    }


    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultText;
        if(maleButton.isChecked()){
            fullResultText = bmiTextResult + "- As you are under 18, please consult with your doctor for the healthy range for boys";
        }else if(femaleButton.isChecked()){
            fullResultText = bmiTextResult + "- As you are under 18, please consult with your doctor for the healthy range for girls";
        }else{
            fullResultText = bmiTextResult + "- As you are under 18, please consult with your doctor for the healthy range";
        }
        result.setText(fullResultText);
    }



}