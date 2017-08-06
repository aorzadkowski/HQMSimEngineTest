package sim;
public class Game {
	private Team team1;
	private Team team2;
	
	//First index is period. Any value > 2 is OT. 3 = OT1, 4 = OT2, etc.
	//Second index is team. 0 = team1 and 1 = team2.
	private int[][] boxScore;
	
	private int team1Score;
	private int team2Score;
	
	private boolean scoreSet;
	
	public Game (Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
		
		team1Score = 0;
		team2Score = 0;
		
		scoreSet = false;
	}
	
	public void setScore(int[][] newBoxScore) {
		if (newBoxScore.length < 3) {
			System.err.println("Game.java: New Box Score Array doesn't have enough periods.");
			return;
		}
		
		if (newBoxScore[0].length != 2) {
			System.err.println("Game.java: New Box Score Array doesn't have exactly two teams.");
			return;
		}
		
		boxScore = newBoxScore;
		
		for (int i = 0; i < boxScore.length; i++) {
			team1Score += boxScore[i][0];
			team2Score += boxScore[i][1];
		}
		
		scoreSet = true;
	}
	
	public Team getTeam1() {
		return team1;
	}
	
	public Team getTeam2() {
		return team2;
	}
	
	public String toString() {
		if (!scoreSet) { System.err.println("Game.java: Score is not set!"); return null; }
		return team1.teamName + ": " + team1Score + ", " + team2.teamName + ": " + team2Score;
	}
	
	public String printableScore() {
		if (!scoreSet) { System.err.println("Game.java: Score is not set!"); return null; }
		return printableScore("\t");
	}
	
	public String printableScore(String delimiter) {
		if (!scoreSet) { System.err.println("Game.java: Score is not set!"); return null; }
		return team1Score + delimiter + team2Score;
	}
	
	public int[] getScores() {
		if (!scoreSet) { System.err.println("Game.java: Score is not set!"); return null; }
		return new int[] {team1Score, team2Score};
	}
	
	public boolean isGameOT() {
		if (!scoreSet) { System.err.println("Game.java: Score is not set!"); return false; }
		return boxScore.length > 3;
	}
	
	public int getOTCount() {
		return boxScore.length - 3;
	}
	
	public int marginOfVictory() {
		if (!scoreSet) { System.err.println("Game.java: Score is not set!"); return -1; }
		return (team1Score > team2Score ? team1Score - team2Score : team2Score - team1Score);
	}
	
	public int totalGoals() {
		if (!scoreSet) { System.err.println("Game.java: Score is not set!"); return -1; }
		return team1Score + team2Score;
	}
	
	public int winningTeamGoals() {
		if (!scoreSet) { System.err.println("Game.java: Score is not set!"); return -1; }
		return (team1Score > team2Score ? team1Score : team2Score);
	}
	
	public int losingTeamGoals() {
		if (!scoreSet) { System.err.println("Game.java: Score is not set!"); return -1; }
		return (team1Score < team2Score ? team1Score : team2Score);
	}
	
	public boolean didTeam1Win() {
		return team1Score > team2Score;
	}
	
	/**
	 * Gonna look like this: 
	 * 
	 * Team  P1  P2  P3
	 * Team1  #   #   #
	 * Team2  #   #   # 
	 */
	public String fancyToString() {
		int headerLength = boxScore.length;
		String headerLine = "Team";
		
		String team1OutputLine = team1.teamName + "\t";
		String team2OutputLine = team2.teamName + "\t";
		
		//Each team line gets the team name, p1-3 scores, any OT, and final.
		for (int i = 0; i < boxScore.length; i++) {
			team1OutputLine += boxScore[i][0] + "\t";
			team2OutputLine += boxScore[i][1] + "\t";
			
			team1Score += boxScore[i][0];
			team2Score += boxScore[i][1];
		}
		team1OutputLine += team1Score;
		team2OutputLine += team2Score;

		for (int i = 0; i < headerLength; i++) {
			headerLine += "\t" + (i <= 2 ? "P" + (i + 1) : "OT" + (i -2));
		}
		headerLine += "\tF";
		
		return headerLine + "\n" + team1OutputLine + "\n" + team2OutputLine;
	}
}