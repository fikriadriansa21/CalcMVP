package com.example.fikriadriansa21.mvpcalc.calc;

import com.example.fikriadriansa21.mvpcalc.model.Operator;

public class CalcContract {

    interface View {

        void displayOperand(String calculation);

        void displayOperator(String operator);
    }

    interface Presenter {

        void clearCalculation();

        Operator getOperator();

        void appendValue(String value);

        void appendOperator(String operator);

        void performCalculation();
    }
}
