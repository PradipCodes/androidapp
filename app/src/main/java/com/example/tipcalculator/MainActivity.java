package com.example.tipcalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAmount;
    private Button button15Percent;
    private Button button18Percent;
    private Button button20Percent;
    private TextView textViewResult;
    private TextView textViewTipAmount;
    private Button selectedButton; // To keep track of the selected button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAmount = findViewById(R.id.editTextAmount);
        button15Percent = findViewById(R.id.button15Percent);
        button18Percent = findViewById(R.id.button18Percent);
        button20Percent = findViewById(R.id.button20Percent);
        textViewResult = findViewById(R.id.textViewResult);
        textViewTipAmount = findViewById(R.id.textViewTipAmount);

        // Set all buttons to their default appearance
        setDefaultButtonAppearance();

        button15Percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonSelection(button15Percent);
                calculateTip(0.15);
            }
        });

        button18Percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonSelection(button18Percent);
                calculateTip(0.18);
            }
        });

        button20Percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonSelection(button20Percent);
                calculateTip(0.20);
            }
        });
    }

    private void handleButtonSelection(Button clickedButton) {
        // Deselect the previously selected button
        if (selectedButton != null) {
            selectedButton.setSelected(false);
            setButtonDefaultAppearance(selectedButton);
        }

        // Select the clicked button
        clickedButton.setSelected(true);
        setButtonSelectedAppearance(clickedButton);

        // Update the selectedButton reference
        selectedButton = clickedButton;
    }

    private void calculateTip(double tipPercentage) {
        String amountText = editTextAmount.getText().toString();
        if (!amountText.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountText);
                double tipAmount = amount * tipPercentage;
                double totalAmount = amount + tipAmount;

                textViewResult.setText(String.format("Tip: $%.2f Total: $%.2f", tipAmount, totalAmount));
                textViewTipAmount.setText(String.format("Tip Amount: $%.2f", tipAmount));
            } catch (NumberFormatException e) {
                textViewResult.setText("Invalid input. Please enter a valid amount.");
                textViewTipAmount.setText("");
            }
        } else {
            textViewResult.setText("Amount cannot be empty.");
            textViewTipAmount.setText("");
        }
    }

    // Method to set the default appearance of a button (blue background, white text)
    private void setButtonDefaultAppearance(Button button) {
        button.setBackgroundColor(Color.BLUE);
        button.setTextColor(Color.WHITE);
    }

    // Method to set the selected appearance of a button (green background, black text)
    private void setButtonSelectedAppearance(Button button) {
        button.setBackgroundColor(Color.GREEN);
        button.setTextColor(Color.BLACK);
    }

    // Method to set the default appearance for all buttons
    private void setDefaultButtonAppearance() {
        setButtonDefaultAppearance(button15Percent);
        setButtonDefaultAppearance(button18Percent);
        setButtonDefaultAppearance(button20Percent);
    }
}
