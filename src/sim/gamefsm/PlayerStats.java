package sim.gamefsm;

import java.util.HashMap;
import java.util.Map;

import hqmdatabase.Player;
import sim.Team;

public class PlayerStats {
	Team team;
	Player player;
	
	Map<String, Integer> stats;
	
	
	/**
	 * int state depicts the state in which a skater is in. 
	 * 0 - nothing. "null" state.
	 * 1 - moving to position.  This could mean going for the puck, intercepting a puck, moving to a position to receive pass, or going for a defensive play
	 * 2 - skating with puck.
	 * 3 - passing the puck.
	 * 4 - shooting the puck.
	 * 5 - defensive play. 
	 */
	protected int state;  
	
	public PlayerStats(Team team, Player player) {
		this.team = team;
		this.player = player;
		
		state = 0;
		
		stats = new HashMap<String, Integer>();
		
		initStatsMap();
	}
	
	private void initStatsMap() {
		stats.put("Goals", 0);
		stats.put("Assists", 0);
		stats.put("Plus/Minus", 0);
		stats.put("Game Winning Goals", 0);
		stats.put("Shots", 0);
		stats.put("Games Played", 0);
		
		//Goalie stats
		stats.put("Shots on Net", 0);
		stats.put("Saves", 0);
		stats.put("Goals Allowed", 0);
		stats.put("Games Played at Goalie", 0);
		stats.put("Wins at Goalie", 0);
		stats.put("Shutouts", 0);
		stats.put("Time on Ice", 0); //in seconds
		
		//Below are additional stats kept by the engine and NOT the LHL.
		stats.put("Attempted Passes", 0);
		stats.put("Completed Passes", 0);
		stats.put("Hits", 0);
		stats.put("Poke Checks", 0);
		stats.put("Time with Puck", 0);
		stats.put("Face Offs Attempted", 0);
		stats.put("Face Offs Won", 0);
	}

	public int getStat(String stat) {
		if (!stats.containsKey(stat)) {
			System.err.println(stat + " is an invalid stat!");
			return -1;
		}
		
		return stats.get(stat);
	}

	public void addStat(String stat, int value) {
		if (!stats.containsKey(stat)) {
			System.err.println(stat + " is an invalid stat!");
		}
		
		stats.replace(stat, getStat(stat) + value);
	}
	
	public void combineStats(PlayerStats other) {
		if (!player.equals(other.player)) {
			System.err.println("Players are not identical! " + player.getName() + " cannot be combined with " + other.player.getName());
		}
		
		for (String stat : stats.keySet()) {
			addStat(stat, other.getStat(stat));
		}
	}
	
	public String keyToCSV() {
		String toReturn = "Name,Season,Position";
		
		for (String stat : stats.keySet()) {
			toReturn += "," + stat;
		}
		
		return toReturn;
	}
	
	public String toCSV() {
		String toReturn = player.getName() + "," + player.getSeason() + "," + player.position;
		
		for (String stat : stats.keySet()) {
			toReturn += "," + getStat(stat);
		}
		
		return toReturn;
	}
	
	public int getState() {
		return state;
	}
	
	protected void setState(int newState) {
		state = newState;
	}
}