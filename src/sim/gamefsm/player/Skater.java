package sim.gamefsm.player;

import hqmdatabase.Player;
import hqmdatabase.Position;
import sim.gamefsm.GameState;

public abstract class Skater {
	protected Player player;
	protected boolean isHomeTeam;  //Home team is red team, also means left team.
	
	protected int x;
	protected int y;
	
	public Skater(Player player, boolean isHomeTeam) {
		this.player = player;
		this.isHomeTeam = isHomeTeam;
		
		x = getStartingXBasedOnPosition(player.position);
		y = getStartingYBasedOnPosition(player.position);
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

	protected int getDirectionTowardsPuck(GameState gs) {
		int dx = x - gs.puckX;
		int dy = y - gs.puckY;
		
		if (dx == 0 && dy > 0) 
			return 1;
		else if (dx < 0 && dy > 0)
			return 2;
		else if (dx < 0 && dy == 0)
			return 3;
		else if (dx < 0 && dy < 0) 
			return 4;
		else if (dx == 0 && dy < 0) 
			return 5;
		else if (dx > 0 && dy < 0) 
			return 6;
		else if (dx > 0 && dy == 0) 
			return 7;
		else if (dx > 0 && dy > 0)
			return 0;
		
		return -1;
	}
	
	/**
	 * Moves the player in a direction using the following pattern (where O is the player)
	 * 
	 * 		8  1  2
	 *       \ | /
	 * 		7 -O- 3
	 * 		 / | \
	 * 		6  5  4
	 * @param direction 
	 */
	protected void move(int direction) {
		switch (direction) {
		case 1: if (isValidLocation(x, y - 1)) 					  y -= 1; 
		case 2: if (isValidLocation(x + 1, y - 1)) 		{ x += 1; y -= 1; } 
		case 3: if (isValidLocation(x + 1, y)) 			  x += 1; 
		case 4: if (isValidLocation(x + 1, y + 1)) 		{ x += 1; y += 1; }
		case 5: if (isValidLocation(x, y + 1)) 			 		  y += 1; 
		case 6: if (isValidLocation(x - 1, y + 1)) 		{ x -= 1; y += 1; }
		case 7: if (isValidLocation(x - 1, y)) 			  x -= 1;
		case 8: if (isValidLocation(x - 1, y - 1)) 		{ x -= 1; y -= 1; }
		}
	}
	
	protected boolean isValidLocation(int x, int y) {
		return x < 0 || y < 0 || x > 9 || y > 4;  //Checks to make sure it's in bounds.
	}
}