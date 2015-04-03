package com.example.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import org.json.JSONObject;

public class RegisterActivity extends ActionBarActivity {
	String firstlastname;
	String username;
	String plate1;
	String plate2;
	String phonenumber;
	String cctype;
	String ccnum;
	String ccexpdate;
	InputStream is=null;
	String result=null;
	String line=null;
	int code;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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

	/** Called when the user clicks the submitregistration button */
	public void clickedSubmitRegistration(View view) {
		EditText editText = (EditText) findViewById(R.id.firstlastname);
		firstlastname = editText.getText().toString();

		editText=(EditText)findViewById(R.id.username);
		username=editText.getText().toString();

		editText=(EditText)findViewById(R.id.plate1);
		plate1=editText.getText().toString();

		editText=(EditText)findViewById(R.id.plate2);
		plate2=editText.getText().toString();

		editText=(EditText)findViewById(R.id.phonenumber);
		phonenumber=editText.getText().toString();

		editText=(EditText)findViewById(R.id.cctype);
		cctype=editText.getText().toString();

		editText=(EditText)findViewById(R.id.ccnum);
		ccnum=editText.getText().toString();

		editText=(EditText)findViewById(R.id.ccexpdate);
		ccexpdate=editText.getText().toString();

		insert();
	}

	
	private void insert() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		nameValuePairs.add(new BasicNameValuePair("name",firstlastname));
		nameValuePairs.add(new BasicNameValuePair("username",username));
		nameValuePairs.add(new BasicNameValuePair("plate1",plate1));
		nameValuePairs.add(new BasicNameValuePair("plate2",plate2));
		nameValuePairs.add(new BasicNameValuePair("phonenumber",phonenumber));
		nameValuePairs.add(new BasicNameValuePair("cctype",cctype));
		nameValuePairs.add(new BasicNameValuePair("ccnum",ccnum));
		nameValuePairs.add(new BasicNameValuePair("ccexpdate",ccexpdate));

		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://127.0.0.1/insert.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost); 
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e("pass 1", "connection success ");
		}
		catch(Exception e)
		{
			Log.e("Fail 1", e.toString());
			Toast.makeText(getApplicationContext(), "Invalid IP Address",
					Toast.LENGTH_LONG).show();
		}     

		try
		{
			BufferedReader reader = new BufferedReader
					(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
			Log.e("pass 2", "connection success ");
		}
		catch(Exception e)
		{
			Log.e("Fail 2", e.toString());
		}     

		try
		{
			JSONObject json_data = new JSONObject(result);
			code=(json_data.getInt("code"));

			if(code==1)
			{
				Toast.makeText(getBaseContext(), "Inserted Successfully",
						Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(getBaseContext(), "Sorry, Try Again",
						Toast.LENGTH_LONG).show();
			}
		}
		catch(Exception e)
		{
			Log.e("Fail 3", e.toString());
		}
	}
}

