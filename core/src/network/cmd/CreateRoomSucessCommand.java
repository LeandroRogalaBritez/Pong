package network.cmd;

import br.com.leandro.pong.screen.multiplayer.MultiPlayerScreen;

import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class CreateRoomSucessCommand extends Command {
	private static final long serialVersionUID = 1094719716851217045L;
	private String roomId;

	public CreateRoomSucessCommand(String roomId) {
		super(CommandType.CREATE_ROOM_SUCESS);
		this.roomId = roomId;
	}

	@Override
	public void executeClient(Socket client, String player) {
		MultiPlayerScreen.setRoomId(roomId);
	}
}
