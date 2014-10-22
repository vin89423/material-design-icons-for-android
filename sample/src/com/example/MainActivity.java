package com.example;

import com.vinexs.mdicon.MaterialIcon;

public class MainActivity extends FragmentActivity {

	public MaterialIcon materialIcon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		/*
			...
		*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
    	materialIcon = new MaterialIcon(this);

    	MenuItem btnDemo1 = menu.add("Account Circle").setIcon( materialIcon.draw("ic_account_circle") );
    	btnDemo1.setShowAsAction( MenuItem.SHOW_AS_ACTION_ALWAYS );
    	
    	MenuItem btnDemo2 = menu.add("Account Box").setIcon( materialIcon.draw("ic_account_box") );
    	btnDemo2.setShowAsAction( MenuItem.SHOW_AS_ACTION_ALWAYS );
    	
        return true;
    }
}
