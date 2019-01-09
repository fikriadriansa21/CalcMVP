package com.example.fikriadriansa21.mvpcalc.model;

public enum Operator {
    EMPTY(""),
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private String operator;

    Operator(String operator){
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator;
    }

    public static Operator getOperator(String operator){
        Operator op = Operator.EMPTY;

        switch (operator){
            case "+" :
                op = PLUS;
                break;
            case "-" :
                op = MINUS;
                break;
            case "*" :
                op = MULTIPLY;
                break;
            case "/" :
                op = DIVIDE;
                break;
        }

        return op;
    }
}
