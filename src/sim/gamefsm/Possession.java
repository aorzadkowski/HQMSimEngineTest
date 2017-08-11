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
		if (gs.recentEvents.get(gs.recentEvents.size() - 1).event == Event.WIN_FACEOFF) {
			double random = Math.random();
			
			
				
				//return gs.possibleStates[2];
			 
				EventHandler event = new EventHandler(gs.playerInPossession, (gs.team1InPossession ? gs.team1.forwards[1] : gs.team2.forwards[1]), Event.PASS);
				gs.recentEvents.add(event);
				return gs.possibleStates[1];
			
		}
		
		return null;
	}
	
	private EventHandler boardPass(Team team, Player playerInPossession) {
		Player[] possiblePasses = new Player[4];  //Every other player not the one in possession.
		
		int currIndex = 0;
		for (int i = 0; i < team.teamPlayers.length; i++) {
			if (!team.teamPlayers[i].equals(playerInPossession)) {
				possiblePasses[currIndex] = team.teamPlayers[i];
				currIndex++;
			}
		}
		
		if (Math.random() > ((double) playerInPossession.stats[4]) / 20) {
			EventHandler event = new EventHandler(playerInPossession, null, Event.PASS_MISSED);
			return event;
		}
		return null;
	}
	
	private double skatingAbility(Player player) {
		double handEyeWeighted = player.getStat(9) * player.role.getWeights()[9];
		double stickHandlingWeighted = player.getStat(10) * player.role.getWeights()[10];
		double danglingWeighted = player.getStat(11) * player.role.getWeights()[11];
		
		return (handEyeWeighted + stickHandlingWeighted + danglingWeighted) / 3;
	}
	
	private double passingAbility(Player player) {
		double boardsWeighted = player.getStat(4) * player.role.getWeights()[4];
		double openWeighted = player.getStat(5) * player.role.getWeights()[5];
		double breakoutWeighted = player.getStat(6) * player.role.getWeights()[6];
		
		return (boardsWeighted + openWeighted + breakoutWeighted) / 3;
	}
	
	private double shootingAbility(Player player) {
		double longWeighted = player.getStat(1) * player.role.getWeights()[1];
		double wristWeighted = player.getStat(3) * player.role.getWeights()[3];
		double pointWeighted = player.getStat(2) * player.role.getWeights()[2];
		
		return (longWeighted + wristWeighted + pointWeighted) / 3;
	}
}
