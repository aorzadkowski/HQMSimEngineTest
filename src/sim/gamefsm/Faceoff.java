package sim.gamefsm;

public class Faceoff extends State {

	@Override
	public State getNextState(GameState gs) {
		int totalFaceoff = gs.team1.teamPlayers[0].getStat("faceoffs") + gs.team2.teamPlayers[1].getStat("faceoffs");
		if (Math.random() < ((double) gs.team1.teamPlayers[0].getStat(0)) / totalFaceoff) {
			gs.playerInPossession = gs.team1.teamPlayers[0];
			gs.team1InPossession = true;
			EventHandler faceoffWin = new EventHandler (gs.team1.teamPlayers[0], null, Event.WIN_FACEOFF);
			gs.recentEvents.add(faceoffWin);
		} else {
			gs.playerInPossession = gs.team2.teamPlayers[0];
			gs.team1InPossession = false;
			EventHandler faceoffWin = new EventHandler (gs.team2.teamPlayers[0], null, Event.WIN_FACEOFF);
			gs.recentEvents.add(faceoffWin);
		}
		System.out.println("Faceoff!");
		return gs.possibleStates[1];
	}
}