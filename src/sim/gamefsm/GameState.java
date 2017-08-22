package sim.gamefsm;

import java.util.ArrayList;

import hqmdatabase.Player;
import sim.Team;

public class GameState {
	Team homeTeam;
	Team awayTeam;
	
	Player playerInPossession;
	boolean homeTeamInPossession;
	
	int homeScore;
	int awayScore;
	
	int period;
	int timeLeft; //in seconds.
	
	ArrayList<EventHandler> recentEvents;
	
	State[] possibleStates = { new Faceoff(), new Possession(), new FreePuck() };
	
	State currentState;
	
	/*
	 * In order for puck movement to not be based on random players, puck positioning has to be accounted for.  
	 * X axis is how far the puck is from the center line, where the line is between values 4 and 5. As the value
	 * approaches 0, the closer the puck is to the red net.  As the value goes up to 9, the puck moves closer to
	 * the blue net.  It's important to remember that x values0 and 9 are actually behind the nets. 
	 *
	 * As for the y value, it is how far off to the side the puck is to the net. 0 is on the red net's left side boards
	 * (or blue's right side), and 4 is to red's right side boards (or blue's left side boards). 2 is dead center.
	 *
	 * The nets are between (0-1, 3) for red and (8-9, 3) for blue.  This means goalies would stand at (1, 2) and (8, 2)
	 * for each team.
	 *
	 * Player starting positions are:
	 * 		Home Team:
	 * 			C (4,2)
	 * 			LW (4,1)
	 * 			LD (3,1)
	 * 			RD (3,3)
	 * 			G (1,2)
	 * 		Away Team:
	 * 			C (5,2)
	 * 			LW (5,3)
	 * 			LD (6,3)
	 * 			RD (6,1)
	 * 			G (8,2)
	 */
	public int puckX;
	public int puckY;
	
	public GameState(Team homeTeam, Team awayTeam) {
		this.homeTeam = applyConsistencyForTeam(homeTeam);
		this.awayTeam = applyConsistencyForTeam(awayTeam);
		
		playerInPossession = null;
		homeTeamInPossession = true;
		
		homeScore = 0;
		awayScore = 0;
		
		period = 1;
		timeLeft = 300; //5 * 60 = 5 minutes
		
		recentEvents = new ArrayList<>();
		
		currentState = possibleStates[0];
	}
	
	private String getNormalizedTime(int seconds) {
		int minute = seconds / 60;
		int second = seconds % 60;
		
		return minute + ":" + (second < 10 ? "0" + second : second); 
	}
	
	Team applyConsistencyForTeam(Team team) {
		Team newTeam = new Team(team);
		
		for (int i = 0; i < newTeam.teamPlayers.length; i++) {
			newTeam.teamPlayers[i] = applyConsistencyForAllStats(newTeam.teamPlayers[i]);
		}
		
		return newTeam;
	}
	
	Player applyConsistencyForAllStats(Player player) {
		Player newPlayer = new Player(player);
		
		for (int i = 0; i < newPlayer.stats.length - 1; i++) {
			newPlayer.stats[i] = applyConsistency(newPlayer, i);
		}
		
		return newPlayer;
	}
	
	int applyConsistency (Player player, int skill) {
		if (skill < 0 || skill > 18) {
			System.err.println("Please enter a valid skill value: " + skill);
			return -1;
		}
		
		boolean decrease = false;
		
		decrease = Math.random() > ((double) player.getStat(19)) / 20; 
		
		if (decrease) {
			double random = Math.random();
			
			if (random > 0.6666) return player.getStat(skill) - 1;
			else if (random > 0.2222) return player.getStat(skill) - 2;
			else return player.getStat(skill) - 3;
		} else 
			return player.getStat(skill);
	}
	
	public String fullPlayByPlay() {
		String toReturn = "";
		
		toReturn += "Tonight's game is between " + awayTeam.teamName + " play at " + homeTeam.teamName + ".\n";
		
		for (int i = 0; i < recentEvents.size(); i++) {
			EventHandler eventH = recentEvents.get(i);
			String play = eventH.event.getEventText().replace("%p1", eventH.player1.getName()).replace("%p2", eventH.player2.getName());
			
			toReturn += getNormalizedTime(eventH.time) + " : " + play + "\n";
		}
		
		return toReturn;
	}
	
	public String toString() {
		String toReturn = fullPlayByPlay();
		return toReturn;
	}
	
	public State getCurrentState() {
		return currentState;
	}
}