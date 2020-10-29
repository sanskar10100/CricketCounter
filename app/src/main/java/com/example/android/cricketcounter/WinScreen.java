package com.example.android.cricketcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WinScreen extends AppCompatActivity {

	@SuppressLint("SetTextI18n")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_win_screen);

		Intent intent = getIntent();
		String winner = intent.getStringExtra("WINNER_NAME");
		((TextView) findViewById(R.id.textView_winningTeam)).setText(winner + " won the game!");
	}

	/**
	 * Pretty obvious, this one. Starts a new game by taking the user back to the main screen
	 *
	 * @param view the clicked button to start the new game
	 */
	public void startNewGame(View view) {
		startActivity(new Intent(this, MainActivity.class));
	}
}