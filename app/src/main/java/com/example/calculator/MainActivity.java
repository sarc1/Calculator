package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    Button btnSubtraction, btnAddition, btnMultiplication, btnDivision, btnAC, btnC, btnPercentage, btnDecimal, btnEquals;
    TextView inputnumber, lastinput;
    boolean  decimal;

    public Calculator calculator = new Calculator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputnumber = findViewById(R.id.inputnumber);
        lastinput = findViewById(R.id.btnlastinputted);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);

        btnAddition = (Button) findViewById(R.id.btnAddition);
        btnSubtraction = (Button) findViewById(R.id.btnSubtraction);
        btnMultiplication = (Button) findViewById(R.id.btnMultiplication);
        btnDivision = (Button) findViewById(R.id.btnDivision);
        btnDecimal = (Button) findViewById(R.id.btnDecimal);
        btnAC = (Button) findViewById(R.id.btnAC);
        btnC = (Button) findViewById(R.id.btnC);
        btnPercentage = (Button) findViewById(R.id.btnPercentage);
        btnEquals = (Button) findViewById(R.id.btnEquals);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastinput.append("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastinput.append("2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastinput.append("3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastinput.append(("4"));
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastinput.append("5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastinput.append("6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastinput.append("7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastinput.append("8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastinput.append("9");
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastinput.append("0");
            }
        });
        btnDivision.setOnClickListener(v -> {
            opChecker("/");
        });

        btnSubtraction.setOnClickListener(v -> {
            opChecker("-");
        });

        btnAddition.setOnClickListener(v -> {
            opChecker("+");
        });

        btnMultiplication.setOnClickListener(v -> {
            opChecker("*");
        });

        btnDecimal.setOnClickListener(v -> {
            if(!decimal) {
                lastinput.append(".");
                decimal = true;
            } else {
                String temp = String.valueOf(lastinput.getText());
                temp = temp.substring(0, temp.length() - 1);
                lastinput.setText(temp);
                decimal = false;
            }
        });


        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = String.valueOf(lastinput.getText());
                String last = input.substring(input.length() - 1, input.length());

                if(last.equals("+") || last.equals("-") || last.equals("/") || last.equals("*")){
                    Context context = getApplicationContext();
                    String invalid = "Error.";
                    inputnumber.setText(invalid);
                }
                else{
                    //lastinput.setText(input);
                    inputnumber.setText(calculator.Eval(input, true));
                }
            }
        });

        btnAC.setOnClickListener(v -> {
            inputnumber.setText("");
            lastinput.setText("");
            decimal = false;
        });

        btnC.setOnClickListener(v -> {
            String temp = String.valueOf(lastinput.getText());
            if(!temp.isEmpty()) {
                if(temp.charAt(temp.length() - 1) == '.') {
                    decimal = false;
                }
                temp = temp.substring(0, temp.length() - 1);
                lastinput.setText(temp);
            }

        });
    }

    private void opChecker(String op) {
        String temp =  String.valueOf(lastinput.getText());
        StringBuilder sb = new StringBuilder();
        if(temp.isEmpty()) {
            return;
        }
        String last = temp.substring(temp.length() - 1, temp.length());

        if(last.equals("/") || last.equals("+") || last.equals("*") || last.equals("-")) {
            temp = temp.substring(0, temp.length() - 1);
        } else {
            decimal = false;
        }
        sb.append(temp);
        sb.append(op);

        lastinput.setText(sb);
        inputnumber.setText(calculator.Eval(temp, false));
    }
}