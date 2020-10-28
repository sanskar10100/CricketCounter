package com.example.android.cricketcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class WhoBatsFirst extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_who_bats_first);

		// TODO set names for RadioButtons

		((RadioButton) findViewById(R.id.radioButton_teamA)).setText(DataHelper.teamAName);
		((RadioButton) findViewById(R.id.radioButton_teamB)).setText(DataHelper.teamBName);
	}

	public void setFirstBattingTeam(View view) {
		Intent intent = new Intent(this, TeamAScoreCount.class);
		startActivity(intent);
	}
}