package de.discountconverter;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class VerlaufDataSource {

	private SQLiteDatabase db;
	private MySQLHelper dbHelper;
	private String[] spalten = {"ID", "PREIS", "RABATT", "ERSPARNIS", "NPREIS"};
	
	public VerlaufDataSource(Context context){
		dbHelper = new MySQLHelper(context);
	}
	
	public void open() throws SQLException{
		db = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		db.close();
	}
	
	public Entry createEntry(double preis, double rabatt, double ersparnis, double nPreis){
		ContentValues values = new ContentValues();
		values.put("PREIS", preis);
		values.put("RABATT", rabatt);
		values.put("ERSPARNIS", ersparnis);
		values.put("NPREIS", nPreis);
		
		long insertID = db.insert("VERLAUF", null, values);
		
		Cursor cursor = db.query("VERLAUF", spalten, "ID = "+insertID, null, null, null, null);
//		cursor.moveToFirst();
		cursor.moveToLast();
		return cursorToEntry(cursor);
	}

	private Entry cursorToEntry(Cursor cursor) {
		Entry entry = new Entry();
		entry.setId(cursor.getLong(0));
		entry.setPreis(cursor.getDouble(1));
		entry.setRabatt(cursor.getDouble(2));
		entry.setErsparnis(cursor.getDouble(3));
		entry.setNeuerPreis(cursor.getDouble(4));
		
		return entry;
		
	}
	
	protected List<Entry> getAllEntries(){
		List<Entry> list = new ArrayList<Entry>();
		list = new ArrayList<Entry>();
		Cursor cursor = db.query("VERLAUF", spalten, null, null, null, null, null);
//		cursor.moveToFirst();
		cursor.moveToLast();
		if(cursor.getCount() == 0) return list;
		
//		while(!cursor.isAfterLast()){ 
//			Entry entry = cursorToEntry(cursor);
//			list.add(entry);
//			cursor.moveToNext();
//		}
		
		while(!cursor.isBeforeFirst()){
			Entry entry = cursorToEntry(cursor);
			list.add(entry);
			cursor.moveToPrevious();
		}
		
		cursor.close();
		
		return list;
	}
}
