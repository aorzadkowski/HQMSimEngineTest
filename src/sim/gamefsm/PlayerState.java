package sim.gamefsm;

import java.util.HashMap;
import java.util.Map;

import hqmdatabase.Player;
import hqmdatabase.Position;

public class PlayerState {
	private Player player;
	private boolean isHomeTeam;  //Home team is red team, also means left team.
	
	private int x;
	private int y;
	
	Map<String, Integer> stats; 
	
	public PlayerState(Player player, boolean isHomeTeam) {
		this.player = player;
		this.isHomeTeam = isHomeTeam;
		
		x = getStartingXBasedOnPosition(player.position);
		y = getStartingYBasedOnPosition(player.position);
		
		stats = new HashMap<String, Integer>();
		
		initStatsMap();
	}
	
	public void act(GameState gs) {
		
	}
	
	/**
	 * Muchlike how stats are kept nowadays, all players have the same stats, but depending on their position,
	 * only a few will get adjusted.  Skaters will never have to worry about any stats related to goalie play.
	 * There are a few extra stats that are not tracked in the LHL here just so we can better analyze the games.
	 */
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
	
	private int getStartingYBasedOnPosition(Position position) {
		if (isHomeTeam) {
			switch (position) {
			case G:
			case C: return 2;
			case LW:
			case LD: return 1;
			case RD: return 3;
			case NO_POS: System.err.println("NO_POS is an invalid position for PlayerState"); return -1;
			}
		} else {
			switch (position) {
			case G:
			case C: return 2;
			case LW: 
			case LD: return 3;
			case RD: return 1;
			case NO_POS: System.err.println("NO_POS is an invalid position for PlayerState"); return -1;
			}
		}
		System.err.println("PlayerState could not define Y value for position " + position);
		return -1;
	}
	
	private int getStartingXBasedOnPosition(Position position) {
		if (isHomeTeam) {
			switch (position) {
			case C:
			case LW: return 4;
			case LD:
			case RD: return 3;
			case G: return 1;
			case NO_POS: System.err.println("NO_POS is an invalid position for PlayerState"); return -1;
			}
		} else {
			switch (position) {
			case C:
			case LW: return 5;
			case LD:
			case RD: return 6;
			case G: return 8;
			case NO_POS: System.err.println("NO_POS is an invalid position for PlayerState"); return -1;
			}
		}
		System.err.println("PlayerState could not define X value for position " + position);
		return -1;
	}
}