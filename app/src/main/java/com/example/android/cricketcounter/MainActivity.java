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

	/**
	 * Sets team name static fields in DataHelper along with maxOvers. ALso handles default values.
	 *
	 * @param view clicked button
	 */
	public void setTeamNames(View view) {
		String teamAName = ((EditText) findViewById(R.id.editText_teamA)).getText().toString();
		String teamBName = ((EditText) findViewById(R.id.editText_teamB)).getText().toString();
		int numOfOvers = Integer.parseInt(((EditText) findViewById(R.id.editText_maxOvers)).getText().toString());

		// Set default values
		if (teamAName.isEmpty()) {
			teamAName = getString(R.string.team_b_name);
		}

		if (teamBName.isEmpty()) {
			teamBName = getString(R.string.team_a_name);
		}

		if (numOfOvers == 0) {
			numOfOvers = 20;
		}

		// Set static names
		DataHelper.teamAName = teamAName;
		DataHelper.teamBName = teamBName;
		DataHelper.maxNumOfOvers = numOfOvers;

		// Move to Who Will Bat First chooser
		Intent intent = new Intent(this, WhoBatsFirst.class);
		startActivity(intent);
	}
}