package com.example.android.cricketcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

public class TargetScreen extends AppCompatActivity {

	String[] inningDetails;

	@SuppressLint({"SetTextI18n", "DefaultLocale"})
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_target_screen);

		Intent intent = getIntent();
		inningDetails = intent.getStringArrayExtra("INNING_DETAILS");
		TextView textView = findViewById(R.id.textView_battingTeamDescription);
		// Couldn't find a better way to transfer all the data without using DataHelper too much
		textView.setText(inningDetails[0] + " made " + inningDetails[1] + " runs on loss of " +
				inningDetails[2] + " wickets in " + inningDetails[3] + " overs." +
				"\n\n" + "The target is: " + DataHelper.targetScore + " runs" +
				"\n" + "RRR: " + String.format("%.1f", (float) DataHelper.targetScore / DataHelper.maxNumOfOvers));
	}

	/**
	 * Launches the activity for team that is batting 2nd
	 *
	 * @param view the clicked button
	 */
	public void startSecondInning(View view) {
		Intent intent;
		if (inningDetails[0].equals(DataHelper.teamAName)) {
			intent = new Intent(this, TeamBScoreCount.class);
		} else {
			intent = new Intent(this, TeamAScoreCount.class);
		}
		startActivity(intent);
	}
}