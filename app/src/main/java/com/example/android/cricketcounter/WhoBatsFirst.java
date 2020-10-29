package com.example.android.cricketcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class WhoBatsFirst extends AppCompatActivity {

	private RadioButton teamA;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_who_bats_first);

		// Set team names for both radiobutton
		teamA = findViewById(R.id.radioButton_teamA);
		teamA.setText(DataHelper.teamAName);
		RadioButton teamB = findViewById(R.id.radioButton_teamB);
		teamB.setText(DataHelper.teamBName);
	}

	/**
	 * Checks which team has been selected on the radioButton, and then sets the same in
	 * DataHelper. Also calls the corresponding score counting activity.
	 *
	 * @param view the clicked button
	 */
	public void setFirstBattingTeam(View view) {
		DataHelper.teamABattingFirst = teamA.isChecked();
		DataHelper.teamAPlaying = teamA.isChecked();
		DataHelper.teamBPlaying = !teamA.isChecked();

		// Launch score counting activity depending on the team
		if (DataHelper.teamABattingFirst) {
			startActivity(new Intent(this, TeamAScoreCount.class));
		} else {
			startActivity(new Intent(this, TeamBScoreCount.class));
		}
	}
}