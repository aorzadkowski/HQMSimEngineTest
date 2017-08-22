package sim;

import hqmdatabase.Player;
import main.Options;

public class SimEngine {
	
	private static int[] team1Goals = new int[4];
	private static int[] team2Goals = new int[4];
	private static int[] team1Blocks = new int[4];
	private static int[] team2Blocks = new int[4];
	private static int[] team1Points = new int[4];
	private static int[] team2Points = new int[4];
	public static Stats[] team1PlayerStats;
	public static Stats[] team2PlayerStats;
	
	public static void simulateGame(Game game) {
		Team team1 = game.getTeam1();	// Stores Team 1 locally
		Team team2 = game.getTeam2();	// Stores Team 2 locally
		
		team1PlayerStats = new Stats[5];	// Resets player stats for Team 1
		team2PlayerStats = new Stats[5];	// Resets player stats for Team 2
		
		for (int i = 0; i < team1.teamPlayers.length; i++) {	// Cycles through all players on a team (5)
			team1PlayerStats[i] = new Stats(team1.teamPlayers[i]);	// Creates player map for Team 1
			team2PlayerStats[i] = new Stats(team2.teamPlayers[i]);	// Creates player map for Team 2
			
			if (i < 4) {
				team1PlayerStats[i].addGamesPlayed();	// Adds game played for non-goalies
				team2PlayerStats[i].addGamesPlayed();	// Adds game played for non-goalies
			} else {
				team1PlayerStats[i].addGamesPlayedAtG();	// Adds game played for goalie
				team2PlayerStats[i].addGamesPlayedAtG();	// Adds game played for goalie
			}
		}
		
		int[][] boxScore = new int[3][2];	// Creates boxscore array
		
		boxScore[0] = getNetScoreInPeriod(team1, team2);	// Simulates the first period
		boxScore[1] = getNetScoreInPeriod(team1, team2);	// Simulates the second period
		boxScore[2] = getNetScoreInPeriod(team1, team2);	// Simulates the third period
		
		int team1Score = (boxScore[0][0] + boxScore[1][0] + boxScore[2][0]);	// Calculates Team 1 total score
		int team2Score = (boxScore[0][1] + boxScore[1][1] + boxScore[2][1]);	// Calculates Team 2 total score
		
		if (team1Score == team2Score) {		// Checks if the game is still tied after regulation
			int team1OTScore = 0;			// Resets Team 1 OT score
			int team2OTScore = 0;			// Resets Team 2 OT score
			int otCount = 0;				// Resets OT Period
			
			while (team1OTScore == team2OTScore) {	// If the score is still tied after OT periods (or regulation for first pass)
				int[] otPeriod = getNetScoreInPeriod(team1, team2);	// Simulates one OT period
			
				team1OTScore = otPeriod[0];	// Retrieves Team 1 score in OT
				team2OTScore = otPeriod[1];	// Retrieves Team 2 score in OT
		
				/*
				8.8.17 Removing code below (sudden death) until we figure out what to do with stats during the OT period
				
				if (team1OTScore > team2OTScore) {
					team1OTScore = 1;
					team2OTScore = 0;
				}
				else if (team2OTScore > team1OTScore) {
					team2OTScore = 1;
					team1OTScore = 0;
				} 
				else if (team1OTScore == team2OTScore && team1OTScore != 0) {
					team1OTScore = 0;
					team2OTScore = 0;
				}
				*/
							
				otCount += 1;				// Increments the OT period tracker
			}
			
			int[][] newBoxScore = new int[3 + otCount][2];	// Creates a new boxscore to accomodate the OT periods
			
			for (int i = 0; i < 3; i++) {	// Cycles through the 3 regulation periods
				for (int j = 0; j < 2; j++) {
					newBoxScore[i][j] = boxScore[i][j];	// Creates the final boxscore
				}
			}
			
			for (int i = 3; i < 3 + otCount; i++) {	// Cycles through OT periods
				for (int j = 0; j < 2; j++) {
					newBoxScore[i][j] = 0;	// Adds any OT periods to final boxscore
				}
			}
			
			newBoxScore[2+otCount][0] = team1OTScore;	// Adds Team 1 final score to end of boxscore
			newBoxScore[2+otCount][1] = team2OTScore;	// Adds Team 2 final score to end of boxscore
			
			boxScore = newBoxScore;			// Assigns the new boxscore to the orignal boxscore (for other methods)
		}
		
		game.setScore(boxScore);			// Sets the final score of the game

		game.team1PlayerStats = team1PlayerStats;	// Maps the player stats for Team 1
		game.team2PlayerStats = team2PlayerStats;	// Maps the player stats for Team 2

		/*
		System.out.println("Here are the stats for each player: \n\tName\tSeason\tPos\tPts\tG\tA\tP/G\t+/-\tShots\tShots Faced\tSvs\tSv%\tSv/G\tGA\tGAA\tGP\tGP@G");
		
		for (int i = 0; i < team1PlayerStats.length; i++) {
			
			String[] stats = team1PlayerStats[i].toCSV().split(",");
			
			for (int j = 0; j < stats.length; j++) {
				System.out.print("\t" + stats[j]);
			}
			System.out.println();
		}
		
		System.out.println();
		
		for (int i = 0; i < team2PlayerStats.length; i++) {
			
			String[] stats = team2PlayerStats[i].toCSV().split(",");
			
			for (int j = 0; j < stats.length; j++) {
				System.out.print("\t" + stats[j]);
			}
			System.out.println();
		}
		*/
	}
	
