package com.example.fikriadriansa21.mvpcalc.calc;

import com.example.fikriadriansa21.mvpcalc.model.Operand;
import com.example.fikriadriansa21.mvpcalc.model.Operator;
import com.example.fikriadriansa21.mvpcalc.utils.Calculator;

public class CalcPresenter implements CalcContract.Presenter{

    private Calculator mCalculator;
    private CalcContract.View mView;

    private Operand mCurrentOperand;
    private Operand mPrevOperand;
    private Operator mOperator;
    private boolean hasLastInputOp;
    private boolean hasLastInputEquals;
    private boolean isInError;

    public CalcPresenter(Calculator calculator,
                         CalcContract.View view,
                         Operand currentOp,
                         Operand previousOp){

        mCalculator = calculator;
        mView = view;

        mCurrentOperand = currentOp;
        mPrevOperand = previousOp;
        resetCalculator();
        updateDisplay();
    }


    @Override
    public void clearCalculation() {
        resetCalculator();
        updateDisplay();
    }

    @Override
    public Operator getOperator() {
        return mOperator;
    }

    @Override
    public void appendValue(String value) {
        if (hasLastInputOp) {
            // Last input was an operator - start a new operand
            mPrevOperand.setValue(mCurrentOperand.getValue());
            mCurrentOperand.reset();
        } else if (hasLastInputEquals) {
            // Last input was calculate - start a new calculation
            resetCalculator();
        }

        // Value should only be appended if the operand size is below the maximum operand size
        if (mCurrentOperand.getValue().length() < Operand.MAX_LENGTH) {
            mCurrentOperand.appendVal(value);
            hasLastInputOp = false;
            hasLastInputEquals = false;
            isInError = false;
            updateDisplay();
        }
    }

    @Override
    public void appendOperator(String operator) {
        if (!isInError) {
            if (mOperator != Operator.EMPTY && !hasLastInputOp) {
                // Previous operator exists - perform partical calculation
                performCalculation();

                // When the partial calculation has led to an error state, stop here
                if (isInError) {
                    return;
                }
            }

            mOperator = Operator.getOperator(operator);
            hasLastInputOp = true;
            updateDisplay();
        }
    }

    @Override
    public void performCalculation() {
        String result = "";

        switch (mOperator) {
            case PLUS:
                result = mCalculator.add(mPrevOperand, mCurrentOperand);
                break;
            case MINUS:
                result = mCalculator.substract(mPrevOperand, mCurrentOperand);
                break;
            case MULTIPLY:
                result = mCalculator.multiply(mPrevOperand, mCurrentOperand);
                break;
            case DIVIDE:
                // Check for division by zero
                if (!mCurrentOperand.getValue().equals(Operand.EMPTY_VALUE)) {
                    result = mCalculator.divide(mPrevOperand, mCurrentOperand);
                }
                break;
            default:
                result = mCurrentOperand.getValue();
        }

        if (result.equals("") || result.length() > Operand.MAX_LENGTH) {
            switchToErrorState();
        } else {
            mCurrentOperand.setValue(result);
        }

        // Reset the previous operand and operator
        mPrevOperand.reset();
        mOperator = Operator.EMPTY;
        hasLastInputEquals = true;
        updateDisplay();

    }

    private void switchToErrorState() {
        mCurrentOperand.setValue(Operand.ErrorValue);
        isInError = true;
    }

    private void resetCalculator() {
        mCurrentOperand.reset();
        mPrevOperand.reset();
        hasLastInputEquals = false;
        hasLastInputOp = false;
        isInError = false;
        mOperator = Operator.EMPTY;
    }

    private void updateDisplay() {
        mView.displayOperand(mCurrentOperand.getValue());
        mView.displayOperator(mOperator.toString());
    }
}
