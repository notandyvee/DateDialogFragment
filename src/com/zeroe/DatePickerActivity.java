package com.zeroe;

import java.util.Calendar;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DatePickerActivity extends Activity {
	DateDialogFragment frag;
	Button button;
    Calendar now;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        now = Calendar.getInstance();
        button = (Button)findViewById(R.id.date_button);
        button.setText(String.valueOf(now.get(Calendar.MONTH)+1)+"-"+String.valueOf(now.get(Calendar.DAY_OF_MONTH))+"-"+String.valueOf(now.get(Calendar.YEAR)));
        button.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		showDialog();	
        	}
        });
        
    }
    
    public void showDialog() {
    	FragmentTransaction ft = getFragmentManager().beginTransaction(); //get the fragment
    	frag = DateDialogFragment.newInstance(this, new DateDialogFragmentListener(){
    		public void updateChangedDate(int year, int month, int day){
    			button.setText(String.valueOf(month+1)+"-"+String.valueOf(day)+"-"+String.valueOf(year));
    			now.set(year, month, day);
    		}
    	}, now);
    	
    	frag.show(ft, "DateDialogFragment");
    	
    }
    
    public interface DateDialogFragmentListener{
    	//this interface is a listener between the Date Dialog fragment and the activity to update the buttons date
    	public void updateChangedDate(int year, int month, int day);
    }
    
    
}