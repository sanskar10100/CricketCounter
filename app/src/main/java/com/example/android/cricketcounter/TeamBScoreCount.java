package com.example.android.cricketcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TeamBScoreCount extends AppCompatActivity {

	private TextView scoreTextView;
	private TextView oversTextView;
	private double overs = 0.0;
	private int score = 0, wickets = 0;

	@SuppressLint("SetTextI18n")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_b_score_count);

		// Set Team B name and the rest of the defaults like initial score and over count
		((TextView) findViewById(R.id.textView_teamBName)).setText(DataHelper.teamBName);
		scoreTextView = findViewById(R.id.textView_teamBScore);
		oversTextView = findViewById(R.id.textView_teamBOvers);
		scoreTextView.setText("0/0");
		oversTextView.setText("0.0 overs");

		if (DataHelper.teamABattingFirst) {
			TextView targetScore = findViewById(R.id.textView_targetTeamB);
			targetScore.setText("Target: " + DataHelper.targetScore);
			targetScore.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * Handles a button clicked in Team B's scoring screen.
	 * NOTE: I'm aware that this function violates the DRY principle, but I couldn't find a better
	 * option without sacrificing code readability and comprehension, and both of them are
	 * more essential to me.
	 * 
	 * @param view the clicked button
	 */
	@SuppressLint({"SetTextI18n", "DefaultLocale", "NonConstantResourceId"})
	public void buttonClicked(View view) {
		if (DataHelper.teamBPlaying) {
			switch (view.getId()) {
				case R.id.button_teamB0Run:
					score += 0;
					break;
				case R.id.button_teamB1Run:
					score += 1;
					break;
				case R.id.button_teamB2Runs:
					score += 2;
					break;
				case R.id.button_teamB3Runs:
					score += 3;
					break;
				case R.id.button_teamB4Runs:
					score += 4;
					break;
				case R.id.button_teamB6Runs:
					score += 6;
					break;
				case R.id.button_teamBWicket:
					// Prevents split second clicks
					wickets += (wickets == DataHelper.MAX_WICKETS) ? 0 : 1;
					break;
			}


			// Refer class A comments
			if (view.getId() == R.id.button_teamBNoBall || view.getId() == R.id.button_teamBWideBall) {
				score += 1;
			} else {
				overs = DataHelper.incrementOver(overs, oversTextView);
			}
		}

		scoreTextView.setText(score + "/" + wickets);

		// If team A batted first, check if team B accumulated the target score. If yes, they win.
		// If all wickets fall or over count runs out, team B loses
		if (DataHelper.teamABattingFirst) {
			// Team A is either playing or played first
			if (score >= DataHelper.targetScore) {
				Intent intent = new Intent(this, WinScreen.class);
				intent.putExtra("WINNER_NAME", DataHelper.teamBName);
				startActivity(intent);
			} else if (wickets == DataHelper.MAX_WICKETS || Math.abs(DataHelper.maxNumOfOvers - overs) < DataHelper.EPSILON) {
				DataHelper.teamBPlaying = false;
				Intent intent = new Intent(this, WinScreen.class);
				intent.putExtra("WINNER_NAME", DataHelper.teamAName);
				startActivity(intent);
			}
		} else if (wickets == DataHelper.MAX_WICKETS || Math.abs(DataHelper.maxNumOfOvers - overs) < DataHelper.EPSILON) {
			// Team B is playing first. Set target when all wickets fall or over count runs out,
			// then passes control to transient inning details screen

			// The playing field is set accordingly to prevent teams from going one screen up
			// and changing the score
			DataHelper.teamBPlaying = false;
			DataHelper.teamAPlaying = true;
			DataHelper.targetScore = score + 1;
			Intent intent = new Intent(this, TargetScreen.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			String[] inningDetails = new String[4];
			inningDetails[0] = DataHelper.teamBName;
			inningDetails[1] = String.valueOf(score);
			inningDetails[2] = String.valueOf(wickets);
			inningDetails[3] = String.format("%.1f", overs);
			intent.putExtra("INNING_DETAILS", inningDetails);
			startActivity(intent);
		}
	}
}