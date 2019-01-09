package com.example.fikriadriansa21.mvpcalc.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.fikriadriansa21.mvpcalc.R;
import com.example.fikriadriansa21.mvpcalc.model.Operand;
import com.example.fikriadriansa21.mvpcalc.utils.Calculator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements CalcContract.View{

    private CalcContract.Presenter mPresenter;

    @BindView(R.id.txtv_display_calculation)
    TextView mCalculationView;

    @BindView(R.id.txtv_display_operator)
    TextView mOperatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);

        mPresenter = new CalcPresenter(new Calculator(), this, new Operand(), new Operand());
    }

    @Override
    public void displayOperand(String calculation) {
        mCalculationView.setText(calculation);
    }

    @Override
    public void displayOperator(String operator) {
        mOperatorView.setText(operator);
    }

    @OnClick({R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9})
    public void numberButtonClicked(Button button) {
        mPresenter.appendValue((String) button.getText());
    }

    @OnClick({R.id.btn_plus, R.id.btn_minus, R.id.btn_multiply, R.id.btn_divide})
    public void operatorButtonClicked(Button button) {
        mPresenter.appendOperator((String) button.getText());
    }

    @OnClick(R.id.btn_clear)
    public void clearButtonClicked(Button button) {
        mPresenter.clearCalculation();
    }

    @OnClick(R.id.btn_equals)
    public void equalsButtonClicked(Button button) {
        mPresenter.performCalculation();
    }
}
