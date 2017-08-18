package sim.gamefsm;

public class Faceoff extends State {

	@Override
	public State getNextState(GameState gs) {
		int totalFaceoff = gs.homeTeam.teamPlayers[0].getStat("faceoffs") + gs.awayTeam.teamPlayers[1].getStat("faceoffs");
		if (Math.random() < ((double) gs.homeTeam.teamPlayers[0].getStat(0)) / totalFaceoff) {  
			gs.playerInPossession = gs.homeTeam.teamPlayers[0];
			gs.homeTeamInPossession = true;
			EventHandler faceoffWin = new EventHandler (gs.homeTeam.teamPlayers[0], null, Event.WIN_FACEOFF, 300 - gs.timeLeft);
			gs.recentEvents.add(faceoffWin);
		} else {
			gs.playerInPossession = gs.awayTeam.teamPlayers[0];
			gs.homeTeamInPossession = false;
			EventHandler faceoffWin = new EventHandler (gs.awayTeam.teamPlayers[0], null, Event.WIN_FACEOFF, 300 - gs.timeLeft);
			gs.recentEvents.add(faceoffWin);
		}
		return gs.possibleStates[1];
	}
}