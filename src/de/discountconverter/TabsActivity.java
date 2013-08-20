package de.discountconverter;

import android.R.id;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class TabsActivity extends TabActivity{
	
	LocalActivityManager mlam;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        mlam = new LocalActivityManager(this, false); 
        mlam.dispatchCreate(savedInstanceState);
        
        
        TabHost tabHost = (TabHost)findViewById(id.tabhost);
        tabHost.setup(mlam);
         
        // Tab for Discounter
        TabSpec rechner = tabHost.newTabSpec("Rechner");
        rechner.setIndicator("Rabatt");
        Intent discountIntent = new Intent(this, DiscountActivity.class);
        rechner.setContent(discountIntent);
        
        // Tab for Database
        TabSpec verlauf = tabHost.newTabSpec("Verlauf");        
        verlauf.setIndicator("Verlauf");
        Intent verlaufIntent = new Intent(this, VerlaufActivity.class);
        verlauf.setContent(verlaufIntent);
        
        //Tab for Fractions
        TabSpec bruch = tabHost.newTabSpec("Bruch");
        bruch.setIndicator("Brüche");
        Intent bruchIntent = new Intent(this, FractionActivity.class);
        bruch.setContent(bruchIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(rechner); // Adding rechner tab
        tabHost.addTab(bruch); //Adding bruch tab
        tabHost.addTab(verlauf); // Adding verlauf tab
        
        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                if(tabId.equals("Verlauf")){
                    VerlaufActivity.self.show();
                }
            }
        });
        
    }
	
	

}
