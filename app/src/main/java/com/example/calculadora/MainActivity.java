package com.example.calculadora;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0;
    private double secondOperand = 0;
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Asignar los botones numéricos y operadores
        setupButtonListeners();
    }

    private void setupButtonListeners() {
        // Números
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button0 = findViewById(R.id.button0);

        button1.setOnClickListener(view -> appendNumber("1"));
        button2.setOnClickListener(view -> appendNumber("2"));
        button3.setOnClickListener(view -> appendNumber("3"));
        button4.setOnClickListener(view -> appendNumber("4"));
        button5.setOnClickListener(view -> appendNumber("5"));
        button6.setOnClickListener(view -> appendNumber("6"));
        button7.setOnClickListener(view -> appendNumber("7"));
        button8.setOnClickListener(view -> appendNumber("8"));
        button9.setOnClickListener(view -> appendNumber("9"));
        button0.setOnClickListener(view -> appendNumber("0"));

        // Operadores
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);

        buttonAdd.setOnClickListener(view -> setOperator("+"));
        buttonSubtract.setOnClickListener(view -> setOperator("-"));
        buttonMultiply.setOnClickListener(view -> setOperator("*"));
        buttonDivide.setOnClickListener(view -> setOperator("/"));

        // Botón igual
        Button buttonEquals = findViewById(R.id.buttonEquals);
        buttonEquals.setOnClickListener(view -> calculateResult());

        // Botón de limpiar
        Button buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(view -> clear());
    }

    private void appendNumber(String number) {
        if (isOperatorPressed) {
            currentInput = "";
            isOperatorPressed = false;
        }
        currentInput += number;
        display.setText(currentInput);
    }

    private void setOperator(String op) {
        if (!currentInput.isEmpty()) {
            firstOperand = Double.parseDouble(currentInput);
            operator = op;
            isOperatorPressed = true;
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty()) {
            secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
        }
    }

    private void clear() {
        currentInput = "";
        operator = "";
        firstOperand = 0;
        secondOperand = 0;
        display.setText("");
    }
}
