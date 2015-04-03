package com.example.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONObject;
import org.json.simple.JSONObject;



public class MainActivity extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	String username;	//was name
	String password;	//was id
	InputStream is=null;
	String result=null;
	String line=null;
	int code;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    
    /** Called when the user clicks the Send button */
    public void clickedLogin(View view) {
        // Do something in response to button
    	boolean namepasswdgood=false;
    	Intent intent = new Intent(this, UserActivity.class);
    	
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	username = editText.getText().toString();
    	if(stringCheck(username)==false){
    		namepasswdgood=false;
    		Context context = getApplicationContext(); 
	        Toast.makeText(context, "invalid username. please re-enter", Toast.LENGTH_LONG).show();
	        //sendLogin(this.findViewById(R.id.home));
	        setContentView(R.layout.activity_main);
    	}else{
    		namepasswdgood=true;
    	}
    	
    	editText= (EditText) findViewById(R.id.edit_message2);
    	password = editText.getText().toString();  	
    	if(stringCheck(password)==false){
    		namepasswdgood=false;
    		Context context = getApplicationContext(); 
	        Toast.makeText(context, "invalid password. please re-enter", Toast.LENGTH_LONG).show();
	        //sendLogin(this.findViewById(R.id.home));
	        setContentView(R.layout.activity_main);
    	}else{
    		namepasswdgood=true;
    	}
    	
    	String namepasswd=username.concat(password);
    	intent.putExtra(EXTRA_MESSAGE, namepasswd);
    	
    	if(namepasswdgood==true){
    		startActivity(intent);
    	}
    }

    /*Checks formatting. allows only letter,digit,space*/
	private boolean stringCheck(String username) {
		boolean correctornot=false;
		for(int i=0;i<username.length();i++){
    		if(Character.isSpaceChar(username.charAt(i)) || 
    				Character.isLetter(username.charAt(i)) ||
    				Character.isDigit(username.charAt(i))){
    			correctornot=true;
    			continue;
    		}else{
    			correctornot=false;
    			break;
    		}
    	}
		return correctornot;
	}
    
	
	 /** Called when the user clicks the register button */
    public void clickedRegister(View view) {
    	Intent intent = new Intent(this, RegisterActivity.class);
    	startActivity(intent);
    }
}
