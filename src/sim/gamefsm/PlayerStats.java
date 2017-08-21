package sim.gamefsm;

import java.util.HashMap;
import java.util.Map;

import hqmdatabase.Player;
import sim.Team;

public class PlayerStats {
	Team team;
	Player player;
	
	Map<String, Integer> stats;
	
	public PlayerStats(Team team, Player player) {
		this.team = team;
		this.player = player;
		
		stats = new HashMap<String, Integer>();
	}
	
	private void initStatsMap() {
		stats.put("Goals", 0);
		stats.put("Assists", 0);
		stats.put("Plus/Minus", 0);
		stats.put("Game Winning Goals", 0);
		stats.put("Shots", 0);
		stats.put("Saves", 0);
		stats.put("Goals Allowed", 0);
		stats.put("Games Played at Goalie", 0);
		stats.put("Wins at Goalie", 0);
		stats.put("Games Played", 0);
		stats.put("Shutouts", 0);
		stats.put("Time on Ice", 0); //in seconds
		
		//Below are additional stats kept by the engine and NOT the LHL.
		stats.put("Attempted Passes", 0);
		stats.put("Completed Passes", 0);
		stats.put("Hits", 0);
		stats.put("Poke Checks", 0);
		stats.put("Time with Puck", 0);
		stats.put("Face Offs won", 0);
	}
}
