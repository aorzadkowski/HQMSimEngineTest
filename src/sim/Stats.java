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
		if ( player.role != Role.GOALIE) {
			stats.put("Shots", 0);
			stats.put("Goals", 0);
			stats.put("Assists", 0);
		}
		else {
			stats.put("Shots on net", 0);
			stats.put("Saves", 0);
			stats.put("Assists", 0);
		}
		stats.put("Games played", 0);
	}
	
	public String getPlayerName() {
		return player.getName();
	}
	
	public String[] getKeys() {
		return stats.keySet().toArray(new String[0]);
	}
	
	public int getValue( String key ) {
		if ( !stats.containsKey(key) ) {
			System.err.println("Player " + player.getName() + " does not contain key " + key);
			return -1;
		}
		return stats.get(key);
	}
	
	public void addShot() {
		stats.replace("Shots",stats.get("Shots")+1);
	}
	
	public void addGoal() {
		stats.replace("Goals",stats.get("Goals")+1);
	}
	
	public void addAssist() {
		stats.replace("Assists",stats.get("Assists")+1);
	}
	
	public void addShotOnNet() {
		stats.replace("Shots on net",stats.get("Shots on net")+1);
	}
}
