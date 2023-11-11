package network.cmd;


import br.com.leandro.pong.model.Ball;

import java.net.Socket;

public class PaddleMovedCommand extends Command {
	private static final long serialVersionUID = 1094719716851217045L;
	private float y;
	private String playerToReceived;
	private String playerMoved;

	public PaddleMovedCommand(float y, String playerToReceived, String playerMoved) {
		super(CommandType.PADDLE_MOVE);
		this.y = y;
		this.playerToReceived = playerToReceived;
		this.playerMoved = playerMoved;
	}

	@Override
	public void executeClient(Socket client, String player) {
		if (Ball.getInstance().getPlayerOne().getPlayer().equals(playerMoved)) {
			Ball.getInstance().getPlayerOne().updatePosition(y);
		}
		if (Ball.getInstance().getPlayerTwo().getPlayer().equals(playerMoved)) {
			Ball.getInstance().getPlayerTwo().updatePosition(y);
		}
	}

}
