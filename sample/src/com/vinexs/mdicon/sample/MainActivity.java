package com.vinexs.mdicon.sample;

import java.util.Arrays;
import java.util.List;

import com.vinexs.mdicon.MaterialIcon;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	public Activity activity = null;
	public MaterialIcon md = null;
	public Menu menu = null;
	
	public EditText edtColor = null;
	public EditText edtAlpha = null;
	
	public ImageView imgView = null;
	public Spinner spinner = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
    	md = new MaterialIcon( this );
    	md.setTheme(MaterialIcon.THEME_FREE);
        setContentView(R.layout.activity_main);
        
        edtColor = (EditText) findViewById(R.id.edtColor);
        edtAlpha = (EditText) findViewById(R.id.edtAlpha);
        Button btnMenuChg = (Button) findViewById(R.id.btnMenuChange);        
        btnMenuChg.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				try{
					final String colorText = "#"+ edtColor.getText().toString();
					if (colorText.length() != 7) {
						throw new Exception("Color Hex exception.");
					}
					final int color = Color.parseColor(colorText);
					
					final double alphaPercentage = Double.parseDouble(edtAlpha.getText().toString());
					double point = alphaPercentage / 100;
					final int alpha = (int) ( 255 * point );
					if (alpha<0 || alpha>255) {
						throw new Exception("Alpha range exception.");
					}

					runOnUiThread(new Runnable(){
						@Override
						public void run() {
							Log.d( "Sample", "Request menu icon color c["+ colorText +"],a["+ ( alphaPercentage / 100 ) +"%]" );
							reloadIconColor( menu, color, alpha );							
						}
					});
				} catch (Exception e){
					Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT ).show();
					e.printStackTrace();
				}
			}
        });
        
        // =================================================

        imgView = (ImageView) findViewById(R.id.imgView);
        setImageDrawable(imgView, "ic_3d_rotation", Color.parseColor("#FFFFFF") );
        
        spinner = (Spinner) findViewById(R.id.spinnerIcon);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.icon_option, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final List<String> iconlist = Arrays.asList(getResources().getStringArray(R.array.icon_option));
        spinner.setOnItemSelectedListener(new OnItemSelectedListener (){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				
				final String colorText = "#"+ edtColor.getText().toString();
				int color = Color.parseColor("#FFFFFF");
				try{
					color = Color.parseColor(colorText);
				} catch( Exception e ){}
				setImageDrawable(imgView, iconlist.get(position), color );
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
        	
        });
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	this.menu = menu;
    	reloadIconColor(menu, Color.parseColor("#FFFFFF"), (int) (255 * 0.8));
        return true;
    }
    
    public void reloadIconColor( Menu menu, int color, int alpha )
    {
		Log.d( "Sample", "Change menu icon color c["+ color +"],a["+ alpha +"%]" );
    	menu.clear();
    	
    	md.setColor(color);
    	md.setAlpah(alpha);
    	
    	MenuItem test1 = menu.add("Material Icon").setIcon( md.getMenuDrawable("ic_account_circle") );
    	test1.setShowAsAction( MenuItem.SHOW_AS_ACTION_ALWAYS );
    	
    	MenuItem test2 = menu.add("Material Icon").setIcon( md.getMenuDrawable("ic_account_box") );
    	test2.setShowAsAction( MenuItem.SHOW_AS_ACTION_ALWAYS );
    	
    	MenuItem test3 = menu.add("Android Icon").setIcon( md.getMenuDrawable("ic_android") );
    	test3.setShowAsAction( MenuItem.SHOW_AS_ACTION_ALWAYS );    	
    }
    
    public void setImageDrawable( ImageView imgView, String iconName, int color ) {
    	imgView.setImageDrawable( MaterialIcon.getDrawable(activity, iconName, color) );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
