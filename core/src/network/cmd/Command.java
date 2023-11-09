package network.cmd;

import java.io.Serializable;
import java.net.Socket;

public class Command implements Serializable {
	private static final long serialVersionUID = 6557517733535860565L;
	private CommandType type;
	
	public Command(CommandType type) {
		this.type = type;
	}

	public CommandType getType() {
		return type;
	}
	
	public void setType(CommandType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Command [type=" + type + "]";
	}

	public void executeServer(Socket client, String player) {
		throw new RuntimeException("Nao implementado");
	}
	
	public void executeClient(Socket client, String player) {
		throw new RuntimeException("Nao implementado");
	}
}