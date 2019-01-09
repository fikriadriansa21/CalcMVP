package com.example.fikriadriansa21.mvpcalc.utils;

import com.example.fikriadriansa21.mvpcalc.model.Operand;

public class Calculator {

    public String add(Operand first,Operand second){
        double result = getValue(first) + getValue(second);
        return formatResult(result);
    }

    public String substract(Operand first,Operand second){
        double result = getValue(first) - getValue(second);
        return formatResult(result);
    }

    public String multiply(Operand first,Operand second){
        double result = getValue(first) * getValue(second);
        return formatResult(result);
    }

    public String divide(Operand first,Operand second){
        double result = getValue(first) / getValue(second);
        return formatResult(result);
    }

    private String formatResult(Double res) {
        // Limit digits
        double digits = Math.pow(10, Operand.MAX_DECIMAL_DIGITS);
        res = Math.round(res * digits) / digits;

        // Split resulting float
        String result = Double.toString(res);
        String decimals = result.substring(0, result.indexOf("."));
        String fractionals = result.substring(result.indexOf(".") + 1);

        // Remove trailing zeros
        while (fractionals.length() > 0 && fractionals.substring(fractionals.length() - 1).equals("0")) {
            fractionals = fractionals.substring(0, fractionals.length() - 1);
        }

        if (fractionals.length() > 0) {
            // Result has fractionals different than zero - return them!
            return decimals + "." + fractionals;
        } else {
            return decimals;
        }
    }

    private double getValue(Operand operand) {
        return Double.valueOf(operand.getValue());
    }
}
