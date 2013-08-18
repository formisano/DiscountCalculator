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
        //TabHost tabHost = getTabHost();
        tabHost.setup(mlam);
         
        // Tab for Discounter
        TabSpec rechner = tabHost.newTabSpec("Rechner");
        rechner.setIndicator("Rabattrechner");
        final Intent discountIntent = new Intent(this, DiscountActivity.class);
        rechner.setContent(discountIntent);
        
        // Tab for Database
        TabSpec verlauf = tabHost.newTabSpec("Verlauf");        
        verlauf.setIndicator("Verlauf");
        
        final Intent verlaufIntent = new Intent(this, VerlaufActivity.class);
        verlauf.setContent(verlaufIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(rechner); // Adding rechner tab
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
