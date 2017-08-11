package sim.gamefsm;

import hqmdatabase.Player;

public class EventHandler {
	Player player1;
	Player player2;
	
	Event event;
	
	public EventHandler(Player player1, Player player2, Event event) {
		this.player1 = player1;
		this.player2 = player2;
		
		this.event = event;
	}
	
	public String getPBPEvent() {
		String toReturn = event.getEventText();
		
		toReturn = toReturn.replaceAll("%p1", player1.getName());
		toReturn = toReturn.replaceAll("%p2", player2.getName());
		
		return toReturn;
	}
}