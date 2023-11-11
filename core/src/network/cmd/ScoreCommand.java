package network.cmd;


import br.com.leandro.pong.model.Ball;
import br.com.leandro.pong.model.GameState;
import game.MatchRoom;

import java.net.Socket;

public class ScoreCommand extends Command {
	private static final long serialVersionUID = 1094719716851217045L;
	private String playerToReceived;
	private String playerScored;

	public ScoreCommand(String playerToReceived, String playerScored) {
		super(CommandType.SCORE);
		this.playerScored = playerScored;
		this.playerToReceived = playerToReceived;
	}

	@Override
	public void executeClient(Socket client, String player) {
		Ball.getInstance().resetGame();
		if (Ball.getInstance().getPlayerOne().getPlayer().equals(playerScored)) {
			Ball.getInstance().getPlayerOne().score();
		}
		if (Ball.getInstance().getPlayerTwo().getPlayer().equals(playerScored)) {
			Ball.getInstance().getPlayerTwo().score();
		}
	}

}
