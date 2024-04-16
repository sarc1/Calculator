package com.example.calculator;

import java.util.Stack;

public class Calculator {
    public String Eval(String s, boolean checkPrev){
        if(s.length() == 0 || s == null){
            return null;
        }

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operations = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
                    sb.append(s.charAt(i));
                    i++;
                }
                i--;
                numbers.push(Double.parseDouble(sb.toString()));
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                if(checkPrev) {
                    while (!operations.isEmpty() && hasPrev(c, operations.peek())) {
                        numbers.push(applyOp(operations.pop(), numbers.pop(), numbers.pop()));
                    }
                } else {
                    while (!operations.isEmpty()) {
                        numbers.push(applyOp(operations.pop(), numbers.pop(), numbers.pop()));
                    }
                }
                operations.push(c);
            }
        }

        while (!operations.isEmpty()) {
            numbers.push(applyOp(operations.pop(), numbers.pop(), numbers.pop()));
        }

        double res = numbers.pop();
        if((res == Math.floor(res)) && !Double.isInfinite(res)) {
            int resInt = (int) res;
            return Integer.toString(resInt);
        }
        return Double.toString(res);
    }

    private double applyOp(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Cannot be divided by 0 ");
                }
                return a / b;
        }
        return 0;
    }

    private boolean hasPrev(char op1, char op2) {
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
}

