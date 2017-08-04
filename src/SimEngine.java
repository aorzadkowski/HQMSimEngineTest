
public class SimEngine {
	public static double maxOffense = 59.2;
	public static double maxDefense = 16.8;
	public static double maxBoth = 4.0;
	
	public static void getBoxScore(Team team1, Team team2) {
		/*
		System.out.print("Box scores:\t\t\tP1\tP2\tP3\tF\nTeam1: \t\t\t");
		*/
		int[][] boxScore = new int[3][2];
		
		boxScore[0] = getNetScoreInPeriod(team1, team2);
		boxScore[1] = getNetScoreInPeriod(team1, team2);
		boxScore[2] = getNetScoreInPeriod(team1, team2);
		/*
		System.out.println(period1[0] + "\t" + period2[0] + "\t" + period3[0] + "\t" + (period1[0] + period2[0] + period3[0]));
		System.out.println("Team2: \t\t\t" + period1[1] + "\t" + period2[1] + "\t" + period3[1]+ "\t" + (period1[1] + period2[1] + period3[1]));
		*/
		
		int team1Score = (boxScore[0][0] + boxScore[1][0] + boxScore[2][0]);
		int team2Score = (boxScore[0][1] + boxScore[1][1] + boxScore[2][1]);
		
		
		if (team1Score == team2Score) {
			int team1OTScore = 0;
			int team2OTScore = 0;
			int otCount = 0;
			
			while (team1OTScore == team2OTScore) {
				int[] otPeriod = getNetScoreInPeriod(team1, team2);
			
				team1OTScore = otPeriod[0];
				team2OTScore = otPeriod[1];
				
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
				otCount += 1;
			}
			
			int[][] newBoxScore = new int[3 + otCount][2];
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 2; j++) {
					newBoxScore[i][j] = boxScore[i][j];
				}
			}
			
			for (int i = 3; i < 3 + otCount; i++) {
				for (int j = 0; j < 2; j++) {
					newBoxScore[i][j] = 0;
				}
			}
			
			newBoxScore[2+otCount][0] = team1OTScore;
			newBoxScore[2+otCount][1] = team2OTScore;
			
			boxScore = newBoxScore;
		}
		int team1FinalScore = 0;
		int team2FinalScore = 0;
		
		String team1OutputLine = team1.teamName + "\t";
		String team2OutputLine = team2.teamName + "\t";

		for (int i = 0; i < boxScore.length; i++) {
			team1OutputLine += boxScore[i][0] + "\t";
			team2OutputLine += boxScore[i][1] + "\t";
			
			team1FinalScore += boxScore[i][0];
			team2FinalScore += boxScore[i][1];
		}
		
		/////////////
		team1OutputLine += team1FinalScore;
		team2OutputLine += team2FinalScore;
		
		int headerLength = boxScore.length;
		String headerLine = "Team";
		
		for (int i = 0; i < headerLength; i++) {
			headerLine += "\t" + (i <= 2 ? "P" + (i + 1) : "OT" + (i -2));
		}
		System.out.println(headerLine + "\tF");
		System.out.println(team1OutputLine);
		System.out.println(team2OutputLine);
		//////////////
		
		//System.out.println(team1FinalScore + "\t" + team2FinalScore + "\t" + (team1FinalScore > team2FinalScore ? "1" : "0"));
	}
	
	private static int[] getNetScoreInPeriod(Team team1, Team team2) {
		int team1Goals = getScoreInPeriod(team1);
		int team1Blocks = getBlocksInPeriod(team1);
		
		int team2Goals = getScoreInPeriod(team2);
		int team2Blocks = getBlocksInPeriod(team2);
		
		int team1Points = team1Goals + team2Blocks;
		if (team1Points < 0) team1Points = 0;
		
		int team2Points = team2Goals + team1Blocks;
		if (team2Points < 0) team2Points = 0;
		
		return new int[] {team1Points, team2Points};
	}
	
	private static int getScoreInPeriod(Team team) {
		double randomNumb = Math.random();
		int currentScore = 0;
		if (randomNumb <= team.oRatio) {
			currentScore += 1;
			currentScore += getScoreInPeriod(team, 1);
		}
		return currentScore;
	}
	
	private static int getScoreInPeriod(Team team, int score) {
		//double probability = team.oRatio - ((0.01 + team.inverseBRatio) * team.oRatio * (score * score));
		double probability = team.oRatio - ((0.01 + team.inverseBRatio) * team.oRatio * (score));
		if (Math.random() <= probability) {
			return 1 + getScoreInPeriod(team, score + 1);
		}
		return 0;
	}
	
	private static int getBlocksInPeriod(Team team) {
		double randomNumb = Math.random();
		int currentDefCont = 0;
		if (randomNumb <= team.dRatio) {
			currentDefCont -= 1;
			currentDefCont += getBlocksInPeriod(team, 1);
		}
		return currentDefCont;
	}
	
	private static int getBlocksInPeriod(Team team, int score) {
		//double probability = team.dRatio - ((0.01 + team.inverseBRatio) * team.dRatio * (score * score));
		double probability = team.dRatio - ((0.01 + team.inverseBRatio) * team.dRatio * (score));
		if (Math.random() <= probability) {
			return (-1) - getBlocksInPeriod(team, score + 1);
		}
		return 0;
	}
	
}
