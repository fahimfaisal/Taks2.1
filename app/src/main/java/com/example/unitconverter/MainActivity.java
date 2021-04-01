package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.digidemic.unitof.UnitOf;
import java.security.PublicKey;

public class MainActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {

    ImageButton buttonWeight, buttonTemp, buttonDistance;
    TextView view1, view2, view3;
    EditText edit;
    Spinner spinner;
    private LinearLayout container;

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
        container= (LinearLayout) findViewById(R.id.textview) ;

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.metrics, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        emptyTextView();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void emptyTextView()
    {
        view1.setText("");
        view2.setText("");
        view3.setText("");
    }



    public void ConvertTemp(View view)
    {
        Double input = Double.parseDouble(edit.getText().toString());
//        Double input = Double.parseDouble(val);
        Double ctof = new UnitOf.Temperature().fromCelsius(input).toFahrenheit();
        Double ctok = new UnitOf.Temperature().fromCelsius(input).toKelvin();



        String text = spinner.getSelectedItem().toString();

        if (text.equals(spinner.getItemAtPosition(1).toString()))
        {


            view1.setText(ctof.toString());
            view2.setText(ctok.toString());
            view3.setText("");



        }

        else
        {

            view1.setText("error");
        }

}



    public void ConvertWeight(View view) {

        Double input = Double.parseDouble(edit.getText().toString());

        Double ktog = new UnitOf.Mass().fromKilograms(input).toGrams();
        Double ktoo = new UnitOf.Mass().fromKilograms(input).toOuncesUS();
        Double ktop = new UnitOf.Mass().fromKilograms(input).toPounds();

        String text = spinner.getSelectedItem().toString();

        if ( text.equals(spinner.getItemAtPosition(2).toString()))
        {

            view1.setText(ktog.toString());
            view2.setText(ktoo.toString());
            view3.setText(ktop.toString());
        }

        else {
            view1.setText("error");

        }

    }

    public void ConvertDistance(View view) {
        Double input = Double.parseDouble(edit.getText().toString());

        Double mtocm = new UnitOf.Length().fromMeters(input).toCentimeters();
        Double mtofeet =new UnitOf.Length().fromMeters(input).toFeet();
        Double ktoi =  new UnitOf.Length().fromMeters(input).toInches();

        String text = spinner.getSelectedItem().toString();

        if (text.equals(spinner.getItemAtPosition(0).toString())) {


            view1.setText(mtocm.toString());
            view2.setText(mtofeet.toString());
            view3.setText(ktoi.toString());
        } else {
            view1.setText("error");
        }

    }
}



