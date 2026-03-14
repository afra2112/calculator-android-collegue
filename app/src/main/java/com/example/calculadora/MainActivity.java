package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay, tvHistory;
    double num1 = 0, num2 = 0, result = 0;
    String operator = "";
    boolean newInput = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);
        tvHistory = findViewById(R.id.tvHistory);

        // Hola Andrés te quedo super chevere la interfaz añadí simplemente la funcionalidad de los números, la resta y división

        int[] numIds = {R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,
                R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,
                R.id.btn8,R.id.btn9};
        for (int id : numIds) {
            findViewById(id).setOnClickListener(v -> {
                Button b = (Button) v;
                if (newInput) { tvDisplay.setText(""); newInput = false; }
                String current = tvDisplay.getText().toString();
                if (current.equals("0")) tvDisplay.setText(b.getText());
                else tvDisplay.setText(current + b.getText());
            });
        }


        findViewById(R.id.btnDot).setOnClickListener(v -> {
            String current = tvDisplay.getText().toString();
            if (!current.contains(".")) tvDisplay.setText(current + ".");
        });

        // Limpiar
        findViewById(R.id.btnClear).setOnClickListener(v -> {
            tvDisplay.setText("0");
            tvHistory.setText("");
            num1 = 0; num2 = 0; operator = "";
        });

        // Suma
        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            num1 = Double.parseDouble(tvDisplay.getText().toString());
            operator = "+";
            tvHistory.setText(num1 + " +");
            newInput = true;
        });

        // Resta
        findViewById(R.id.btnSubtract).setOnClickListener(v -> {
            num1 = Double.parseDouble(tvDisplay.getText().toString());
            operator = "-";
            tvHistory.setText(num1 + " -");
            newInput = true;
        });

        // Multiplicacion
        findViewById(R.id.btnMultiply).setOnClickListener(v -> {
            num1 = Double.parseDouble(tvDisplay.getText().toString());
            operator = "*";
            tvHistory.setText(num1 + " ×");
            newInput = true;
        });

        // División
        findViewById(R.id.btnDivide).setOnClickListener(v -> {
            num1 = Double.parseDouble(tvDisplay.getText().toString());
            operator = "/";
            tvHistory.setText(num1 + " ÷");
            newInput = true;
        });

        // Igual
        findViewById(R.id.btnEquals).setOnClickListener(v -> {
            num2 = Double.parseDouble(tvDisplay.getText().toString());
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/":
                    if (num2 == 0) { tvDisplay.setText("Error"); return; }
                    result = num1 / num2;
                    break;
                default: return;
            }

            tvHistory.setText(num1 + " " + operator + " " + num2 + " =");
            tvDisplay.setText(result % 1 == 0 ?
                    String.valueOf((int) result) : String.valueOf(result));
            newInput = true;
        });
    }
}