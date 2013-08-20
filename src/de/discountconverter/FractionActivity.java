package de.discountconverter;

import java.text.DecimalFormat;

import de.discountconverter.R.id;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FractionActivity extends Activity {
	
	Button ok, clear, operator;
	EditText zähler1, zähler2, nenner1, nenner2;
	TextView ergBruch, ergDezi;
	final int[] ops = {R.string.plus, R.string.minus, R.string.mal, R.string.geteilt}; 
	static int i = 0;
	DecimalFormat f = new DecimalFormat("#0.00");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bruchrechner);
		
		init();
		
		operator.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(i == ops.length) i = 0;
				else {
					operator.setText(ops[i]);
					i++;
				}	
			}
		});
		
		clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				zähler1.setText(null);
				zähler2.setText(null);
				nenner1.setText(null);
				nenner2.setText(null);
			}
		});
		
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int z1 = Integer.parseInt(zähler1.getText().toString());
				int n1 = Integer.parseInt(nenner1.getText().toString());
				int z2 = Integer.parseInt(zähler2.getText().toString());
				int n2 = Integer.parseInt(nenner2.getText().toString());
				
				
				Bruch bruch1 = new Bruch(z1, n1);
				Bruch bruch2 = new Bruch(z2, n2);
				
				switch (i) {
					case 0:{
						add(bruch1, bruch2);
						break;
					}
					case 1:{
						sub(bruch1, bruch2);
						break;
					}
					case 2:{
						times(bruch1, bruch2);
						break;
					}
					case 3:{
						div(bruch1, bruch2);
						break;
					}
				}
			}
		});
		
	}

	private void init() {
		ok = (Button)findViewById(id.buttonok2);
		clear = (Button)findViewById(id.buttonclear2);
		operator = (Button)findViewById(id.buttonoperator);
		zähler1 = (EditText)findViewById(id.editTextZaehler1);
		zähler2 = (EditText)findViewById(id.editTextZaehler2);
		nenner1 = (EditText)findViewById(id.editTextNenner1);
		nenner2 = (EditText)findViewById(id.editTextNenner2);
		ergBruch = (TextView)findViewById(id.textViewErgebnisBruch);
		ergDezi = (TextView)findViewById(id.textViewErgebnisDezi);
	}
	
	public void add(Bruch b1, Bruch b2){
		int ergZ = b2.getNenner() * b1.getZähler() + b1.getNenner() * b2.getZähler();
		int ergN = b1.getNenner() * b2.getNenner();
		double erg = ergZ / ergN;
		String ergB = ergZ + " / " + ergN;
		String ergD = String.valueOf(f.format(erg));
		ergBruch.setText(ergB);
		ergDezi.setText(ergD);
	}
	
	public void sub(Bruch b1, Bruch b2){
		int ergZ = b2.getNenner() * b1.getZähler() - b1.getNenner() * b2.getZähler();
		int ergN = b1.getNenner() * b2.getNenner();
		double erg = ergZ / ergN;
		String ergB = ergZ + " / " + ergN;
		String ergD = String.valueOf(f.format(erg));
		ergBruch.setText(ergB);
		ergDezi.setText(ergD);
	}
	
	public void times(Bruch b1, Bruch b2){
		double ergZ = b1.getZähler() * b2.getZähler();
		double ergN = b1.getNenner() * b2.getNenner();
		double erg = ergZ / ergN;
		String ergB = (int)ergZ + " / " + (int)ergN;
		String ergD = String.valueOf(f.format(erg));
		ergBruch.setText(ergB);
		ergDezi.setText(ergD);
	}
	
	public void div(Bruch b1, Bruch b2){
		Bruch tmp = new Bruch(b2.getNenner(), b2.getZähler());
		times(b1, tmp);
	}

}
