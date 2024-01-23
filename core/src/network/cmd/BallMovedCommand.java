package network.cmd;


import br.com.leandro.pong.model.Ball;
import br.com.leandro.pong.model.GameState;
import game.MatchRoom;

import java.net.Socket;

public class BallMovedCommand extends Command {
	private static final long serialVersionUID = 1094719716851217045L;
	private float x;
	private float y;
	private float speedX;
	private float speedY;
	private String playerToReceived;

	public BallMovedCommand(float x, float y, float speedX, float speedY, String playerToReceived) {
		super(CommandType.BALL_MOVE);
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.playerToReceived = playerToReceived;
	}

	@Override
	public void executeClient(Socket client, String player) {
		Ball.getInstance().updateBall(x, y, speedX, speedY);
	}

}
