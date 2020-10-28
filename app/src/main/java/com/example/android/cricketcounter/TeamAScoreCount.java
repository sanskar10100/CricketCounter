package com.example.android.cricketcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TeamAScoreCount extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_a_score_count);

		// Set Team A Name
		((TextView) findViewById(R.id.textView_teamAName)).setText(DataHelper.teamAName);
	}
}