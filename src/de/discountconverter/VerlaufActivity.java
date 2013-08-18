package de.discountconverter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class VerlaufActivity extends Activity{

	List<Entry> list = new ArrayList<Entry>();
	VerlaufDataSource dataSource;
	public static VerlaufActivity self;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.verlauf);
		self = this;
	}
	
	public void show(){
		dataSource = new VerlaufDataSource(getApplicationContext());
		list.clear();
		try {
			dataSource.open();
			list = dataSource.getAllEntries();
			dataSource.close();
		} catch (Exception e) {
			Toast.makeText(this, "Fehler beim Auslesen: "+e.toString(), Toast.LENGTH_LONG).show();
		}
		ArrayAdapter<Entry> adapter = new ArrayAdapter<Entry>(this, android.R.layout.simple_list_item_1, list);
		ListView lview = (ListView)findViewById(R.id.listView1);
		lview.setAdapter(adapter);
	}
	
}
