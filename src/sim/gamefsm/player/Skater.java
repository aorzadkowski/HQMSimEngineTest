package sim.gamefsm.player;

import java.util.HashMap;
import java.util.Map;

import hqmdatabase.Player;
import hqmdatabase.Position;
import sim.gamefsm.GameState;

public abstract class Skater {
	protected Player player;
	protected boolean isHomeTeam;  //Home team is red team, also means left team.
	
	protected int x;
	protected int y;
	
	Map<String, Integer> stats; 
	
	public Skater(Player player, boolean isHomeTeam) {
		this.player = player;
		this.isHomeTeam = isHomeTeam;
		
		stats = new HashMap<String, Integer>();
	}
	
	public abstract void act(GameState gs);
	
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