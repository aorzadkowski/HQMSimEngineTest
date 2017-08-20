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
		return (player.role != Role.GOALIE ? ((double) getPoints()) / getGamesPlayed() : ((double) getPoints()) / getGamesPlayedAtG()); 
	}
	
	public double getSavePercentage() {
		return (player.role == Role.GOALIE ? ((double) getSaves()) / getShotsOnNet() : 0);
	}
	
	public double getSavesPerGame() {
		return (player.role == Role.GOALIE ? ((double) getSaves()) / getGamesPlayedAtG() : 0);
	}
	
	public int getGoalsAllowed() {
		return (player.role == Role.GOALIE ? (getShotsOnNet() - getSaves() >= 0 ? getShotsOnNet() - getSaves() : 0) : 0);
	}
	
	public double getGoalsAllowedAverage() {
		return (player.role == Role.GOALIE ? ((double) getGoalsAllowed()) / getGamesPlayedAtG() : 0);
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
	
	public void removeShot() {
		stats.replace("Shots", stats.get("Shots") - 1);
	}
	
	public void addStats(Stats other) {
		for (int i = 0; i < other.getShots(); i++) {
			addShot();
		}
		
		for (int i = 0; i < other.getAssists(); i++) {
			addAssist();
		}
		
		for (int i = 0; i < other.getGoals(); i++) {
			addGoal();
		}
		
		for (int i = 0; i < other.getShotsOnNet(); i++) {
			addShotOnNet();
		}
		
		for (int i = 0; i < other.getSaves(); i++) {
			addSave();
		}
		
		for (int i = 0;  i < other.getGamesPlayed(); i++) {
			addGamesPlayed();
		}
		
		for (int i = 0; i < other.getGamesPlayedAtG(); i++) {
			addGamesPlayedAtG();
		}
		
		//There are two loops for +/-. If a player has a negative +/-, the first loop will never be true. The reverse is true for the second loop.
		for (int i = 0; i < other.getPlusMinus(); i++) {
			addPlusMinus();
		}
		
		for (int i = 0; i > other.getPlusMinus(); i--) {
			removePlusMinus();
		}
	}
	
	public String toCSV() {
		return player.getName() + "," + player.getSeason() + "," + player.position + "," 
				+ getPoints() + "," + getGoals() + "," + getAssists() + "," 
				+ getPointsPerGame() + "," + getPlusMinus() + "," + getShots() + "," + getShotsOnNet() + ","
				+ getSaves() + "," + getSavePercentage() + "," + getSavesPerGame() + "," 
				+ getGoalsAllowed() + "," + getGoalsAllowedAverage() + "," + getGamesPlayed() + "," 
				+ getGamesPlayedAtG();
	}
}