package com.example.android.cricketcounter;

/**
 * Store mutually accessible data
 */
public class DataHelper {
	static double EPSILON = 0.01f;
	static String teamAName;
	static String teamBName;
	static int maxNumOfOvers;
	static boolean teamAPlaying;

	DataHelper(final String teamA, final String teamB, final int overs) {
		teamAName = teamA;
		teamBName = teamB;
		maxNumOfOvers = overs;
	}
}
