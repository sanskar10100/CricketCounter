package com.example.android.cricketcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TeamAScoreCount extends AppCompatActivity {

	private TextView scoreTextView;
	private TextView oversTextView;
	private double overs = 0.0;
	private int score = 0, wickets = 0;

	@SuppressLint("SetTextI18n")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_a_score_count);

		// Set Team A Name and rest of the defaults like score and overs
		((TextView) findViewById(R.id.textView_teamAName)).setText(DataHelper.teamAName);
		scoreTextView = findViewById(R.id.textView_teamAScore);
		oversTextView = findViewById(R.id.textView_teamAOvers);
		scoreTextView.setText("0/0");
		oversTextView.setText("0.0 overs");

		if (!DataHelper.teamABattingFirst) {
			TextView targetScore = findViewById(R.id.textView_targetTeamA);
			targetScore.setText("Target: " + DataHelper.targetScore);
			targetScore.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * When any of the button in Team A's score count screen is clicked, this handler increments
	 * the score and the over count, unless the clicked button is a 'Wide' or 'No Ball'.
	 * 
	 * @param view the clicked button
	 */
	@SuppressLint({"NonConstantResourceId", "DefaultLocale", "SetTextI18n"})
	public void buttonClicked(View view) {
		if (DataHelper.teamAPlaying) {
			switch (view.getId()) {
				case R.id.button_teamA0Run:
					score += 0;
					break;
				case R.id.button_teamA1Run:
					score += 1;
					break;
				case R.id.button_teamA2Runs:
					score += 2;
					break;
				case R.id.button_teamA3Runs:
					score += 3;
					break;
				case R.id.button_teamA4Runs:
					score += 4;
					break;
				case R.id.button_teamA6Runs:
					score += 6;
					break;
				case R.id.button_teamAWicket:
					// If the wickets are already at the maximum, restrict increment for split second
					// activity refresh
					wickets += (wickets == DataHelper.MAX_WICKETS) ? 0 : 1;
					break;
			}

			// Check if the ball is either a 'No ball' or 'wide'. If it is, only increment score,
			// not the over count.
			if (view.getId() == R.id.button_teamANoBall || view.getId() == R.id.button_teamAWideBall) {
				score += 1;
			} else {
				overs = DataHelper.incrementOver(overs, oversTextView);
			}
		}

		scoreTextView.setText(score + "/" + wickets);

		// If team A bats first, let them score, until all wickets fall or over count runs out.
		// After that, pass the control to the transient screen that shows target and other details.
		// Transient passes control to Team B screen
		if (DataHelper.teamABattingFirst) {
			if (wickets == DataHelper.MAX_WICKETS || Math.abs(DataHelper.maxNumOfOvers - overs) < DataHelper.EPSILON) {

				// The playing field is set accordingly to prevent teams from going one screen up
				// and changing the score
				DataHelper.teamAPlaying = false;
				DataHelper.teamBPlaying = true;
				DataHelper.targetScore = score + 1;
				Intent intent = new Intent(this, TargetScreen.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				String[] inningDetails = new String[4];
				inningDetails[0] = DataHelper.teamAName;
				inningDetails[1] = String.valueOf(score);
				inningDetails[2] = String.valueOf(wickets);
				inningDetails[3] = String.format("%.1f", overs);
				intent.putExtra("INNING_DETAILS", inningDetails);
				startActivity(intent);
			}
		} else {
			if (score >= DataHelper.targetScore) {
				// Win case scenario, Team A doesn't bat first, but accumulated the target
				// Both cases pass control to winner screen
				Intent intent = new Intent(this, WinScreen.class);
				intent.putExtra("WINNER_NAME", DataHelper.teamAName);
				startActivity(intent);
			} else if (wickets == DataHelper.MAX_WICKETS || Math.abs(DataHelper.maxNumOfOvers - overs) < DataHelper.EPSILON) {
				// Lose case scenario, Team A doesn't bat first, and either over count runs out
				// or all the wickets fall
				DataHelper.teamAPlaying = false;
				Intent intent = new Intent(this, WinScreen.class);
				intent.putExtra("WINNER_NAME", DataHelper.teamBName);
				startActivity(intent);
			}
		}
	}
}