	public static int[] getNetScoreInPeriod(Team team1, Team team2) {
		int team1Total = 0;						// Resets Team 1 goals
		int team2Total = 0;						// Resets Team 2 goals
		
		for (int i = 0; i < 4; i++) {		// Cycles through the 4 skaters for the teams
			team1Goals[i] = getScoreInPeriod(team1, i);	// Calculates Team 1 shots on net for each player
			team1Blocks[i] = getBlocksInPeriod(team1);	// Calculates Team 1 blocks against each opposing player
			team2Goals[i] = getScoreInPeriod(team2, i);	// Calculates Team 2 shots on net for each player
			team2Blocks[i] = getBlocksInPeriod(team2);	// Calculates Team 2 blocks against each opposing player
		}
		
		for ( int i = 0; i < 4; i++ ) {		// Cycles through the 4 skaters for the teams
			team1Points[i] = team1Goals[i] + team2Blocks[i];	// Calculates potential points for the player (shots on net - blocks)
		    if (team1Points[i] > 0) {		// Checks to see if any shots went unblocked
				for (int k = 0; k < team1Points[i]; k++) {	// Cycles through the amount of shots on net that were not blocked
					addShotOnGoal(getNameFromIndex(4, team2));	// Adds a shot on net for the opposing goalie
					addShot(getNameFromIndex(i, team1));	// Adds a shot for the shooter
				}
				team1Points[i] = calcSave(team2, team1.teamPlayers[i], team2.teamPlayers[4], team1Points[i], 2);	// Calculates how many shots are saved by the opposing goalie, returns the amount of goals scored
			}
		    
			if (team1Points[i] < 0) team1Points[i] = 0;	// Sets points to 0 in case blocks outnumber shots
			team1Total += team1Points[i];	// Adds up the goals for each skater
			
			for (int k = 0; k < team1Points[i]; k++) {	// Cycles through the amount of goals scored
				addGoal(getNameFromIndex(i, team1));	// Adds a goal stat
				addPlus(true);				// Adds a plus stat for Team 1
				addMinus(false);			// Adds a minus stat for Team 2
			}
			if ( team1Points[i] > 0) calcApple(team1, team1Points[i], i, 1);	// Calculates any assists if goals were scored
		
			team2Points[i] = team2Goals[i] + team1Blocks[i];	// Calculates potential points for the player (shots on net - blocks)
			if (team2Points[i] > 0) {		// Checks to see if any shots went unblocked
				for (int k = 0; k < team2Points[i]; k++) {	// Cycles through the amount of shots on net that were not blocked
					addShotOnGoal(getNameFromIndex(4, team1));	// Adds a shot on net for the opposing goalie
					addShot(getNameFromIndex(i, team2));	// Adds a shot for the shooter
				}
				team2Points[i] = calcSave(team1, team2.teamPlayers[i], team1.teamPlayers[4], team2Points[i], 1);	// Calculates how many shots are saved by the opposing goalie, returns the amount of goals scored
			}
			
			if (team2Points[i] < 0) team2Points[i] = 0;	// Sets points to 0 in case blocks outnumber shots
			team2Total += team2Points[i];	// Adds up the goals for each skater
			
			for (int k = 0; k < team2Points[i]; k++) {	// Cycles through the amount of goals scored
				addGoal(getNameFromIndex(i, team2));	// Adds a goal stat
				addPlus(false);				// Adds a plus stat for Team 2
				addMinus(true);				// Adds a minus stat for Team 1
			}
			if ( team2Points[i] > 0) calcApple(team2, team2Points[i], i, 2);	// Calculates any assists if goals were scored
		}
		return new int[] {team1Total, team2Total};	// Returns the goals scored by each team
	}
	
