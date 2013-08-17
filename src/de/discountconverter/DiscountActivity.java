package de.discountconverter;


import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import de.discountconverter.R.id;

public class DiscountActivity extends Activity  {

	VerlaufDataSource dataSource;
	EditText preis, rabatt;
	Button clear, ok;
	TextView ersparnis, neuerPreis;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabelle);
		
		dataSource = new VerlaufDataSource(getApplicationContext());
		
		preis = (EditText)findViewById(id.editTextPreis);
		rabatt = (EditText)findViewById(id.editTextRabatt);
		
		clear = (Button)findViewById(id.buttonclear);
		clear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buttonClear();
			}
		});
		
		ok = (Button)findViewById(id.buttonok);
		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buttonOK();
			}
		});
		
		ersparnis = (TextView)findViewById(id.textViewErsparnisResult);
		neuerPreis = (TextView)findViewById(id.textViewNeuerPreisResult);
	}


	public void buttonOK(){
		
		try {
			
			//holt sich Eingabe, konvertiert diese als String und parst sich den double Wert raus
			String preisString = preis.getText().toString();
			double preis = Double.parseDouble(preisString);
			
			//analog zu preis
			String rabattString = rabatt.getText().toString();
			int rabattInt = Integer.parseInt(rabattString);
			
			if(preis * rabattInt == 0){
				Toast.makeText(this, "Eine Eingabe fehlt!", Toast.LENGTH_LONG).show();
			}
			
			DecimalFormat f = new DecimalFormat("#0.00");
			//rundet ersparnis auf zwei Nachkommastellen
			double ersparnisD = ((double)Math.round((preis*rabattInt/100)*100))/100;
			double nPreis = ((double)Math.round((preis - ersparnisD)*100))/100;
			
			
			//schreibt ersparnis ist das TextView-Feld
			String spartString = String.valueOf(f.format(ersparnisD));
			ersparnis.setText(spartString+" €");
			
			//schreibt neuen Preis in das enrspr. Felds
			String neuerPreisString = String.valueOf(f.format(nPreis));
			neuerPreis.setText(neuerPreisString+" €");
		
			//Datenbank-Eintrag
			dataSource.open();
			dataSource.createEntry(preis, rabattInt, ersparnisD, nPreis);
			dataSource.close();
			
				
			//Tastatur ausblenden	
			InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			input.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				
			} catch (NumberFormatException e) {
				Toast.makeText(this, "Falsche Eingabe: "+e.toString(), Toast.LENGTH_LONG);	
			} catch(Exception e){
				Toast.makeText(this, "Fehler beim Eintragen: "+e.toString(), Toast.LENGTH_LONG).show();
			}
	}
	
	public void buttonClear(){
		//Tastatur ausblenden	
		InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		input.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		
		preis.setText("");
		rabatt.setText("");
		ersparnis.setText("");
		neuerPreis.setText("");
		
	}
	
}

