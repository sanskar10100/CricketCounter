package com.example.android.cricketcounter;

import android.annotation.SuppressLint;
import android.widget.TextView;

/**
 * Store mutually accessible data for both Team A and Team B
 * Tried to make it somewhat equal to a Kotlin data class (next project in Kotlin for sure)
 */
public class DataHelper {
	// Always remember epsilon. Floating point comparisons may yield incorrect result due to a difference
	// in insignificant trailing decimal values. EPSILON values ensure that the error in difference is neglected
	// and doesn't ruin the flow of the program.
	static double EPSILON = 0.01f;
	static String teamAName;
	static String teamBName;
	static int maxNumOfOvers;
	static boolean teamABattingFirst;
	static boolean teamAPlaying;
	static boolean teamBPlaying;
	static int MAX_WICKETS = 10;
	static int targetScore;
	static final String LOG_TAG = "RESULT";

	/**
	 * Increments overs, if 5 balls have been delivered in the current over,
	 * change to the next over
	 *
	 * @param currentOvers the current over count
	 * @return updatedOvers
	 */
	@SuppressLint({"DefaultLocale", "SetTextI18n"})
	static double incrementOver(double currentOvers, TextView textView) {
		if (Math.abs(currentOvers - (int) currentOvers - 0.5f) < EPSILON) {
			currentOvers -= 0.5f;
			currentOvers += 1f;
		} else {
			currentOvers += 0.1f;
		}

		textView.setText(String.format("%.1f", currentOvers) + " overs");
		return currentOvers;
	}
}