	private static void addPlus(boolean team1) {
		if (team1) {
			for (int i = 0; i < team1PlayerStats.length - 1; i++) {
				team1PlayerStats[i].addPlusMinus();
			}
		} else {
			for (int i = 0; i < team2PlayerStats.length - 1; i++) {
				team2PlayerStats[i].addPlusMinus();
			}
		}
	}
	
	private static void addMinus(boolean team1) {
		if (team1) {
			for (int i = 0; i < team1PlayerStats.length - 1; i++) {
				team1PlayerStats[i].removePlusMinus();
			}
		} else {
			for (int i = 0; i < team2PlayerStats.length - 1; i++) {
				team2PlayerStats[i].removePlusMinus();
			}
		}
	}
	
	private static void addShot(String playerName) {
		for (int i = 0; i < team1PlayerStats.length; i++) {
			if (team1PlayerStats[i].getPlayerName().equals(playerName)) {
				team1PlayerStats[i].addShot();
				return;
			}
		}
		
		for (int i = 0; i < team2PlayerStats.length; i++) {
			if (team2PlayerStats[i].getPlayerName().equals(playerName)) {
				team2PlayerStats[i].addShot();
				return;
			}
		}
	}
	
	private static void addGoal(String playerName) {
		for (int i = 0; i < team1PlayerStats.length; i++) {
			if (team1PlayerStats[i].getPlayerName().equals(playerName)) {
				team1PlayerStats[i].addGoal();
			}
		}
		
		for (int i = 0; i < team2PlayerStats.length; i++) {
			if (team2PlayerStats[i].getPlayerName().equals(playerName)) {
				team2PlayerStats[i].addGoal();
				return;
			}
		}
	}
	
	private static void addShotOnGoal(String playerName) {
		for (int i = 0; i < team1PlayerStats.length; i++) {
			if (team1PlayerStats[i].getPlayerName().equals(playerName)) {
				team1PlayerStats[i].addShotOnNet();
			}
		}
		
		for (int i = 0; i < team2PlayerStats.length; i++) {
			if (team2PlayerStats[i].getPlayerName().equals(playerName)) {
				team2PlayerStats[i].addShotOnNet();
				return;
			}
		}
	}
	
	private static void addSave(String playerName) {
		for (int i = 0; i < team1PlayerStats.length; i++) {
			if (team1PlayerStats[i].getPlayerName().equals(playerName)) {
				team1PlayerStats[i].addSave();
			}
		}
		
		for (int i = 0; i < team2PlayerStats.length; i++) {
			if (team2PlayerStats[i].getPlayerName().equals(playerName)) {
				team2PlayerStats[i].addSave();
				return;
			}
		}
	}
	
	private static void addAssist(String playerName) {
		for (int i = 0; i < team1PlayerStats.length; i++) {
			if (team1PlayerStats[i].getPlayerName().equals(playerName)) {
				team1PlayerStats[i].addAssist();
			}
		}
		
		for (int i = 0; i < team2PlayerStats.length; i++) {
			if (team2PlayerStats[i].getPlayerName().equals(playerName)) {
				team2PlayerStats[i].addAssist();
				return;
			}
		}
	}
	
	private static String getNameFromIndex(int index, Team team) {
		return team.teamPlayers[index].getName();
	}
	
	private static int getScoreInPeriod(Team team, int player) {
		double randomNumb = Math.random();
		int currentScore = 0;
			if (randomNumb <= team.oRatio[player]) {
				currentScore += 1;
				currentScore += getScoreInPeriod(team, 1, player);
			}
		return currentScore;
	}
		
	private static int getScoreInPeriod(Team team, int score, int player) {
		double probability = team.oRatio[player] - ((0.01 + team.playerInvBRatio[player]) * team.oRatio[player] * (score));
		if (Math.random() <= probability) {
			return 1 + getScoreInPeriod(team, score + 1, player);
		}
		return 0;
	}
	
	private static int getBlocksInPeriod(Team team ) {
		double randomNumb = Math.random();
		int currentDefCont = 0;
		if (randomNumb <= team.teamDRatio) {
			currentDefCont -= 1;
			currentDefCont += getBlocksInPeriod(team, 1);
		}
		return currentDefCont;
	}
	
