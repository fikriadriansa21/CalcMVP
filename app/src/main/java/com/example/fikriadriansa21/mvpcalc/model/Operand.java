package com.example.fikriadriansa21.mvpcalc.model;

public class Operand {

    public static final String EMPTY_VALUE = "0";
    public static final String ErrorValue = "Error";
    public static final int MAX_LENGTH = 10;
    public static final int MAX_DECIMAL_DIGITS = 1;

    private String mValue = EMPTY_VALUE;

    public String getValue() {
        return mValue;
    }

    public void setValue(String mValue) {
        this.mValue = mValue;
    }

    public void appendVal(String val){
        if (mValue.equals(EMPTY_VALUE)){
            mValue = val;
        }else {
            mValue += val;
        }
    }

    public void reset(){
        mValue = EMPTY_VALUE;
    }

}
