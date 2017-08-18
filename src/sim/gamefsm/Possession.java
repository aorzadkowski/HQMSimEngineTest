package sim.gamefsm;

import hqmdatabase.Player;
import sim.Team;

public class Possession extends State {

	@Override
	public State getNextState(GameState gs) {
			/*
			 * possible actions:
			 * 		skate
			 * 		pass
			 * 			boards
			 * 			breakout
			 * 			open ice
			 * 		shoot
			 * 			longshot
			 * 			wristshot
			 * 			pointshot
			 * 
			 * possible turnovers:
			 * 		failed pass
			 * 			missed
			 * 			tipped
			 * 			intercepted
			 * 		failed shot
			 * 			missed
			 * 			blocked
			 * 			saved
			 * 		failed skate
			 * 			lost puck
			 * 			poke checked
			 * 			body checked
			 * 			physics off stick
			 */
		
		//If the player recently won a faceoff, win it left.
		Team teamInPossession = (gs.homeTeamInPossession ? gs.homeTeam : gs.awayTeam);
		Team teamOnDefense = (gs.homeTeamInPossession ? gs.awayTeam : gs.homeTeam);
		
		if (gs.recentEvents.get(gs.recentEvents.size() - 1).event == Event.WIN_FACEOFF) {      //If the most recent event is a faceoff,
			
		}
		
		return null;
	}
	
	private EventHandler faceoffPass(Player playerInPossession, Team teamInPossession) {
		
		
		return null;
	}
}