	private static int getBlocksInPeriod(Team team, int score ) {
		double probability = team.teamDRatio - ((0.01 + team.teamInvBRatio) * team.teamDRatio * (score));
		if (Math.random() <= probability) {
			return (-1) - getBlocksInPeriod(team, score + 1);
		}
		return 0;
	}
	
	public static int calcSave( Team goalieTeam, Player shooter, Player goalie, int goals, int goalieTeamNum ) {
		double[] weights;
		int shooterStatUsed;
		double goalieStatUsed;
		double difference;
		double newGoalieDRatio;
		double newGoalieDBRatio;
		int saves = 0;
		int newGoals = 0;


		for ( int i = 0; i < goals; i++ ) {
			if( shooter.position.toString() == "C" || shooter.position.toString() == "LW" ) weights = new double[] {0.05,0.25,1}; // 5% long shot 20% point shot 75% wrist shot
			else weights = new double[] {0.05,0.8,1}; // 5% long shot 75% point shot 20% wrist shot
			double prob = Math.random();
			if ( prob <= weights[0]) {
				shooterStatUsed = shooter.stats[1];
				goalieStatUsed = goalie.stats[0];
			}
			else if ( prob <= weights[1]) {
				shooterStatUsed = shooter.stats[2];
				goalieStatUsed = goalie.stats[1];
			}
			else {
				shooterStatUsed = shooter.stats[3];
				goalieStatUsed = (double)(goalie.stats[1] + goalie.stats[2])/2; // Average of wrist and breakaway
			}
			difference = goalieStatUsed - shooterStatUsed;
			newGoalieDRatio = goalieTeam.goalieDRatio + (0.05 * difference);
			if ( newGoalieDRatio > 0.90) newGoalieDRatio = 0.9;
			if ( newGoalieDRatio < 0.10) newGoalieDRatio = 0.1;
			if ( i == 0 ) {
				if (Options.debug) System.out.println( newGoalieDRatio + "\t" + i );
				if ( Math.random() < newGoalieDRatio ) saves++;
				else newGoals++;
			}
			else {
				newGoalieDBRatio = newGoalieDRatio - (( 0.01 + goalieTeam.invGoalieBRatio ) * newGoalieDRatio * i);
				if (Options.debug) System.out.println( newGoalieDBRatio + "\t" + i );
				if ( Math.random() < newGoalieDBRatio ) saves++;
				else newGoals++;
			}
		}

		if ( saves > 0 && goalieTeamNum == 1 ) {
			for (int i = 0; i < saves; i++) {
				addSave(goalie.getName());
			}
		}
		
		else if( saves > 0 && goalieTeamNum == 2 ) {
			for (int i = 0; i < saves; i++) {
				addSave(getNameFromIndex(4, goalieTeam));
			}
		}
	
		return newGoals;
	}
	
	public static void calcApple( Team team, int goals, int player, int teamNum ) {
		for ( int i = 0; i < goals; i++ ) {
			double[] playerPassing = {0,0,0,0,0};
			int passingTotal = 0;
			double[] probArray = new double[]{0.28,0.28,0.28,0.28,0.28,0.28};
			int appleFlag = 0;
			
			for ( int j = 0; j < 4; j++ ) {
				if( j != player ) playerPassing[j] = team.teamPlayers[j].stats[4] + team.teamPlayers[j].stats[5] + team.teamPlayers[j].stats[6];
				passingTotal += playerPassing[j];
			}
			
			playerPassing[4] = team.teamPlayers[4].stats[6];
			passingTotal += playerPassing[4];
			
			for ( int j = 0; j < 5; j++) {
				playerPassing[j] =  playerPassing[j] / passingTotal;
				if ( j == player ) probArray[j+1] = probArray[j];
				else	probArray[j+1] = probArray[j] + playerPassing[j] - 0.07;
			}
			
			double prob = Math.random();
			for ( int j = 0; j < 6; j++) {
				if( prob < probArray[j] && j == 0 && appleFlag == 0 ) {
					appleFlag = 1;
				}
				else if( j != (player + 1) ) {
					if( prob < probArray[j] && j != 0 && appleFlag == 0 ) {
						if ( teamNum == 1 && j != 5) {
							addAssist(getNameFromIndex(j-1, team));
							appleFlag = 1;
						}
						else if ( j == 5) {
							addAssist(getNameFromIndex(4, team));
							appleFlag = 1;
						}
						else {
							addAssist(getNameFromIndex(j-1, team));
							appleFlag = 1;
						}
					}
				}
			}
		}
	}
}
