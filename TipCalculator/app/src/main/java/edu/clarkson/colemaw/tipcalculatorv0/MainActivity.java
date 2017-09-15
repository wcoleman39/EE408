package edu.clarkson.colemaw.tipcalculatorv0;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatProperty;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private TipCalculator tipCalc;
    public NumberFormat money = NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipCalc = new TipCalculator(0.17f, 100.0f);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks on the 'calculate' button */
    public void calculate(View v)
    {
        Log.w("MainActivity", "v = " + v);
        EditText billEditTest =
                (EditText) findViewById(R.id.amount_bill);
        EditText tipEditText =
                (EditText) findViewById(R.id.amount_tip_precent);
        String billString = billEditTest.getText().toString();
        String tipString = tipEditText.getText().toString();

        TextView tipTextView =
                (TextView) findViewById(R.id.amount_tip);
        TextView totalTextView =
                (TextView) findViewById(R.id.amount_total);
        try
        {
            //convert billString and tipString to floats
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);

            //update the model
            tipCalc.setBill(billAmount);
            tipCalc.setTip(.01f * tipPercent);

            //ask model to calculate tip and total amounts
            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();

            //update the view with formatted tip and total amounts
            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));
        }

        catch (NumberFormatException nfe)
        {
            //pop up and alert view here
        }
    }
}
