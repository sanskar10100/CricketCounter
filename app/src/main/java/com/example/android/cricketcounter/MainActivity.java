package com.example.android.cricketcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void setTeamNames(View view) {
		String teamAName = ((EditText) findViewById(R.id.editText_teamA)).getText().toString();
		String teamBName = ((EditText) findViewById(R.id.editText_teamB)).getText().toString();
		int numOfOvers = Integer.parseInt(((EditText) findViewById(R.id.editText_maxOvers)).getText().toString());

		DataHelper.teamAName = teamAName;
		DataHelper.teamBName = teamBName;
		DataHelper.maxNumOfOvers = numOfOvers;

		Intent intent = new Intent(this, WhoBatsFirst.class);
		startActivity(intent);
	}
}