package sim.gamefsm;

public enum Event {
	FACEOFF,WIN_FACEOFF,SKATE,OPEN_PASS,OPEN_RECEIVE,BOARD_PASS,BOARD_RECEIVE,BREAKOUT_PASS,BREAKOUT_RECEIVE,RECEIVE_MISSED,PASS_MISSED,PASS_TIPPED,PASS_INTERCEPTED,PASS_DUMPED,
	SHOT_LONG,SHOT_WRIST,SHOT_POINT,SHOT_WRAP,SHOT_GOAL,SHOT_ASSISTED_GOAL,SHOT_BLOCKED,SHOT_MISSED,SHOT_SAVED,
	LOST_PUCK,POKE_CHECKED,BODY_CHECKED,PHYSICS,SWING_MISSED;
	
	/**
	 * %p1 refers to player 1, while %p2 refers to player 2. Replace these strings with player names.
	 * @return
	 */
	public String getEventText() {
		switch (this) {
		case FACEOFF: return "Face off!";
		case WIN_FACEOFF: return "%p1 has won the faceoff!";
		case SKATE: return "%p1 skates up with the puck.";
		case OPEN_PASS: return "%p1 passes the puck up the open ice to %p2.";
		case OPEN_RECEIVE: return "%p1 has the puck.";
		case BREAKOUT_PASS: return "%p1 passes the puck up to %p2.";
		case BREAKOUT_RECEIVE: return "%p1 got the puck.";
		case BOARD_PASS: return "%p1 hits it up the boards to %p2.";
		case BOARD_RECEIVE: return "%p1 grabs if off the boards.";
		case RECEIVE_MISSED: return "%p1 could not reach the puck.";
		case PASS_MISSED: return "%p1 missed the pass.";
		case PASS_TIPPED: return "%p2's pass was tipped by %p1!";
		case PASS_INTERCEPTED: return "%p2 has intercepted the pass!";
		case PASS_DUMPED: return "%p1 dumps the puck.";
		case SHOT_LONG: return "%p1 attemps the long shot.";
		case SHOT_WRIST: return "And a wrist shot from %p1.";
		case SHOT_POINT: return "%p1 has the puck on the point and it's going to be a shot!";
		case SHOT_WRAP: return "%p1 goes for the wrap!";
		case SHOT_GOAL: return "%p1 shoots! He scores!";
		case SHOT_ASSISTED_GOAL: return "%p1 scores! Assisted by %p2";
		case SHOT_BLOCKED: return "%p2 blocks %p1's shot.";
		case SHOT_MISSED: return "It's wide.";
		case SHOT_SAVED: return "%p2 makes the save!";
		case LOST_PUCK: return "%p1 loses the puck.";
		case POKE_CHECKED: return "%p2 pokes the puck off of %p1.";
		case BODY_CHECKED: return "And %p2 lays down the body on %p1!";
		case PHYSICS: return "The puck physics off of %p1's and %p2's stick.";
		case SWING_MISSED: return "%p1 fans over the puck.";
		default: return "SYNTAX ERROR!";
		}
	}
}