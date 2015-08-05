package com.jeffgan.example.bingogenerator;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;


public class MainActivity extends Activity {

	private Button generateButton;
	private TextView bListTV, iListTV, nListTV, gListTV, oListTV;
	private TextSwitcher currentNumberTV;
	private ArrayList<Integer> currentListNumbers;
	String tag="BINGO";
	private final int MAX_NUMBER_OF_BINGO = 75;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		generateButton = (Button)findViewById(R.id.generateButton);
		currentNumberTV = (TextSwitcher)findViewById(R.id.currentNumber);
		bListTV = (TextView)findViewById(R.id.b_numbers);
		iListTV = (TextView)findViewById(R.id.i_numbers);
		nListTV = (TextView)findViewById(R.id.n_numbers);
		gListTV = (TextView)findViewById(R.id.g_numbers);
		oListTV = (TextView)findViewById(R.id.o_numbers);

		//nice!
		currentListNumbers = new ArrayList<Integer>();
		generateButton.setOnClickListener(generateAction);
		
		currentNumberTV.setFactory(new ViewFactory() {
             
             public View makeView() {
                 // TODO Auto-generated method stub
                 TextView myText = new TextView(MainActivity.this);
//                 myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                 myText.setTextSize(100);
                 myText.setTextColor(Color.BLUE);
                 return myText;
             }
         });
		
		 // Declare the in and out animations and initialize them  
        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
        
        // set the animation type of textSwitcher
        currentNumberTV.setInAnimation(in);
        currentNumberTV.setOutAnimation(out);
    
	}

	private Button.OnClickListener generateAction = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

        	Log.v(tag, "Current size:"+currentListNumbers.size());
        	if(currentListNumbers.size() < MAX_NUMBER_OF_BINGO){
            	int number = generateNumber();
            	while(number < 1 || currentListNumbers.contains(number)){
            		number = generateNumber();
            	}
            	if(number < 10){
            		currentNumberTV.setText("0"+number);	
            	}else{
            		currentNumberTV.setText(""+number);	
            	}
            	
            	updateScreen(number);
        	}else{
        		generateButton.setText(getString(R.string.bingo));
        		return;
        	}

        	
        }
	};
	
	private int generateNumber(){
    	Random randomNumber = new Random();
    	int realNumber = randomNumber.nextInt(MAX_NUMBER_OF_BINGO+1);
		Log.v(tag, "Generating "+realNumber);
    	return realNumber;
	}
	
	private void updateScreen(int newNumber){
		currentListNumbers.add(newNumber);
		//starting to generate
		if(newNumber < 16){
			if(bListTV.getText().equals("")){
				bListTV.setText(""+newNumber);
			}else{
				bListTV.setText(bListTV.getText() + ", " + newNumber);
			}
		}else if(newNumber < 31){
			if(iListTV.getText().equals("")){
				iListTV.setText(""+newNumber);
			}else{
				iListTV.setText(iListTV.getText() + ", " + newNumber);
			}
		}else if(newNumber < 46){
			if(nListTV.getText().equals("")){
				nListTV.setText(""+newNumber);
			}else{
				nListTV.setText(nListTV.getText() + ", " + newNumber);
			}
		}else if(newNumber < 61){
			if(gListTV.getText().equals("")){
				gListTV.setText(""+newNumber);
			}else{
				gListTV.setText(gListTV.getText() + ", " + newNumber);
			}
		}else if(newNumber < 76){
			if(oListTV.getText().equals("")){
				oListTV.setText(""+newNumber);
			}else{
				oListTV.setText(oListTV.getText() + ", " + newNumber);
			}
		}

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
