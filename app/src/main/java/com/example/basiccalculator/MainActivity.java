package com.example.basiccalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private StringBuilder history = new StringBuilder(); // Store history of calculations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Buttons
        Button b1 = findViewById(R.id.b1);
        Button b2 = findViewById(R.id.b2);
        Button b3 = findViewById(R.id.b3);
        Button b4 = findViewById(R.id.b4);
        Button b5 = findViewById(R.id.b5);
        Button b6 = findViewById(R.id.b6);
        Button b7 = findViewById(R.id.b7);
        Button b8 = findViewById(R.id.b8);
        Button b9 = findViewById(R.id.b9);
        Button b0 = findViewById(R.id.b0);
        Button badd = findViewById(R.id.bplus);
        Button bsub = findViewById(R.id.bsub);
        Button bmul = findViewById(R.id.bmultiple);
        Button bdiv = findViewById(R.id.bdiv);
        Button bopen = findViewById(R.id.bopen);   // Button for '('
        Button bclose = findViewById(R.id.bclose); // Button for ')'
        Button bequal = findViewById(R.id.bequal);
        Button bdot = findViewById(R.id.bdot);
        Button bc = findViewById(R.id.bc);
        Button bac = findViewById(R.id.bac);
        Button bhistory = findViewById(R.id.bhistory); // History button

        EditText et = findViewById(R.id.edit);

        // Number Buttons
        View.OnClickListener numberListener = v -> {
            Button button = (Button) v;
            et.setText(et.getText().toString() + button.getText().toString());
        };

        b1.setOnClickListener(numberListener);
        b2.setOnClickListener(numberListener);
        b3.setOnClickListener(numberListener);
        b4.setOnClickListener(numberListener);
        b5.setOnClickListener(numberListener);
        b6.setOnClickListener(numberListener);
        b7.setOnClickListener(numberListener);
        b8.setOnClickListener(numberListener);
        b9.setOnClickListener(numberListener);
        b0.setOnClickListener(numberListener);

        // Parentheses Buttons
        bopen.setOnClickListener(view -> et.setText(et.getText().toString() + "("));
        bclose.setOnClickListener(view -> et.setText(et.getText().toString() + ")"));

        // Clear Buttons
        bc.setOnClickListener(view -> et.setText(""));
        bac.setOnClickListener(view -> et.setText(""));

        // Operator Buttons
        badd.setOnClickListener(view -> et.setText(et.getText().toString() + "+"));
        bsub.setOnClickListener(view -> et.setText(et.getText().toString() + "-"));
        bmul.setOnClickListener(view -> et.setText(et.getText().toString() + "*"));
        bdiv.setOnClickListener(view -> et.setText(et.getText().toString() + "/"));

        // Dot Button
        bdot.setOnClickListener(view -> {
            String currentText = et.getText().toString();
            et.setText(currentText + ".");
        });

        // Equals Button
        bequal.setOnClickListener(view -> {
            String expression = et.getText().toString();
            try {
                double result = evaluateExpression(expression);
                et.setText(String.valueOf(result));

                // Append the result to the history
                history.append(expression).append(" = ").append(result).append("\n");

            } catch (Exception e) {
                et.setText("Error");
            }
        });

        // History Button - Launch SecondActivity to show history
        bhistory.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("history", history.toString());
            startActivity(intent);
        });
    }

    // Function to evaluate a mathematical expression with parentheses
    private double evaluateExpression(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        char[] tokens = expression.toCharArray();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') continue;

            // If the character is a number
            if (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < tokens.length && (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.')) {
                    sb.append(tokens[i++]);
                }
                values.push(Double.parseDouble(sb.toString()));
                i--; // Correct index after loop
            }
            // If the character is an opening parenthesis
            else if (tokens[i] == '(') {
                operators.push(tokens[i]);
            }
            // If the character is a closing parenthesis
            else if (tokens[i] == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop();
            }
            // If the character is an operator
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!operators.isEmpty() && hasPrecedence(tokens[i], operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(tokens[i]);
            }
        }

        // Apply remaining operators
        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    // Helper method to check operator precedence
    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }

    // Helper method to apply an operator
    private double applyOperator(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
        }
        return 0;
    }
}
