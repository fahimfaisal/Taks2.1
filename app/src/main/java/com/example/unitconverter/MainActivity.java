package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.digidemic.unitof.UnitOf;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class MainActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {

    ImageButton buttonWeight, buttonTemp, buttonDistance;
    TextView view1, view2, view3;
    EditText edit;
    Spinner spinner;
    Context error;
    Context novalue;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonWeight = (ImageButton) findViewById(R.id.weight);
        buttonTemp = (ImageButton) findViewById(R.id.temp);
        buttonDistance = (ImageButton) findViewById(R.id.distance);
        view1 = (TextView) findViewById(R.id.textView1);
        view2 = (TextView) findViewById(R.id.textView2);
        view3 = (TextView) findViewById(R.id.textView3);
        edit = (EditText) findViewById(R.id.input);
        error  = getApplicationContext();
        novalue = getApplicationContext();
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.metrics, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        emptyTextView();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private double round(double value, int places)
    {

        BigDecimal bd;
        bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    public void ConvertTemp(View view)
    {

        String text = spinner.getSelectedItem().toString();

        if(edit.getText().toString().isEmpty())
        {
            CharSequence empty = "Please Enter a Value";
            Toast.makeText(novalue, empty, Toast.LENGTH_SHORT).show();
        }

        else
        {


            if(text.equals(spinner.getItemAtPosition(1).toString()))
            {

                Double input = Double.parseDouble(edit.getText().toString());

                Double ctof = round(new UnitOf.Temperature().fromCelsius(input).toFahrenheit(),2);
                Double ctok = round( new UnitOf.Temperature().fromCelsius(input).toKelvin(),2);


                view1.setText(ctof.toString() + " Fahrenheit");
                view2.setText(ctok.toString() + " Kelvin");
                view3.setText("");
            }
            else
            {
                showError();
            }

        }

    }



    public void ConvertWeight(View view)
    {


        String text = spinner.getSelectedItem().toString();

        if(edit.getText().toString().isEmpty())
        {
            CharSequence empty = "Please Enter a Value";
            Toast.makeText(novalue, empty, Toast.LENGTH_SHORT).show();
        }

        else
        {


            if(text.equals(spinner.getItemAtPosition(2).toString()))
            {

                Double input = Double.parseDouble(edit.getText().toString());

                Double ktog = round(new UnitOf.Mass().fromKilograms(input).toGrams(),2);
                Double ktoo = round(new UnitOf.Mass().fromKilograms(input).toOuncesUS(),2);
                Double ktop = round(new UnitOf.Mass().fromKilograms(input).toPounds(),2);

                view1.setText(ktog.toString() + " Gram");
                view2.setText(ktoo.toString() + " Ounce(Oz)");
                view3.setText(ktop.toString() + " Pound(lb)");
            }
            else
            {
                showError();
            }

        }

    }

    public void ConvertDistance(View view)
    {

        String text = spinner.getSelectedItem().toString();

        if(edit.getText().toString().isEmpty())
        {
            CharSequence empty = "Please Enter a Value";
            Toast.makeText(novalue, empty, Toast.LENGTH_SHORT).show();
        }

        else
        {


            if(text.equals(spinner.getItemAtPosition(0).toString()))
            {

                Double input = Double.parseDouble(edit.getText().toString());

                Double mtocm = round(new UnitOf.Length().fromMeters(input).toCentimeters(),2);
                Double mtofeet =round(new UnitOf.Length().fromMeters(input).toFeet(),2);
                Double mtoi = round(new UnitOf.Length().fromMeters(input).toInches(), 2);


                view1.setText(mtocm.toString() + " Centimetre");
                view2.setText(mtofeet.toString() + " Feet");
                view3.setText(mtoi.toString() + " Inch");
            }
            else
            {
               showError();
            }

         }


    }


    public void emptyTextView()
    {
        view1.setText("");
        view2.setText("");
        view3.setText("");
    }

    public void showError()
    {
        emptyTextView();

        CharSequence showmsg = "Please Select The Correct Conversion Icon";

        Toast.makeText(error, showmsg, Toast.LENGTH_SHORT).show();
    }

}



