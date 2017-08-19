package sim;
import java.util.HashMap;
import java.util.Map;

import hqmdatabase.Player;
import hqmdatabase.Role;

public class Stats {
	private Player player;
	Map <String, Integer> stats;
	
	public Stats ( Player P1 ) {
		this.player = P1;
		stats = new HashMap<String,Integer>();
		initMap();
	}
	
	private void initMap () {
		stats.put("Shots", 0);
		stats.put("Goals", 0);
		stats.put("Assists", 0);
		stats.put("Shots on net", 0);
		stats.put("Saves", 0);
		stats.put("Games played", 0);
		stats.put("Games played at G", 0);
		stats.put("Plus/minus", 0);
	}
	
	public String getPlayerName() {
		return player.getName();
	}

	public Player getPlayer() {
		return player;
	}
	
	public int getPoints() {
		return getGoals() + getAssists();
	}
	
	public double getPointsPerGame() {
		return ((double) getPoints()) / getGamesPlayed(); 
	}
	
	public double getSavePercentage() {
		return ((double) getSaves()) / getShotsOnNet();
	}
	
	public double getSavesPerGame() {
		return ((double) getSaves()) / getGamesPlayedAtG();
	}
	
	public int getGoalsAllowed() {
		return getShotsOnNet() - getSaves();
	}
	
	public double getGoalsAllowedAverage() {
		return ((double) getGoalsAllowed()) / getGamesPlayedAtG();
	}
	
	public int getShots() {
		return stats.get("Shots");
	}
	
	public int getGoals() {
		return stats.get("Goals");
	}
	
	public int getAssists() {
		return stats.get("Assists");
	}
	
	public int getShotsOnNet() {
		return stats.get("Shots on net");
	}
	
	public int getSaves() {
		return stats.get("Saves");
	}
	
	public int getGamesPlayed() {
		return stats.get("Games played");
	}
	
	public int getGamesPlayedAtG() {
		return stats.get("Games played at G");
	}
	
	public int getPlusMinus() {
		return stats.get("Plus/minus");
	}
	
	public void addShot() {
		stats.replace("Shots",stats.get("Shots") + 1);
	}
	
	public void addGoal() {
		stats.replace("Goals",stats.get("Goals") + 1);
	}
	
	public void addAssist() {
		stats.replace("Assists",stats.get("Assists") + 1);
	}
	
	public void addShotOnNet() {
		stats.replace("Shots on net",stats.get("Shots on net") + 1);
	}
	
	public void addSave() {
		stats.replace("Saves", stats.get("Saves") + 1);
	}
	
	public void addGamesPlayed() {
		stats.replace("Games played", stats.get("Games played") + 1);
	}
	
	public void addGamesPlayedAtG() {
		stats.replace("Games played at G", stats.get("Games played at G") + 1);
	}
	
	public void addPlusMinus() {
		stats.replace("Plus/minus", stats.get("Plus/minus") + 1);
	}
	
	public void removePlusMinus() {
		stats.replace("Plus/minus", stats.get("Plus/minus") - 1);
	}
	
	public String toCSV() {
		return player.getName() + "," + player.getSeason() + "," + player.position + "," 
				+ getPoints() + "," + getGoals() + "," + getAssists() + "," 
				+ getPointsPerGame() + "," + getPlusMinus() + "," + getShots() + "," 
				+ getSaves() + "," + getSavePercentage() + "," + getSavesPerGame() + "," 
				+ getGoalsAllowed() + "," + getGoalsAllowedAverage() + "," + getGamesPlayed() + "," 
				+ getGamesPlayedAtG();
	}
}